package io.brad.core.fields;

import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldRule;
import io.brad.core.operators.Operators;
import io.brad.core.rules.Rule;

import java.time.temporal.Temporal;
import java.util.function.Function;

public class TemporalField<M, T extends Temporal> extends FieldImpl<M, T> {

    public TemporalField(String code, Function<M, T> fieldAccessor) {
        super(code, fieldAccessor);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isAfter(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) Operators.isTemporalAfter, value);
    }

    public Rule<M> isAfterOrEqual(T value) {
        return isBefore(value).negate();
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isBefore(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) Operators.isTemporalBefore, value);
    }

    public Rule<M> isBeforeOrEqual(T value) {
        return isAfter(value).negate();
    }
}
