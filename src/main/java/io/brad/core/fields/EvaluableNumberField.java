package io.brad.core.fields;

import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import static io.brad.core.operators.Operators.*;

public interface EvaluableNumberField<M, T extends Number> extends EvaluableField<M, T> {

    @SuppressWarnings("unchecked")
    default Rule<M> isGreaterThan(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) isNumberGreaterThan, value);
    }

    @SuppressWarnings("unchecked")
    default Rule<M> isGreaterOrEqual(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) isNumberGreaterOrEqual, value);
    }

    @SuppressWarnings("unchecked")
    default Rule<M> isLessThan(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) isNumberLessThan, value);
    }

    @SuppressWarnings("unchecked")
    default Rule<M> isLessOrEqual(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) isNumberLessOrEqual, value);
    }
}
