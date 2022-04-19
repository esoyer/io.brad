package io.brad.core.rules;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.brad.core.ModelFields;
import io.brad.core.deserializers.FieldDeserializer;
import io.brad.core.deserializers.NamedFunctionDeserializer;
import io.brad.core.deserializers.OperatorDeserializer;
import io.brad.core.fields.Field;
import io.brad.core.functions.NamedFunction;
import io.brad.core.operators.ComparisonOperator;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static io.brad.core.operators.BooleanOperator.and;
import static io.brad.core.operators.BooleanOperator.or;

@JsonTypeInfo(use = NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BinaryRule.class, name = "binaryRule"),
        @JsonSubTypes.Type(value = FieldRule.class, name = "fieldRule"),
        @JsonSubTypes.Type(value = AlwaysTrueRule.class, name = "alwaysTrueRule"),
        @JsonSubTypes.Type(value = AlwaysFalseRule.class, name = "alwaysFalseRule"),
        @JsonSubTypes.Type(value = NegateRule.class, name = "negateRule"),
        @JsonSubTypes.Type(value = FunctionRule.class, name = "functionRule"),
})
public interface Rule<M> {

    boolean validate(M model);

    default Rule<M> and(Rule<M> rule) {
        return new BinaryRule<>(this, and, rule);
    }

    default Rule<M> or(Rule<M> rule) {
        return new BinaryRule<>(this, or, rule);
    }

    default Rule<M> negate() {
        return new NegateRule<>(this);
    }

    default String serialize() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    static <P> Rule<P> deserialize(String ruleAsJson, ModelFields<P> modelFields) throws JsonProcessingException {
        return new ObjectMapper()
                .registerModule(
                        new SimpleModule()
                                .addDeserializer(Field.class, new FieldDeserializer<>(modelFields))
                                .addDeserializer(ComparisonOperator.class, new OperatorDeserializer())
                                .addDeserializer(NamedFunction.class, new NamedFunctionDeserializer()))
                .readValue(ruleAsJson, new TypeReference<>() {
                });
    }
}
