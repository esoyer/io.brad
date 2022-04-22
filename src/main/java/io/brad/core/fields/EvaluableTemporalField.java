package io.brad.core.fields;

import io.brad.core.operators.ComparisonOperator;
import io.brad.core.operators.Operators;
import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import java.time.temporal.Temporal;

interface EvaluableTemporalField<M, T extends Temporal> extends EvaluableField<M, T> {

    @SuppressWarnings("unchecked")
    default Rule<M> isAfter(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) Operators.isTemporalAfter, value);
    }

    default Rule<M> isAfterOrEqual(T value) {
        return isBefore(value).negate();
    }

    @SuppressWarnings("unchecked")
    default Rule<M> isBefore(T value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<T>) Operators.isTemporalBefore, value);
    }

    default Rule<M> isBeforeOrEqual(T value) {
        return isAfter(value).negate();
    }
}
