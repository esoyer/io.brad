package io.brad.core.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.brad.core.fields.ComputedField;
import io.brad.core.fields.Field;
import io.brad.core.fields.FieldFromModel;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class FieldSerializer extends StdSerializer<Field> {

    public FieldSerializer() {
        super(Field.class);
    }

    @Override
    public void serialize(Field field, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (field instanceof ComputedField<?, ?, ?> computedField) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "computedField");
            jsonGenerator.writeObjectField("initialField", computedField.getInitialField());
            jsonGenerator.writeObjectField("function", computedField.getFunction());
            jsonGenerator.writeEndObject();
        } else if (field instanceof FieldFromModel<?, ?> fieldFromModel) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "fieldFromModel");
            jsonGenerator.writeObjectField("code", fieldFromModel.getCode());
            jsonGenerator.writeEndObject();
        } else {
            throw new IllegalStateException("Cannot serialize field=" + field);
        }
    }
}
