package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.brad.core.fields.Field;
import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldToValueRule;

import java.io.IOException;

public class FieldToValueRuleDeserializer extends StdDeserializer<FieldToValueRule<?, ?>> {

    public FieldToValueRuleDeserializer() {
        super(FieldToValueRule.class);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public FieldToValueRule<?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        TreeNode fieldNode = rootNode.get("field");
        Field<?, ?> field = deserializationContext.readValue(fieldNode.traverse(jsonParser.getCodec()), Field.class);

        TreeNode operatorNode = rootNode.get("operator");
        ComparisonOperator<?> operator = deserializationContext.readValue(operatorNode.traverse(jsonParser.getCodec()), ComparisonOperator.class);

        TreeNode valueNode = rootNode.get("value");
        if (valueNode instanceof NullNode) {
            return new FieldToValueRule(field, operator, null);
        }

        JsonParser valueJsonParser = valueNode.traverse(jsonParser.getCodec());
        valueJsonParser.nextToken();

        Object value = deserializationContext.readValue(valueJsonParser,
                TypeFactory.defaultInstance().constructType(field.getType()));

        return new FieldToValueRule(field, operator, value);
    }
}
