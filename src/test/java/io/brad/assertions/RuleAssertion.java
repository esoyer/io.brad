package io.brad.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.brad.core.ModelFields;
import io.brad.core.rules.Rule;
import org.assertj.core.api.AbstractAssert;

public class RuleAssertion<M> extends AbstractAssert<RuleAssertion<M>, Rule<M>> {

    public RuleAssertion(Rule<M> rule) {
        super(rule, RuleAssertion.class);
    }

    public static <M> RuleAssertion<M> assertThat(Rule<M> rule) {
        return new RuleAssertion<>(rule);
    }

    public RuleAssertion<M> validates(M model) {
        boolean validated = actual.validate(model);
        if (!validated) {
            failWithMessage("Expected rule to validate model, but didn't");
        }

        return this;
    }

    public RuleAssertion<M> doesNotValidate(M model) {
        boolean validated = actual.validate(model);
        if (validated) {
            failWithMessage("Expected rule to not validate model, but did");
        }

        return this;
    }

    public RuleAssertion<M> isSerializable() {
        try {
            actual.serialize();
        } catch (JsonProcessingException e) {
            failWithMessage("Expected rule to be serializable, but was not");
        }

        return this;
    }

    public RuleAssertion<M> isDeserializable(ModelFields<M> modelFields) {
        String jsonRule = null;
        try {
            jsonRule = actual.serialize();
        } catch (JsonProcessingException e) {
            failWithMessage("Expected rule to be serializable, but was not");
        }

        Rule<M> deserializedRule = null;
        try {
            deserializedRule = Rule.deserialize(jsonRule, modelFields);
        } catch (JsonProcessingException e) {
            failWithMessage("Expected rule to be deserializable, but was not");
        }

        assertThat(deserializedRule)
                .usingRecursiveComparison()
                .withFailMessage("Expected deserialized rule to be equal to the actual one")
                .isEqualTo(actual);

        return this;
    }
}
