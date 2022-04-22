package io.brad.core.operators;

import java.util.function.BiPredicate;

public class ComparisonOperatorImpl<T> implements ComparisonOperator<T> {

    private final String code;
    private final BiPredicate<T, T> biPredicate;
    private final boolean acceptsNulls;

    private ComparisonOperatorImpl(String code, BiPredicate<T, T> biPredicate, boolean acceptsNulls) {
        this.code = code;
        this.biPredicate = biPredicate;
        this.acceptsNulls = acceptsNulls;
    }

    public static <T> ComparisonOperator<T> ofNullsAccepted(String code, BiPredicate<T, T> biPredicate) {
        return new ComparisonOperatorImpl<>(code, biPredicate, true);
    }

    public static <T> ComparisonOperator<T> ofNullsNotAccepted(String code, BiPredicate<T, T> biPredicate) {
        return new ComparisonOperatorImpl<>(code, biPredicate, false);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public boolean test(T value1, T value2) {
        return biPredicate.test(value1, value2);
    }

    @Override
    public boolean acceptsNulls() {
        return acceptsNulls;
    }
}
