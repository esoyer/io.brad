package io.brad.core.fields;

import io.brad.core.functions.BiFunctions;

import java.time.LocalDate;

public interface EvaluableLocalDateField<M> extends EvaluableTemporalField<M, LocalDate> {

    @Override
    default LongField<M> ageAt(LocalDate value) {
        return new LongField<>(new BiComputedFieldWithValue<>(this, BiFunctions.localDateAgeAt(), value));
    }

    @Override
    default LongField<M> ageAt(Field<M, LocalDate> value) {
        return new LongField<>(new BiComputedFieldWithField<>(this, BiFunctions.localDateAgeAt(), value));
    }
}
