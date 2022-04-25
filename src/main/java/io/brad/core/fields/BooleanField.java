package io.brad.core.fields;

import java.util.function.Function;

public final class BooleanField<M> extends DelegateFieldImpl<M, Boolean> implements EvaluableBooleanField<M> {

    public BooleanField(Field<M, Boolean> field) {
        super(field);
    }

    public BooleanField(String code, Function<M, Boolean> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, Boolean.class));
    }
}
