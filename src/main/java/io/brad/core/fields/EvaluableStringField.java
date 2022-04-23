package io.brad.core.fields;

import io.brad.core.rules.FieldToFieldRule;
import io.brad.core.rules.FieldToValueRule;
import io.brad.core.rules.Rule;

import static io.brad.core.functions.Functions.stringSize;
import static io.brad.core.operators.Operators.*;

// TODO: add isEmpty / isNotEmpty and test them!
interface EvaluableStringField<M> extends EvaluableField<M, String> {

    default EvaluableNumberField<M, Integer> size() {
        return new ComputedNumberField<>(this, stringSize());
    }

    default Rule<M> contains(String value) {
        return new FieldToValueRule<>(this, stringContains(), value);
    }

    default Rule<M> contains(Field<M, String> value) {
        return new FieldToFieldRule<>(this, stringContains(), value);
    }

    default Rule<M> doesNotContain(String value) {
        return contains(value).negate();
    }

    default Rule<M> doesNotContain(Field<M, String> value) {
        return contains(value).negate();
    }

    default Rule<M> startsWith(String value) {
        return new FieldToValueRule<>(this, stringStartsWith(), value);
    }

    default Rule<M> startsWith(Field<M, String> value) {
        return new FieldToFieldRule<>(this, stringStartsWith(), value);
    }

    default Rule<M> doesNotStartWith(String value) {
        return startsWith(value).negate();
    }

    default Rule<M> doesNotStartWith(Field<M, String> value) {
        return startsWith(value).negate();
    }

    default Rule<M> endsWith(String value) {
        return new FieldToValueRule<>(this, stringEndsWith(), value);
    }

    default Rule<M> endsWith(Field<M, String> value) {
        return new FieldToFieldRule<>(this, stringEndsWith(), value);
    }

    default Rule<M> doesNotEndWith(String value) {
        return endsWith(value).negate();
    }

    default Rule<M> doesNotEndWith(Field<M, String> value) {
        return endsWith(value).negate();
    }
}
