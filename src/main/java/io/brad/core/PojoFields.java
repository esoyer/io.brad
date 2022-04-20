package io.brad.core;

import io.brad.core.fields.StringField;

public class PojoFields implements ModelFields<Pojo> {

    public static final StringField<Pojo> field1 = new StringField<>("field1", Pojo::getField1);
}
