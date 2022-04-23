package io.brad.core.fields;

import io.brad.core.functions.BiFunctions;

import java.time.LocalDate;

public interface EvaluableLocalDateField<M> extends EvaluableTemporalField<M, LocalDate> {

    @Override
    default EvaluableNumberField<M, Long> ageAt(LocalDate value) {
        return new BiComputedNumberFieldWithValue<>(this, BiFunctions.localDateAgeAt(), value);
    }

    @Override
    default EvaluableNumberField<M, Long> ageAt(Field<M, LocalDate> value) {
        return new BiComputedNumberFieldWithField<>(this, BiFunctions.localDateAgeAt(), value);
    }
}
