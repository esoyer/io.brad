package io.brad.core.fields;

import java.util.function.Function;

public final class FloatField<M> extends DelegateFieldImpl<M, Float> implements EvaluableFloatField<M> {

    public FloatField(Field<M, Float> field) {
        super(field);
    }

    public FloatField(String code, Function<M, Float> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, Float.class));
    }
}
