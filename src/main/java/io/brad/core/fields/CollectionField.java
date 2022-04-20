package io.brad.core.fields;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;
import java.util.function.Function;

public class CollectionField<M, T, C extends Collection<T>> extends FieldFromModel<M, C> implements EvaluableCollectionField<M, T, C> {

    public CollectionField(String code, Function<M, C> fieldAccessor, TypeReference<C> typeReference) {
        super(code, fieldAccessor, typeReference.getType());
    }
}
