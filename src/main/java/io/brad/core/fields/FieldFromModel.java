package io.brad.core.fields;

import java.lang.reflect.Type;
import java.util.function.Function;

public abstract class FieldFromModel<M, T> implements Field<M, T> {

    private final String code;
    private final Function<M, T> fieldAccessor;
    private final Type type;

    FieldFromModel(String code, Function<M, T> fieldAccessor, Type type) {
        this.code = code;
        this.fieldAccessor = fieldAccessor;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    @Override
    public T getValue(M model) {
        return fieldAccessor.apply(model);
    }

    @Override
    public Type getType() {
        return type;
    }
}
