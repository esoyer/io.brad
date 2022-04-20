package io.brad.core.functions;

import java.util.Collection;

public class Functions {

    public static final NamedFunction<? extends Collection<?>, Integer> collection_size
            = new NamedFunctionImpl<>("collection_size", Collection::size, Integer.class);

    public static final NamedFunction<? extends String, Integer> string_size
            = new NamedFunctionImpl<>("string_size", String::length, Integer.class);
}
