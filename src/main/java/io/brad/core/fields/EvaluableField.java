package io.brad.core.fields;

import io.brad.core.DSL;
import io.brad.core.operators.Operators;
import io.brad.core.rules.FieldToFieldRule;
import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import java.util.Arrays;
import java.util.Collection;

public interface EvaluableField<M, T> extends Field<M, T> {

    default Rule<M> eq(T value) {
        return new FieldToValueRule<>(this, Operators.eq(), value);
    }

    default Rule<M> eq(Field<M, T> value) {
        return new FieldToFieldRule<>(this, Operators.eq(), value);
    }

    default Rule<M> notEq(Field<M, T> value) {
        return eq(value).negate();
    }

    default Rule<M> notEq(T value) {
        return eq(value).negate();
    }

    default Rule<M> isNull() {
        return new FieldToValueRule<>(this, Operators.eq(), null);
    }

    default Rule<M> isNotNull() {
        return isNull().negate();
    }

    default Rule<M> in(Collection<? extends T> values) {
        return DSL.matchAny(values.stream().map(this::eq).toList());
    }

    default Rule<M> in(T... values) {
        return in(Arrays.asList(values));
    }

    default Rule<M> notIn(Collection<? extends T> values) {
        return in(values).negate();
    }

    default Rule<M> notIn(T... values) {
        return notIn(Arrays.asList(values));
    }
}
