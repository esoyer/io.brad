package io.brad.core.functions;

public interface NamedFunction<T, U> {

    Class<U> getReturnType();

    String getCode();

    U apply(T t);
}
