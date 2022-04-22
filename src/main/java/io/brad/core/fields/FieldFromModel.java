package io.brad.core.fields;

import java.util.function.Function;

public abstract class FieldFromModel<M, T> implements Field<M, T> {

    private final String code;
    private final Function<M, T> fieldAccessor;
    private final Class<T> type;

    FieldFromModel(String code, Function<M, T> fieldAccessor, Class<T> type) {
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
    public Class<T> getType() {
        return type;
    }
}
