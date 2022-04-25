package io.brad.core.fields;

import io.brad.core.functions.NamedBiFunction;

public final class BiComputedFieldWithField<M, T, U, V> implements Field<M, V> {

    private final Field<M, T> initialField;
    private final NamedBiFunction<T, U, V> biFunction;
    private final Field<M, U> otherField;

    public BiComputedFieldWithField(Field<M, T> initialField, NamedBiFunction<T, U, V> biFunction, Field<M, U> otherField) {
        this.initialField = initialField;
        this.biFunction = biFunction;
        this.otherField = otherField;
    }

    public Field<M, T> getInitialField() {
        return initialField;
    }

    public NamedBiFunction<T, U, V> getBiFunction() {
        return biFunction;
    }

    public Field<M, U> getOtherField() {
        return otherField;
    }

    @Override
    public Class<V> getType() {
        return biFunction.getReturnType();
    }

    @Override
    public V getValue(M model) {
        return biFunction.apply(initialField.getValue(model), otherField.getValue(model));
    }
}
