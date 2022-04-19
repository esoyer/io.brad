package io.brad.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NegateRule<M> implements Rule<M> {

    private final Rule<M> ruleToNegate;

    @JsonCreator
    public NegateRule(@JsonProperty("rule") Rule<M> ruleToNegate) {
        this.ruleToNegate = ruleToNegate;
    }

    @JsonGetter("rule")
    @SuppressWarnings("unused")
    public Rule<M> getRuleToNegate() {
        return ruleToNegate;
    }

    @Override
    public boolean validate(M model) {
        return !ruleToNegate.validate(model);
    }
}
