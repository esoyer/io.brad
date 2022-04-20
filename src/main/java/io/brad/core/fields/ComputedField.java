package io.brad.core.fields;

import io.brad.core.functions.NamedFunction;

import java.lang.reflect.Type;

public abstract class ComputedField<M, T, U> implements Field<M, U> {

    private final Field<M, T> initialField;
    private final NamedFunction<T, U> function;

    public ComputedField(Field<M, T> initialField, NamedFunction<T, U> function) {
        this.initialField = initialField;
        this.function = function;
    }

    public Field<M, T> getInitialField() {
        return initialField;
    }

    public NamedFunction<T, U> getFunction() {
        return function;
    }

    @Override
    public Type getType() {
        return function.getReturnType();
    }

    @Override
    public U getValue(M model) {
        return function.apply(initialField.getValue(model));
    }
}
