package io.brad.core.fields;

import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import java.time.temporal.Temporal;

import static io.brad.core.operators.Operators.isTemporalAfter;
import static io.brad.core.operators.Operators.isTemporalBefore;

public interface EvaluableTemporalField<M, T extends Temporal> extends EvaluableField<M, T> {

    EvaluableNumberField<M, Long> ageAt(T value);

    EvaluableNumberField<M, Long> ageAt(Field<M, T> value);

    default Rule<M> isAfter(T value) {
        return new FieldToValueRule<>(this, isTemporalAfter(), value);
    }

    default Rule<M> isAfterOrEqual(T value) {
        return isBefore(value).negate();
    }

    default Rule<M> isBefore(T value) {
        return new FieldToValueRule<>(this, isTemporalBefore(), value);
    }

    default Rule<M> isBeforeOrEqual(T value) {
        return isAfter(value).negate();
    }
}
