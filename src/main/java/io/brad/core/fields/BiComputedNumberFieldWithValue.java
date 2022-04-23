package io.brad.core.fields;

import io.brad.core.functions.NamedBiFunction;

public class BiComputedNumberFieldWithValue<M, T, U, V extends Number> extends BiComputedFieldWithValue<M, T, U, V> implements EvaluableNumberField<M, V> {

    public BiComputedNumberFieldWithValue(Field<M, T> initialField, NamedBiFunction<T, U, V> biFunction, U value) {
        super(initialField, biFunction, value);
    }
}
