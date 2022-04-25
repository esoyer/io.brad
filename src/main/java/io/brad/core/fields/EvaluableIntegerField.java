package io.brad.core.fields;

import static io.brad.core.functions.BiFunctions.integerMinus;
import static io.brad.core.functions.BiFunctions.integerPlus;

public interface EvaluableIntegerField<M> extends EvaluableNumberField<M, Integer> {

    @Override
    default IntegerField<M> plus(Integer value) {
        return new IntegerField<>(new BiComputedFieldWithValue<>(this, integerPlus(), value));
    }

    @Override
    default IntegerField<M> plus(Field<M, Integer> value) {
        return new IntegerField<>(new BiComputedFieldWithField<>(this, integerPlus(), value));
    }

    @Override
    default IntegerField<M> minus(Integer value) {
        return new IntegerField<>(new BiComputedFieldWithValue<>(this, integerMinus(), value));
    }

    @Override
    default IntegerField<M> minus(Field<M, Integer> value) {
        return new IntegerField<>(new BiComputedFieldWithField<>(this, integerMinus(), value));
    }
}
