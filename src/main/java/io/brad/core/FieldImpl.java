package io.brad.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import static io.brad.core.Operators.equals;
import static io.brad.core.Operators.isNull;

public abstract class FieldImpl<M, T> implements Field<M, T> {

    private final String code;
    private final Function<M, T> fieldAccessor;

    public FieldImpl(String code, Function<M, T> fieldAccessor) {
        this.code = code;
        this.fieldAccessor = fieldAccessor;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public T getValue(M model) {
        return fieldAccessor.apply(model);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> eq(T value) {
        return new FieldRule<>(this, (ComparisonOperator<T>) equals, value);
    }

    public Rule<M> notEq(T value) {
        return eq(value).negate();
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isNull() {
        return new FieldRule<>(this, (ComparisonOperator<T>) isNull, null);
    }

    public Rule<M> isNotNull() {
        return isNull().negate();
    }

    public Rule<M> in(Collection<? extends T> values) {
        return DSL.matchAny(values.stream().map(this::eq).toList());
    }

    @SafeVarargs
    public final Rule<M> in(T... values) {
        return in(Arrays.asList(values));
    }

    public Rule<M> notIn(Collection<? extends T> values) {
        return in(values).negate();
    }

    @SafeVarargs
    public final Rule<M> notIn(T... values) {
        return notIn(Arrays.asList(values));
    }
}
