package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.NullNode;
import io.brad.core.fields.Field;
import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldToValueRule;

import java.io.IOException;

public class FieldToValueRuleDeserializer extends StdDeserializer<FieldToValueRule<?, ?>> {

    public FieldToValueRuleDeserializer() {
        super(FieldToValueRule.class);
    }

    @Override
    public FieldToValueRule<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        TreeNode fieldNode = rootNode.get("field");
        Field<?, ?> field = deserializationContext.readValue(toJsonParser(fieldNode, jsonParser), Field.class);

        TreeNode operatorNode = rootNode.get("operator");
        ComparisonOperator<?> operator = deserializationContext.readValue(toJsonParser(operatorNode, jsonParser), ComparisonOperator.class);

        TreeNode valueNode = rootNode.get("value");
        Object value = valueNode instanceof NullNode ? null : deserializationContext.readValue(toJsonParser(valueNode, jsonParser), field.getType());

        @SuppressWarnings({"unchecked", "rawtypes"})
        FieldToValueRule result = new FieldToValueRule(field, operator, value);
        return result;
    }

    private static JsonParser toJsonParser(TreeNode fieldNode, JsonParser jsonParser) throws IOException {
        JsonParser result = fieldNode.traverse(jsonParser.getCodec());
        result.nextToken();
        return result;
    }
}
