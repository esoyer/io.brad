package io.brad.core;

public interface ComparisonOperator<T> {

    String getCode();

    boolean test(T value1, T value2);
}
