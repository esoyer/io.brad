package io.brad.core.fields;

public class DelegateFieldImpl<M, T> implements Field<M, T> {

    private final Field<M, T> field;

    public DelegateFieldImpl(Field<M, T> field) {
        this.field = field;
    }

    public Field<M, T> getField() {
        return field;
    }

    @Override
    public String getCode() {
        return field.getCode();
    }

    @Override
    public Class<T> getType() {
        return field.getType();
    }

    @Override
    public T getValue(M model) {
        return field.getValue(model);
    }
}
