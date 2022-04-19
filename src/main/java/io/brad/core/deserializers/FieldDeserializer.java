package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import io.brad.core.ModelFields;
import io.brad.core.fields.Field;

import java.io.IOException;

import static java.lang.reflect.Modifier.*;

public class FieldDeserializer<M> extends StdDeserializer<Field<M, ?>> {

    private final ModelFields<M> modelFields;

    public FieldDeserializer(ModelFields<M> modelFields) {
        super(Field.class);
        this.modelFields = modelFields;
    }

    @Override
    public Field<M, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String code = ((TextNode) treeNode.get("code")).textValue();

        return modelFields.getFields().stream()
                .filter(f -> f.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }

    private boolean isPublicStaticFinal(java.lang.reflect.Field f) {
        return isPublic(f.getModifiers()) && isStatic(f.getModifiers()) && isFinal(f.getModifiers());
    }
}
