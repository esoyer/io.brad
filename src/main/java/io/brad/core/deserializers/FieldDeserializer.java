package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import io.brad.core.ModelFields;
import io.brad.core.fields.*;
import io.brad.core.functions.NamedBiFunction;
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
            return deserializeComputedField(jsonParser, deserializationContext, treeNode);
        } else if ("biComputedFieldWithValue".equals(type)) {
            return deserializeBiComputedFieldWithValue(jsonParser, deserializationContext, treeNode);
        } else if ("biComputedFieldWithField".equals(type)) {
            return deserializeBiComputedFieldWithField(jsonParser, deserializationContext, treeNode);
        } else if ("fieldFromModel".equals(type)) {
            return deserializeFieldFromModel(treeNode);
        } else if ("delegateField".equals(type)) {
            return deserializeDelegateField(jsonParser, deserializationContext, treeNode);
        } else {
            throw new IllegalStateException("Could not deserialize field with type=" + type);
        }
    }

    private Field<?, ?> deserializeFieldFromModel(TreeNode treeNode) {
        String code = ((TextNode) treeNode.get("code")).textValue();

        return modelFields.getFields().stream()
                .filter(f -> f.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }

    private Field<?, ?> deserializeBiComputedFieldWithField(JsonParser jsonParser, DeserializationContext deserializationContext, TreeNode treeNode) throws IOException {
        Field<?, ?> initialField = deserializationContext.readValue(treeNode.get("initialField").traverse(jsonParser.getCodec()), Field.class);
        NamedBiFunction<?, ?, ?> biFunction = deserializationContext.readValue(treeNode.get("biFunction").traverse(jsonParser.getCodec()), NamedBiFunction.class);
        Field<?, ?> otherField = deserializationContext.readValue(treeNode.get("otherField").traverse(jsonParser.getCodec()), Field.class);

        @SuppressWarnings({"unchecked", "rawtypes"})
        Field<?, ?> field = new BiComputedFieldWithField(initialField, biFunction, otherField);
        return field;
    }

    private Field<?, ?> deserializeBiComputedFieldWithValue(JsonParser jsonParser, DeserializationContext deserializationContext, TreeNode treeNode) throws IOException {
        Field<?, ?> initialField = deserializationContext.readValue(treeNode.get("initialField").traverse(jsonParser.getCodec()), Field.class);
        NamedBiFunction<?, ?, ?> biFunction = deserializationContext.readValue(treeNode.get("biFunction").traverse(jsonParser.getCodec()), NamedBiFunction.class);
        Object value = deserializationContext.readValue(treeNode.get("value").traverse(jsonParser.getCodec()), biFunction.getParameterType());

        @SuppressWarnings({"unchecked", "rawtypes"})
        Field<?, ?> field = new BiComputedFieldWithValue(initialField, biFunction, value);
        return field;
    }

    private Field<?, ?> deserializeComputedField(JsonParser jsonParser, DeserializationContext deserializationContext, TreeNode treeNode) throws IOException {
        Field<?, ?> initialField = deserializationContext.readValue(treeNode.get("initialField").traverse(jsonParser.getCodec()), Field.class);
        NamedFunction<?, ?> function = deserializationContext.readValue(treeNode.get("function").traverse(jsonParser.getCodec()), NamedFunction.class);

        @SuppressWarnings({"unchecked", "rawtypes"})
        Field<?, ?> field = new ComputedField(initialField, function);
        return field;
    }

    private Field<?, ?> deserializeDelegateField(JsonParser jsonParser, DeserializationContext deserializationContext, TreeNode treeNode) throws IOException {
        Field<?, ?> delegateField = deserializationContext.readValue(treeNode.get("field").traverse(jsonParser.getCodec()), Field.class);

        @SuppressWarnings({"unchecked", "rawtypes"})
        Field<?, ?> field = new DelegateFieldImpl(delegateField);
        return field;
    }
}
