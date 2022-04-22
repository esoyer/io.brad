package io.brad.core.fields;

import java.util.function.Function;

public final class FloatField<M> extends NumberField<M, Float> {

    public FloatField(String code, Function<M, Float> fieldAccessor) {
        super(code, fieldAccessor, Float.class);
    }
}
