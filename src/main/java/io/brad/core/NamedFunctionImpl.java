package io.brad.core;

import java.util.function.Function;

public class NamedFunctionImpl<T, U> implements NamedFunction<T, U> {

    private final String code;
    private final Function<T, U> function;

    public NamedFunctionImpl(String code, Function<T, U> function) {
        this.code = code;
        this.function = function;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public U apply(T t) {
        return function.apply(t);
    }
}
