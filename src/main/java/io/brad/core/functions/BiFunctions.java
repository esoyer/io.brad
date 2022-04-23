package io.brad.core.functions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.YEARS;

public class BiFunctions {

    public static NamedBiFunction<LocalDate, LocalDate, Long> localDateAgeAt() {
        return new NamedBiFunctionImpl<>("localdate_age_at", LocalDate.class, Long.class, (v1, v2) -> YEARS.between(v2, v1));
    }

    public static NamedBiFunction<LocalDateTime, LocalDateTime, Long> localDateTimeAgeAt() {
        return new NamedBiFunctionImpl<>("localdatetime_age_at", LocalDateTime.class, Long.class, (v1, v2) -> YEARS.between(v2, v1));
    }
}
