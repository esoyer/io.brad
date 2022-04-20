package io.brad.core.fields;

import java.util.function.Function;

public class NumberField<M, T extends Number> extends FieldFromModel<M, T> implements EvaluableNumberField<M, T> {

    public NumberField(String code, Function<M, T> fieldAccessor, Class<T> type) {
        super(code, fieldAccessor, type);
    }
}
