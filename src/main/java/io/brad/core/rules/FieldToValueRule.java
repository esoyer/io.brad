package io.brad.core.rules;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.brad.core.fields.Field;
import io.brad.core.operators.ComparisonOperator;

import static io.brad.core.operators.Operators.isNull;

public class FieldToValueRule<M, T> implements Rule<M> {

    private final Field<M, T> field;
    private final ComparisonOperator<T> comparisonOperator;
    private final T value;

    public FieldToValueRule(Field<M, T> field, ComparisonOperator<T> comparisonOperator, T value) {
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
