package io.brad.core;

import io.brad.core.fields.Field;

import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.jooq.lambda.Unchecked.function;

public interface ModelFields<M> {

    default List<? extends Field<M, ?>> getFields() {
        return Arrays.stream(this.getClass().getFields())
                .filter(ModelFields::isPublicStatic)
                .filter(f -> Field.class.isAssignableFrom(f.getType()))
                .map(function(f -> f.get(null)))
                .map(f -> (Field<M, ?>) f)
                .toList();
    }

    private static boolean isPublicStatic(java.lang.reflect.Field f) {
        return isPublic(f.getModifiers()) && isStatic(f.getModifiers());
    }
}
