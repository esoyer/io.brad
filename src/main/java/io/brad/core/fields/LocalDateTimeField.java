package io.brad.core.fields;

import java.time.LocalDateTime;
import java.util.function.Function;

public final class LocalDateTimeField<M> extends TemporalField<M, LocalDateTime> {

    public LocalDateTimeField(String code, Function<M, LocalDateTime> fieldAccessor) {
        super(code, fieldAccessor, LocalDateTime.class);
    }
}
