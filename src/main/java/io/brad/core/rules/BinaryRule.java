package io.brad.core.rules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.brad.core.operators.BooleanOperator;

public class BinaryRule<M> implements Rule<M> {

    private final Rule<M> leftRule;
    private final BooleanOperator booleanOperator;
    private final Rule<M> rightRule;

    @JsonCreator
    public BinaryRule(@JsonProperty("leftRule") Rule<M> leftRule,
                      @JsonProperty("operator") BooleanOperator booleanOperator,
                      @JsonProperty("rightRule") Rule<M> rightRule) {
        this.leftRule = leftRule;
        this.booleanOperator = booleanOperator;
        this.rightRule = rightRule;
    }

    @Override
    public boolean validate(M model) {
        switch (booleanOperator) {
            case and -> {
                return leftRule.validate(model) && rightRule.validate(model);
            }
            case or -> {
                return leftRule.validate(model) || rightRule.validate(model);
            }
            default -> throw new IllegalStateException();
        }
    }

    @JsonGetter("leftRule")
    public Rule<M> getLeftRule() {
        return leftRule;
    }

    @JsonGetter("operator")
    public BooleanOperator getBooleanOperator() {
        return booleanOperator;
    }

    @JsonGetter("rightRule")
    public Rule<M> getRightRule() {
        return rightRule;
    }
}
