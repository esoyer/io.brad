package io.brad.core.fields;

import static io.brad.core.functions.BiFunctions.floatMinus;
import static io.brad.core.functions.BiFunctions.floatPlus;

public interface EvaluableFloatField<M> extends EvaluableNumberField<M, Float> {

    @Override
    default FloatField<M> plus(Float value) {
        return new FloatField<>(new BiComputedFieldWithValue<>(this, floatPlus(), value));
    }

    @Override
    default FloatField<M> plus(Field<M, Float> value) {
        return new FloatField<>(new BiComputedFieldWithField<>(this, floatPlus(), value));
    }

    @Override
    default FloatField<M> minus(Float value) {
        return new FloatField<>(new BiComputedFieldWithValue<>(this, floatMinus(), value));
    }

    @Override
    default FloatField<M> minus(Field<M, Float> value) {
        return new FloatField<>(new BiComputedFieldWithField<>(this, floatMinus(), value));
    }
}
