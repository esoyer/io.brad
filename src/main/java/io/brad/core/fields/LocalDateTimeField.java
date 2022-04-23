package io.brad.core.fields;

import java.time.LocalDateTime;
import java.util.function.Function;

public final class LocalDateTimeField<M> extends FieldFromModel<M, LocalDateTime> implements EvaluableLocalDateTimeField<M> {

    public LocalDateTimeField(String code, Function<M, LocalDateTime> fieldAccessor) {
        super(code, fieldAccessor, LocalDateTime.class);
    }
}
