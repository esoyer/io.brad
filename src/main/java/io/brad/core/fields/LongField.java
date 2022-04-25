package io.brad.core.fields;

import java.util.function.Function;

public final class LongField<M> extends DelegateFieldImpl<M, Long> implements EvaluableLongField<M> {

    public LongField(Field<M, Long> field) {
        super(field);
    }

    public LongField(String code, Function<M, Long> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, Long.class));
    }
}
