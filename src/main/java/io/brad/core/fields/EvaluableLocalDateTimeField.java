package io.brad.core.fields;

import java.time.LocalDateTime;

import static io.brad.core.functions.BiFunctions.localDateTimeAgeAt;

public interface EvaluableLocalDateTimeField<M> extends EvaluableTemporalField<M, LocalDateTime> {

    @Override
    default LongField<M> ageAt(LocalDateTime value) {
        return new LongField<>(new BiComputedFieldWithValue<>(this, localDateTimeAgeAt(), value));
    }

    @Override
    default LongField<M> ageAt(Field<M, LocalDateTime> value) {
        return new LongField<>(new BiComputedFieldWithField<>(this, localDateTimeAgeAt(), value));
    }
}
