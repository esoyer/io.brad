package io.brad.core.fields;

import java.util.function.Function;

public final class LongField<M> extends NumberField<M, Long> {

    public LongField(String code, Function<M, Long> fieldAccessor) {
        super(code, fieldAccessor, Long.class);
    }
}
