package io.brad.core.fields;

import java.time.temporal.Temporal;
import java.util.function.Function;

abstract class TemporalField<M, T extends Temporal> extends FieldFromModel<M, T> implements EvaluableTemporalField<M, T> {

    public TemporalField(String code, Function<M, T> fieldAccessor, Class<T> type) {
        super(code, fieldAccessor, type);
    }
}
