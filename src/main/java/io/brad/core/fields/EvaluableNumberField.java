package io.brad.core.fields;

import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import static io.brad.core.operators.Operators.isNumberGreaterThan;
import static io.brad.core.operators.Operators.isNumberLessThan;

interface EvaluableNumberField<M, T extends Number> extends EvaluableField<M, T> {

    @SuppressWarnings("unchecked")
    default Rule<M> isGreaterThan(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) isNumberGreaterThan, value);
    }

    default Rule<M> isGreaterOrEqual(T value) {
        return isLessThan(value).negate();
    }

    @SuppressWarnings("unchecked")
    default Rule<M> isLessThan(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) isNumberLessThan, value);
    }

    default Rule<M> isLessOrEqual(T value) {
        return isGreaterThan(value).negate();
    }
}
