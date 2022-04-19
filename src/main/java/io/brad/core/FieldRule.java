package io.brad.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import static io.brad.core.Operators.isNull;

public class FieldRule<M, T> implements Rule<M> {

    private final Field<M, T> field;
    private final ComparisonOperator<T> comparisonOperator;
    private final T value;

    @JsonCreator
    public FieldRule(
            @JsonProperty("field") Field<M, T> field,
            @JsonProperty("operator") ComparisonOperator<T> comparisonOperator,
            @JsonProperty("value") T value) {
        this.field = field;
        this.comparisonOperator = comparisonOperator;
        this.value = value;
    }

    @Override
    public boolean validate(M model) {
        T fieldValue = field.getValue(model);
        if (comparisonOperator != isNull && fieldValue == null) {
            return false;
        }

        return comparisonOperator.test(fieldValue, value);
    }

    @JsonGetter("field")
    public Field<M, T> getField() {
        return field;
    }

    @JsonGetter("operator")
    @SuppressWarnings("unused")
    public ComparisonOperator<T> getComparisonOperator() {
        return comparisonOperator;
    }

    @JsonGetter("value")
    public T getValue() {
        return value;
    }
}
