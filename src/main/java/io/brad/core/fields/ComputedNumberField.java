package io.brad.core.fields;

import io.brad.core.functions.NamedFunction;

public class ComputedNumberField<M, T, U extends Number> extends ComputedField<M, T, U> implements EvaluableNumberField<M, U> {

    public ComputedNumberField(Field<M, T> initialField, NamedFunction<T, U> function) {
        super(initialField, function);
    }
}
