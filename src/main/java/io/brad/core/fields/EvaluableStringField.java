package io.brad.core.fields;

import io.brad.core.functions.NamedFunction;

import static io.brad.core.functions.Functions.string_size;

public interface EvaluableStringField<M> extends EvaluableField<M, String> {

    @SuppressWarnings("unchecked")
    default EvaluableNumberField<M, Integer> size() {
        return new ComputedNumberField<>(this, (NamedFunction<String, Integer>) string_size);
    }
}
