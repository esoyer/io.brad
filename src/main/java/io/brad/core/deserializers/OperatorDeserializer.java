package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import io.brad.core.operators.ComparisonOperator;
import io.brad.core.operators.Operators;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.reflect.Modifier.*;
import static org.jooq.lambda.Unchecked.function;

public class OperatorDeserializer extends StdDeserializer<ComparisonOperator<?>> {

    public OperatorDeserializer() {
        super(ComparisonOperator.class);
    }

    @Override
    public ComparisonOperator<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String code = ((TextNode) treeNode.get("code")).textValue();

        return Arrays.stream(Operators.class.getFields())
                .filter(f -> isPublic(f.getModifiers()) && isStatic(f.getModifiers()) && isFinal(f.getModifiers()))
                .filter(f -> ComparisonOperator.class.isAssignableFrom(f.getType()))
                .map(function(f -> f.get(null)))
                .map(ComparisonOperator.class::cast)
                .filter(o -> o.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
