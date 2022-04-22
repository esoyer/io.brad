package io.brad.core.fields;

public interface Field<M, T> {

    Class<T> getType();

    T getValue(M model);
}
