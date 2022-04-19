package io.brad.core;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static io.brad.core.Functions.collection_size;
import static io.brad.core.Operators.equals;

public class CollectionField<M, T, C extends Collection<? extends T>> extends FieldImpl<M, C> {

    public CollectionField(String code, Function<M, C> fieldAccessor) {
        super(code, fieldAccessor);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> containsAny(C value) {
        return new FieldRule<>(this, (ComparisonOperator<C>) Operators.containsAny, value);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> containsAll(C value) {
        return new FieldRule<>(this, (ComparisonOperator<C>) Operators.containsAll, value);
    }

    @SuppressWarnings("unchecked")
    public Rule<M> contains(T value) {
        return containsAll((C) List.of(value));
    }

    @SuppressWarnings("unchecked")
    public Rule<M> isEmpty() {
        return new FieldRule<>(this, (ComparisonOperator<C>) Operators.isEmpty, null);
    }

    public Rule<M> isNotEmpty() {
        return isEmpty().negate();
    }

    @SuppressWarnings("unchecked")
    public Rule<M> hasSize(int size) {
        return new FunctionRule<>(this,
                (NamedFunction<C, Integer>) collection_size,
                (ComparisonOperator<Integer>) equals,
                size);
    }
}
