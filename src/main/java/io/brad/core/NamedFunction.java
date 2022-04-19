package io.brad.core;

public interface NamedFunction<T, U> {

    String getCode();

    U apply(T t);
}
