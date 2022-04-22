package io.brad.core.fields;

import java.util.function.Function;

public final class StringField<M> extends FieldFromModel<M, String> implements EvaluableStringField<M> {

    public StringField(String code, Function<M, String> fieldAccessor) {
        super(code, fieldAccessor, String.class);
    }
}
