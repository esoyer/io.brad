package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.brad.core.fields.Field;
import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldRule;

import java.io.IOException;

public class FieldRuleDeserializer extends StdDeserializer<FieldRule<?, ?>> {

    public FieldRuleDeserializer() {
        super(FieldRule.class);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FieldRule<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        TreeNode fieldNode = rootNode.get("field");
        Field<?, ?> field = deserializationContext.readValue(fieldNode.traverse(jsonParser.getCodec()), Field.class);

        TreeNode operatorNode = rootNode.get("operator");
        ComparisonOperator<?> operator = deserializationContext.readValue(operatorNode.traverse(jsonParser.getCodec()), ComparisonOperator.class);

        TreeNode valueNode = rootNode.get("value");
        JsonParser valueJsonParser = valueNode.traverse(jsonParser.getCodec());
        valueJsonParser.nextToken();

        Object value = deserializationContext.readValue(valueJsonParser,
                TypeFactory.defaultInstance().constructType(field.getType()));

        return new FieldRule(field, operator, value);
    }
}
