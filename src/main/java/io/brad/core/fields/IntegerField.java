package io.brad.core.fields;

import java.util.function.Function;

public final class IntegerField<M> extends DelegateFieldImpl<M, Integer> implements EvaluableIntegerField<M> {

    public IntegerField(Field<M, Integer> field) {
        super(field);
    }

    public IntegerField(String code, Function<M, Integer> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, Integer.class));
    }
}
