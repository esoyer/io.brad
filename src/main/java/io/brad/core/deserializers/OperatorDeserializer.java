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

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.jooq.lambda.Unchecked.function;

public class OperatorDeserializer extends StdDeserializer<ComparisonOperator<?>> {

    public OperatorDeserializer() {
        super(ComparisonOperator.class);
    }

    @Override
    public ComparisonOperator<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String code = ((TextNode) treeNode.get("code")).textValue();

        return Arrays.stream(Operators.class.getMethods())
                .filter(m -> isPublic(m.getModifiers()) && isStatic(m.getModifiers()))
                .filter(m -> m.getParameterCount() == 0)
                .filter(m -> ComparisonOperator.class.isAssignableFrom(m.getReturnType()))
                .map(function(m -> m.invoke(null)))
                .map(ComparisonOperator.class::cast)
                .filter(m -> m.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
