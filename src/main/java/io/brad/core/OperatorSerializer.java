package io.brad.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OperatorSerializer extends StdSerializer<ComparisonOperator> {

    public OperatorSerializer() {
        super(ComparisonOperator.class);
    }

    @Override
    public void serialize(ComparisonOperator comparisonOperator, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", comparisonOperator.getCode());
        jsonGenerator.writeEndObject();
    }
}
