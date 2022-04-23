package io.brad.core.functions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.function.BiFunction;

class NamedBiFunctionImpl<T, U, V> implements NamedBiFunction<T, U, V> {

    private final String code;
    private final Class<U> parameterType;
    private final Class<V> returnType;
    private final BiFunction<T, U, V> biFunction;

    public NamedBiFunctionImpl(String code, Class<U> parameterType, Class<V> returnType, BiFunction<T, U, V> biFunction) {
        this.code = code;
        this.parameterType = parameterType;
        this.returnType = returnType;
        this.biFunction = biFunction;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public V apply(T t, U u) {
        return biFunction.apply(t, u);
    }

    @Override
    @JsonIgnore
    public Class<U> getParameterType() {
        return parameterType;
    }

    @Override
    @JsonIgnore
    public Class<V> getReturnType() {
        return returnType;
    }
}
