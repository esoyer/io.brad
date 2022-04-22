package io.brad.core.fields;

import java.time.LocalDate;
import java.util.function.Function;

public final class LocalDateField<M> extends TemporalField<M, LocalDate> {

    public LocalDateField(String code, Function<M, LocalDate> fieldAccessor) {
        super(code, fieldAccessor, LocalDate.class);
    }
}
