package io.brad.core.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import io.brad.core.functions.BiFunctions;
import io.brad.core.functions.NamedBiFunction;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.jooq.lambda.Unchecked.function;

public class NamedBiFunctionDeserializer extends StdDeserializer<NamedBiFunction<?, ?, ?>> {

    public NamedBiFunctionDeserializer() {
        super(NamedBiFunction.class);
    }

    @Override
    public NamedBiFunction<?, ?, ?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String code = ((TextNode) treeNode.get("code")).textValue();

        return Arrays.stream(BiFunctions.class.getMethods())
                .filter(m -> isPublic(m.getModifiers()) && isStatic(m.getModifiers()))
                .filter(m -> m.getParameterCount() == 0)
                .filter(m -> NamedBiFunction.class.isAssignableFrom(m.getReturnType()))
                .map(function(m -> m.invoke(null)))
                .map(NamedBiFunction.class::cast)
                .filter(f -> f.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
