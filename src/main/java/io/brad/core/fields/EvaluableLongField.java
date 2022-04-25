package io.brad.core.fields;

import static io.brad.core.functions.BiFunctions.longMinus;
import static io.brad.core.functions.BiFunctions.longPlus;

public interface EvaluableLongField<M> extends EvaluableNumberField<M, Long> {

    @Override
    default LongField<M> plus(Long value) {
        return new LongField<>(new BiComputedFieldWithValue<>(this, longPlus(), value));
    }

    @Override
    default LongField<M> plus(Field<M, Long> value) {
        return new LongField<>(new BiComputedFieldWithField<>(this, longPlus(), value));
    }

    @Override
    default LongField<M> minus(Long value) {
        return new LongField<>(new BiComputedFieldWithValue<>(this, longMinus(), value));
    }

    @Override
    default LongField<M> minus(Field<M, Long> value) {
        return new LongField<>(new BiComputedFieldWithField<>(this, longMinus(), value));
    }
}
