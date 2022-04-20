package io.brad.core.functions;

import java.lang.reflect.Type;

public interface NamedFunction<T, U> {

    Type getReturnType();

    String getCode();

    U apply(T t);
}
