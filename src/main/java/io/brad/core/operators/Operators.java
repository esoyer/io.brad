package io.brad.core.operators;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Objects;

import static io.brad.core.operators.ComparisonOperatorImpl.ofNullsAccepted;
import static io.brad.core.operators.ComparisonOperatorImpl.ofNullsNotAccepted;

public class Operators {

    public static final ComparisonOperator<?> equals
            = ofNullsAccepted("eq", Objects::equals);

    public static final ComparisonOperator<? extends Number> isNumberGreaterThan
            = ofNullsNotAccepted("number_greater_than", (v1, v2) -> compareNumbers(v1, v2) > 0);

    public static final ComparisonOperator<? extends Number> isNumberLessThan
            = ofNullsNotAccepted("number_less_than", (v1, v2) -> compareNumbers(v1, v2) < 0);

    public static final ComparisonOperator<? extends Temporal> isTemporalAfter
            = ofNullsNotAccepted("is_temporal_after", (v1, v2) -> compareTemporals(v1, v2) < 0);

    public static final ComparisonOperator<? extends Temporal> isTemporalBefore
            = ofNullsNotAccepted("is_temporal_before", (v1, v2) -> compareTemporals(v1, v2) > 0);

    public static final ComparisonOperator<String> stringContains
            = ofNullsNotAccepted("string_contains", String::contains);

    public static final ComparisonOperator<String> stringStartsWith
            = ofNullsNotAccepted("string_starts_with", String::startsWith);

    public static final ComparisonOperator<String> stringEndsWith
            = ofNullsNotAccepted("string_ends_with", String::endsWith);

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
