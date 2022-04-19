package io.brad.core.functions;

public interface NamedFunction<T, U> {

    String getCode();

    U apply(T t);
}
