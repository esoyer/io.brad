package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import io.brad.core.ModelFields;
import io.brad.core.fields.ComputedField;
import io.brad.core.fields.Field;
import io.brad.core.functions.NamedFunction;

import java.io.IOException;

public class FieldDeserializer extends StdDeserializer<Field<?, ?>> {

    private final ModelFields<?> modelFields;

    public FieldDeserializer(ModelFields<?> modelFields) {
        super(Field.class);
        this.modelFields = modelFields;
    }

    @Override
    public Field<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String type = ((TextNode) treeNode.get("type")).textValue();

        if ("computedField".equals(type)) {
            Field<?, ?> initialField = deserializationContext.readValue(treeNode.get("initialField").traverse(jsonParser.getCodec()), Field.class);
            NamedFunction<?, ?> function = deserializationContext.readValue(treeNode.get("function").traverse(jsonParser.getCodec()), NamedFunction.class);

            @SuppressWarnings({"unchecked", "rawtypes"})
            ComputedField<?, ?, ?> field = new ComputedField(initialField, function) {
            };

            return field;
        } else if ("fieldFromModel".equals(type)) {
            String code = ((TextNode) treeNode.get("code")).textValue();

            return modelFields.getFields().stream()
                    .filter(f -> f.getCode().equals(code))
                    .findFirst()
                    .orElseThrow();
        } else {
            throw new IllegalStateException("Could not deserialize field with type=" + type);
        }
    }
}
