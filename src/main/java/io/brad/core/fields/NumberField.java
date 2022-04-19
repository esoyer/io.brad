package io.brad.core.fields;

import io.brad.core.operators.ComparisonOperator;
import io.brad.core.rules.FieldRule;
import io.brad.core.rules.Rule;

import java.util.function.Function;

import static io.brad.core.operators.Operators.*;

public class NumberField<M, T extends Number> extends FieldImpl<M, T> {

    public NumberField(String code, Function<M, T> fieldAccessor) {
        super(code, fieldAccessor);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isGreaterThan(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) isNumberGreaterThan, value);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isGreaterOrEqual(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) isNumberGreaterOrEqual, value);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isLessThan(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) isNumberLessThan, value);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isLessOrEqual(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) isNumberLessOrEqual, value);
    }
}
