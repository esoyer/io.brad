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
            serializeComputedField(jsonGenerator, computedField);
        } else if (field instanceof FieldFromModel<?, ?> fieldFromModel) {
            serializeFieldFromModel(jsonGenerator, fieldFromModel);
        } else if (field instanceof BiComputedFieldWithValue<?, ?, ?, ?> biComputedFieldWithValue) {
            serializeBiComputedFieldWithValue(jsonGenerator, biComputedFieldWithValue);
        } else if (field instanceof BiComputedFieldWithField<?, ?, ?, ?> biComputedFieldWithField) {
            serializeBiComputedFieldWithField(jsonGenerator, biComputedFieldWithField);
        } else if (field instanceof DelegateFieldImpl<?, ?> delegateField) {
            serializeDelegateField(jsonGenerator, delegateField);
        } else {
            throw new IllegalStateException("Cannot serialize field=" + field);
        }
    }

    private void serializeComputedField(JsonGenerator jsonGenerator, ComputedField<?, ?, ?> computedField) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "computedField");
        jsonGenerator.writeObjectField("initialField", computedField.getInitialField());
        jsonGenerator.writeObjectField("function", computedField.getFunction());
        jsonGenerator.writeEndObject();
    }

    private void serializeFieldFromModel(JsonGenerator jsonGenerator, FieldFromModel<?, ?> fieldFromModel) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "fieldFromModel");
        jsonGenerator.writeObjectField("code", fieldFromModel.getCode());
        jsonGenerator.writeEndObject();
    }

    private void serializeBiComputedFieldWithValue(JsonGenerator jsonGenerator, BiComputedFieldWithValue<?, ?, ?, ?> biComputedFieldWithValue) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "biComputedFieldWithValue");
        jsonGenerator.writeObjectField("initialField", biComputedFieldWithValue.getInitialField());
        jsonGenerator.writeObjectField("biFunction", biComputedFieldWithValue.getBiFunction());
        jsonGenerator.writeObjectField("value", biComputedFieldWithValue.getValue());
        jsonGenerator.writeEndObject();
    }

    private void serializeBiComputedFieldWithField(JsonGenerator jsonGenerator, BiComputedFieldWithField<?, ?, ?, ?> biComputedFieldWithField) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "biComputedFieldWithField");
        jsonGenerator.writeObjectField("initialField", biComputedFieldWithField.getInitialField());
        jsonGenerator.writeObjectField("biFunction", biComputedFieldWithField.getBiFunction());
        jsonGenerator.writeObjectField("otherField", biComputedFieldWithField.getOtherField());
        jsonGenerator.writeEndObject();
    }

    private void serializeDelegateField(JsonGenerator jsonGenerator, DelegateFieldImpl<?, ?> delegateField) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "delegateField");
        jsonGenerator.writeObjectField("field", delegateField.getField());
        jsonGenerator.writeEndObject();
    }
}
