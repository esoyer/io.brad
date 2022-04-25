package io.brad.core.fields;

public interface Field<M, T> {

    default String getCode() {
        return null;
    }

    Class<T> getType();

    T getValue(M model);
}
