package io.brad.core.fields;

import io.brad.core.functions.NamedBiFunction;

public abstract class BiComputedFieldWithValue<M, T, U, V> implements Field<M, V> {

    private final Field<M, T> initialField;
    private final NamedBiFunction<T, U, V> biFunction;
    private final U value;

    public BiComputedFieldWithValue(Field<M, T> initialField, NamedBiFunction<T, U, V> biFunction, U value) {
        this.initialField = initialField;
        this.biFunction = biFunction;
        this.value = value;
    }

    public Field<M, T> getInitialField() {
        return initialField;
    }

    public NamedBiFunction<T, U, V> getBiFunction() {
        return biFunction;
    }

    public U getValue() {
        return value;
    }

    @Override
    public Class<V> getType() {
        return biFunction.getReturnType();
    }

    @Override
    public V getValue(M model) {
        return biFunction.apply(initialField.getValue(model), value);
    }
}
