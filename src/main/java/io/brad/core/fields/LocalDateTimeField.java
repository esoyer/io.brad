package io.brad.core.fields;

import java.time.LocalDateTime;
import java.util.function.Function;

public final class LocalDateTimeField<M> extends DelegateFieldImpl<M, LocalDateTime> implements EvaluableLocalDateTimeField<M> {

    public LocalDateTimeField(Field<M, LocalDateTime> field) {
        super(field);
    }

    public LocalDateTimeField(String code, Function<M, LocalDateTime> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, LocalDateTime.class));
    }
}
