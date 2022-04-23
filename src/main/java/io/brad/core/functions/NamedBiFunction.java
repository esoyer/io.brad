package io.brad.core.functions;

public interface NamedBiFunction<T, U, V> {

    String getCode();

    V apply(T t, U u);

    Class<U> getParameterType();

    Class<V> getReturnType();
}
