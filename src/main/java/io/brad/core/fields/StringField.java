package io.brad.core.fields;

import java.util.function.Function;

public final class StringField<M> extends DelegateFieldImpl<M, String> implements EvaluableStringField<M> {

    public StringField(Field<M, String> field) {
        super(field);
    }

    public StringField(String code, Function<M, String> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, String.class));
    }
}
