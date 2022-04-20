package io.brad.core.fields;

import java.util.function.Function;

public class BooleanField<M> extends FieldFromModel<M, Boolean> implements EvaluableBooleanField<M> {

    public BooleanField(String code, Function<M, Boolean> fieldAccessor) {
        super(code, fieldAccessor, Boolean.class);
    }
}
