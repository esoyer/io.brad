package io.brad.core.functions;

public class Functions {

    public static NamedFunction<String, Integer> stringSize() {
        return new NamedFunctionImpl<>("string_size", String::length, Integer.class);
    }
}
