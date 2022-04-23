package io.brad.core.operators;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Objects;

import static io.brad.core.operators.ComparisonOperatorImpl.ofNullsAccepted;
import static io.brad.core.operators.ComparisonOperatorImpl.ofNullsNotAccepted;

public class Operators {

    public static <T> ComparisonOperator<T> eq() {
        return ofNullsAccepted("eq", Objects::equals);
    }

    public static <T extends Number> ComparisonOperator<T> isNumberGreaterThan() {
        return ofNullsNotAccepted("number_greater_than", (v1, v2) -> compareNumbers(v1, v2) > 0);
    }

    public static <T extends Number> ComparisonOperator<T> isNumberLessThan() {
        return ofNullsNotAccepted("number_less_than", (v1, v2) -> compareNumbers(v1, v2) < 0);
    }

    public static <T extends Temporal> ComparisonOperator<T> isTemporalAfter() {
        return ofNullsNotAccepted("is_temporal_after", (v1, v2) -> compareTemporals(v1, v2) < 0);
    }

    public static <T extends Temporal> ComparisonOperator<T> isTemporalBefore() {
        return ofNullsNotAccepted("is_temporal_before", (v1, v2) -> compareTemporals(v1, v2) > 0);
    }

    public static ComparisonOperator<String> stringContains() {
        return ofNullsNotAccepted("string_contains", String::contains);
    }

    public static ComparisonOperator<String> stringStartsWith() {
        return ofNullsNotAccepted("string_starts_with", String::startsWith);
    }

    public static ComparisonOperator<String> stringEndsWith() {
        return ofNullsNotAccepted("string_ends_with", String::endsWith);
    }

    private static int compareNumbers(Number v1, Number v2) {
        return BigDecimal.valueOf(v1.doubleValue()).compareTo(BigDecimal.valueOf(v2.doubleValue()));
    }

    private static long compareTemporals(Temporal v1, Temporal v2) {
        return Arrays.stream(ChronoUnit.values())
                .filter(u -> u.isSupportedBy(v1))
                .findFirst()
                .map(u -> v1.until(v2, u))
                .orElseThrow();
    }
}
