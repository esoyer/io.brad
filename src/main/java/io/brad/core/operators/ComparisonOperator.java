package io.brad.core.operators;

public interface ComparisonOperator<T> {

    String getCode();

    boolean test(T value1, T value2);

    boolean acceptsNulls();
}
