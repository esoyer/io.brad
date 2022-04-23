package io.brad.core.functions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.function.Function;

class NamedFunctionImpl<T, U> implements NamedFunction<T, U> {

    private final String code;
    private final Function<T, U> function;
    private final Class<U> type;

    public NamedFunctionImpl(String code, Function<T, U> function, Class<U> type) {
        this.code = code;
        this.function = function;
        this.type = type;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public U apply(T t) {
        return function.apply(t);
    }

    @Override
    @JsonIgnore
    public Class<U> getReturnType() {
        return type;
    }
}
