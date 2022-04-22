package io.brad.core.functions;

public class Functions {

    public static final NamedFunction<String, Integer> string_size
            = new NamedFunctionImpl<>("string_size", String::length, Integer.class);
}
