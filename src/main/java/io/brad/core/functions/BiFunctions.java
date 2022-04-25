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

    public static NamedBiFunction<Integer, Integer, Integer> integerPlus() {
        return new NamedBiFunctionImpl<>("integer_plus", Integer.class, Integer.class, Integer::sum);
    }

    public static NamedBiFunction<Long, Long, Long> longPlus() {
        return new NamedBiFunctionImpl<>("long_plus", Long.class, Long.class, Long::sum);
    }

    public static NamedBiFunction<Float, Float, Float> floatPlus() {
        return new NamedBiFunctionImpl<>("float_plus", Float.class, Float.class, Float::sum);
    }

    public static NamedBiFunction<Double, Double, Double> doublePlus() {
        return new NamedBiFunctionImpl<>("double_plus", Double.class, Double.class, Double::sum);
    }

    public static NamedBiFunction<Integer, Integer, Integer> integerMinus() {
        return new NamedBiFunctionImpl<>("integer_minus", Integer.class, Integer.class, (a, b) -> a - b);
    }

    public static NamedBiFunction<Long, Long, Long> longMinus() {
        return new NamedBiFunctionImpl<>("long_minus", Long.class, Long.class, (a, b) -> a - b);
    }

    public static NamedBiFunction<Float, Float, Float> floatMinus() {
        return new NamedBiFunctionImpl<>("float_minus", Float.class, Float.class, (a, b) -> a - b);
    }

    public static NamedBiFunction<Double, Double, Double> doubleMinus() {
        return new NamedBiFunctionImpl<>("double_minus", Double.class, Double.class, (a, b) -> a - b);
    }
}
