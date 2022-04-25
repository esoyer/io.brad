package io.brad.core.fields;

import java.time.LocalDate;
import java.util.function.Function;

public final class LocalDateField<M> extends DelegateFieldImpl<M, LocalDate> implements EvaluableLocalDateField<M> {

    public LocalDateField(Field<M, LocalDate> field) {
        super(field);
    }

    public LocalDateField(String code, Function<M, LocalDate> fieldAccessor) {
        super(new FieldFromModel<>(code, fieldAccessor, LocalDate.class));
    }
}
