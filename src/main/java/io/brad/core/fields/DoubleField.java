package io.brad.core.fields;

import java.util.function.Function;

public final class DoubleField<M> extends DelegateFieldImpl<M, Double> implements EvaluableNumberField<M, Double> {

    public DoubleField(Field<M, Double> field) {
        super(field);
    }

    public DoubleField(String code, Function<M, Double> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, Double.class));
    }
}
