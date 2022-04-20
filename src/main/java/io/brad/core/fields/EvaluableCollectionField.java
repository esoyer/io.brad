package io.brad.core.fields;

import io.brad.core.functions.Functions;
import io.brad.core.functions.NamedFunction;
import io.brad.core.operators.ComparisonOperator;
import io.brad.core.operators.Operators;
import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import java.util.Collection;
import java.util.List;

interface EvaluableCollectionField<M, T, C extends Collection<T>> extends EvaluableField<M, C> {

    @SuppressWarnings("unchecked")
    default Rule<M> containsAny(C value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<C>) Operators.containsAny, value);
    }

    @SuppressWarnings("unchecked")
    default Rule<M> containsAll(C value) {
        return new FieldToValueRule<>(this, (ComparisonOperator<C>) Operators.containsAll, value);
    }

    @SuppressWarnings("unchecked")
    default Rule<M> contains(T value) {
        return containsAll((C) List.of(value));
    }

    @SuppressWarnings("unchecked")
    default Rule<M> isEmpty() {
        return new FieldToValueRule<>(this, (ComparisonOperator<C>) Operators.isEmpty, null);
    }

    default Rule<M> isNotEmpty() {
        return isEmpty().negate();
    }

    @SuppressWarnings("unchecked")
    default EvaluableNumberField<M, Integer> size() {
        return new ComputedNumberField<>(this, (NamedFunction<C, Integer>) Functions.collection_size);
    }

    default Rule<M> hasSize(int size) {
        return size().eq(size);
    }
}
