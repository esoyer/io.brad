package io.brad.sample;

import io.brad.core.ModelFields;
import io.brad.core.fields.StringField;

public class PojoFields implements ModelFields<Pojo> {

    public static final StringField<Pojo> field1 = new StringField<>("field1", Pojo::getField1);

    public static final StringField<Pojo> field2 = new StringField<>("field2", Pojo::getField2);
}
