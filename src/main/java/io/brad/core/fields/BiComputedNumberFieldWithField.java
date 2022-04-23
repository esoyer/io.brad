package io.brad.core.fields;

import io.brad.core.functions.NamedBiFunction;

public class BiComputedNumberFieldWithField<M, T, U, V extends Number> extends BiComputedFieldWithField<M, T, U, V> implements EvaluableNumberField<M, V> {

    public BiComputedNumberFieldWithField(Field<M, T> initialField, NamedBiFunction<T, U, V> biFunction, Field<M, U> otherField) {
        super(initialField, biFunction, otherField);
    }
}
