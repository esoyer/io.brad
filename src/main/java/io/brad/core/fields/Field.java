package io.brad.core.fields;

public interface Field<M, T> {

    String getCode();

    T getValue(M model);
}
