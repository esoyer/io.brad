package io.brad.core.operators;

import java.util.function.BiPredicate;

public class ComparisonOperatorImpl<T> implements ComparisonOperator<T> {

    private final String code;
    private final BiPredicate<T, T> biPredicate;

    public ComparisonOperatorImpl(String code, BiPredicate<T, T> biPredicate) {
        this.code = code;
        this.biPredicate = biPredicate;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public boolean test(T value1, T value2) {
        return biPredicate.test(value1, value2);
    }
}
