package io.brad.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FunctionRule<M, T, U> implements Rule<M> {

    private final Field<M, T> field;
    private final NamedFunction<T, U> function;
    private final ComparisonOperator<U> comparisonOperator;
    private final U value;

    @JsonCreator
    public FunctionRule(
            @JsonProperty("field") Field<M, T> field,
            @JsonProperty("function") NamedFunction<T, U> function,
            @JsonProperty("operator") ComparisonOperator<U> comparisonOperator,
            @JsonProperty("value") U value) {
        this.field = field;
        this.function = function;
        this.comparisonOperator = comparisonOperator;
        this.value = value;
    }

    @Override
    public boolean validate(M model) {
        T value = field.getValue(model);
        if (value == null) {
            return false;
        }

        return comparisonOperator.test(function.apply(value), this.value);
    }

    @JsonGetter("field")
    public Field<M, T> getField() {
        return field;
    }

    @JsonGetter("function")
    public NamedFunction<T, U> getFunction() {
        return function;
    }

    @JsonGetter("comparisonOperator")
    public ComparisonOperator<U> getComparisonOperator() {
        return comparisonOperator;
    }

    @JsonGetter("value")
    public U getValue() {
        return value;
    }
}
