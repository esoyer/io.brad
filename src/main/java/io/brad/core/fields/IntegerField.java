package io.brad.core.fields;

import java.util.function.Function;

public final class IntegerField<M> extends NumberField<M, Integer> {

    public IntegerField(String code, Function<M, Integer> fieldAccessor) {
        super(code, fieldAccessor, Integer.class);
    }
}
