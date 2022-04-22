package io.brad.core.fields;

import java.util.function.Function;

public final class DoubleField<M> extends NumberField<M, Double> {

    public DoubleField(String code, Function<M, Double> fieldAccessor) {
        super(code, fieldAccessor, Double.class);
    }
}
