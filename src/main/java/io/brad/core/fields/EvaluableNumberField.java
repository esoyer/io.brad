package io.brad.core.fields;

import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import static io.brad.core.operators.Operators.isNumberGreaterThan;
import static io.brad.core.operators.Operators.isNumberLessThan;

public interface EvaluableNumberField<M, T extends Number> extends EvaluableField<M, T> {

    EvaluableNumberField<M, T> plus(T value);

    EvaluableNumberField<M, T> plus(Field<M, T> value);

    EvaluableNumberField<M, T> minus(T value);

    EvaluableNumberField<M, T> minus(Field<M, T> value);

    default Rule<M> isGreaterThan(T value) {
        return new FieldToValueRule<>(this, isNumberGreaterThan(), value);
    }

    default Rule<M> isGreaterOrEqual(T value) {
        return isLessThan(value).negate();
    }

    default Rule<M> isLessThan(T value) {
        return new FieldToValueRule<>(this, isNumberLessThan(), value);
    }

    default Rule<M> isLessOrEqual(T value) {
        return isGreaterThan(value).negate();
    }
}
