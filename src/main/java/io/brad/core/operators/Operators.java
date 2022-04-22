package io.brad.core.operators;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Objects;

public class Operators {

    public static final ComparisonOperator<?> equals
            = new ComparisonOperatorImpl<>("eq", Objects::equals);

    public static final ComparisonOperator<?> isNull
            = new ComparisonOperatorImpl<>("is_null", (a, b) -> a == null);

    public static final ComparisonOperator<? extends Number> isNumberGreaterThan
            = new ComparisonOperatorImpl<>("number_greater_than", (v1, v2) -> compareNumbers(v1, v2) > 0);

    public static final ComparisonOperator<? extends Number> isNumberLessThan
            = new ComparisonOperatorImpl<>("number_less_than", (v1, v2) -> compareNumbers(v1, v2) < 0);

    public static final ComparisonOperator<? extends Temporal> isTemporalAfter
            = new ComparisonOperatorImpl<>("is_temporal_after", (v1, v2) -> compareTemporals(v1, v2) < 0);

    public static final ComparisonOperator<? extends Temporal> isTemporalBefore
            = new ComparisonOperatorImpl<>("is_temporal_before", (v1, v2) -> compareTemporals(v1, v2) > 0);

    public static final ComparisonOperator<String> stringContains
            = new ComparisonOperatorImpl<>("string_contains", String::contains);

    public static final ComparisonOperator<String> stringStartsWith
            = new ComparisonOperatorImpl<>("string_starts_with", String::startsWith);

    public static final ComparisonOperator<String> stringEndsWith
            = new ComparisonOperatorImpl<>("string_ends_with", String::endsWith);

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
