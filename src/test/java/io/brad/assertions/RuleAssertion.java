package io.brad.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.brad.core.ModelFields;
import io.brad.core.rules.Rule;
import org.assertj.core.api.AbstractAssert;

public class RuleAssertion<M> extends AbstractAssert<RuleAssertion<M>, Rule<M>> {

    private final ModelFields<M> modelFields;

    private RuleAssertion(Rule<M> rule, ModelFields<M> modelFields) {
        super(rule, RuleAssertion.class);
        this.modelFields = modelFields;
    }

    public static <M> RuleAssertion<M> assertThat(Rule<M> rule, ModelFields<M> modelFields) {
        return new RuleAssertion<>(rule, modelFields);
    }

    public RuleAssertion<M> validates(M model) {
        boolean validated = actual.validate(model);
        if (!validated) {
            failWithMessage("Expected rule to validate model, but didn't");
        }

        boolean validatedByDeser = deserializedRule().validate(model);
        if (!validatedByDeser) {
            failWithMessage("Expected deserialized rule to validate model, but didn't");
        }

        return this;
    }

    public RuleAssertion<M> doesNotValidate(M model) {
        boolean validated = actual.validate(model);
        if (validated) {
            failWithMessage("Expected rule to not validate model, but did");
        }

        boolean validatedByDeser = deserializedRule().validate(model);
        if (validatedByDeser) {
            failWithMessage("Expected deserialized rule to not validate model, but did");
        }

        return this;
    }

    private Rule<M> deserializedRule() {
        String serialize;
        try {
            serialize = actual.serialize();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not serialize rule", e);
        }

        try {
            return Rule.deserialize(serialize, modelFields);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not deserialize rule", e);
        }
    }
}
