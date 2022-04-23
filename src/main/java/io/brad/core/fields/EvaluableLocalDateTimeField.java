package io.brad.core.fields;

import java.time.LocalDateTime;

import static io.brad.core.functions.BiFunctions.localDateTimeAgeAt;

public interface EvaluableLocalDateTimeField<M> extends EvaluableTemporalField<M, LocalDateTime> {

    @Override
    default EvaluableNumberField<M, Long> ageAt(LocalDateTime value) {
        return new BiComputedNumberFieldWithValue<>(this, localDateTimeAgeAt(), value);
    }

    @Override
    default EvaluableNumberField<M, Long> ageAt(Field<M, LocalDateTime> value) {
        return new BiComputedNumberFieldWithField<>(this, localDateTimeAgeAt(), value);
    }
}
