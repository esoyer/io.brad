package io.brad.core.fields;

import java.util.function.Function;

public class StringField<M> extends FieldImpl<M, String> {

    public StringField(String code, Function<M, String> fieldAccessor) {
        super(code, fieldAccessor, String.class);
    }
}
