package io.brad.core;

import java.util.function.Function;

public class BooleanField<M> extends FieldImpl<M, Boolean> {

    public BooleanField(String code, Function<M, Boolean> fieldAccessor) {
        super(code, fieldAccessor);
    }

    public Rule<M> isTrue() {
        return eq(true);
    }

    public Rule<M> isFalse() {
        return eq(false);
    }
}
