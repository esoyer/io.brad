package io.brad.core.fields;

import static io.brad.core.functions.BiFunctions.doubleMinus;
import static io.brad.core.functions.BiFunctions.doublePlus;

public interface EvaluableDoubleField<M> extends EvaluableNumberField<M, Double> {

    @Override
    default DoubleField<M> plus(Double value) {
        return new DoubleField<>(new BiComputedFieldWithValue<>(this, doublePlus(), value));
    }

    @Override
    default DoubleField<M> plus(Field<M, Double> value) {
        return new DoubleField<>(new BiComputedFieldWithField<>(this, doublePlus(), value));
    }

    @Override
    default DoubleField<M> minus(Double value) {
        return new DoubleField<>(new BiComputedFieldWithValue<>(this, doubleMinus(), value));
    }

    @Override
    default DoubleField<M> minus(Field<M, Double> value) {
        return new DoubleField<>(new BiComputedFieldWithField<>(this, doubleMinus(), value));
    }
}
