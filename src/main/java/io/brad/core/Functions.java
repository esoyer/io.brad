package io.brad.core;

import java.util.Collection;

public class Functions {

    public static final NamedFunction<? extends Collection<?>, Integer> collection_size
            = new NamedFunctionImpl<>("collection_size", Collection::size);
}
