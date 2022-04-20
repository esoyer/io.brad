package io.brad.core.fields;

import java.lang.reflect.Type;

public interface Field<M, T> {

    Type getType();

    T getValue(M model);
}
