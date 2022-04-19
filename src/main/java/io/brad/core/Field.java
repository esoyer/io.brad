package io.brad.core;

public interface Field<M, T> {

    String getCode();

    T getValue(M model);
}
