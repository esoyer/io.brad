package io.brad.core.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.brad.core.fields.*;

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
        } else if (field instanceof BiComputedFieldWithValue<?, ?, ?, ?> biComputedFieldWithValue) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "biComputedFieldWithValue");
            jsonGenerator.writeObjectField("initialField", biComputedFieldWithValue.getInitialField());
            jsonGenerator.writeObjectField("biFunction", biComputedFieldWithValue.getBiFunction());
            jsonGenerator.writeObjectField("value", biComputedFieldWithValue.getValue());
            jsonGenerator.writeEndObject();
        } else if (field instanceof BiComputedNumberFieldWithField<?, ?, ?, ?> biComputedFieldWithField) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "biComputedFieldWithField");
            jsonGenerator.writeObjectField("initialField", biComputedFieldWithField.getInitialField());
            jsonGenerator.writeObjectField("biFunction", biComputedFieldWithField.getBiFunction());
            jsonGenerator.writeObjectField("otherField", biComputedFieldWithField.getOtherField());
            jsonGenerator.writeEndObject();
        } else {
            throw new IllegalStateException("Cannot serialize field=" + field);
        }
    }
}
