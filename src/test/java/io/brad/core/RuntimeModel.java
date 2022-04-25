package io.brad.core;

import io.brad.core.fields.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuntimeModel implements ModelFields<RuntimeModel> {

    private final Map<String, Object> values;
    private final Map<String, Field<RuntimeModel, ?>> fields;

    public RuntimeModel() {
        values = new HashMap<>();
        fields = new HashMap<>();
    }

    public <T> RuntimeModel set(Field<RuntimeModel, T> field, T value) {
        if (!fields.containsValue(field)) {
            throw new IllegalArgumentException(field + " does not exist in model");
        }
        values.put(field.getCode(), value);
        return this;
    }

    public StringField<RuntimeModel> addStringField(String fieldName, String value) {
        return addField(fieldName, value, StringField::new);
    }

    public IntegerField<RuntimeModel> addIntegerField(String fieldName, Integer value) {
        return addField(fieldName, value, IntegerField::new);
    }

    public LongField<RuntimeModel> addLongField(String fieldName, Long value) {
        return addField(fieldName, value, LongField::new);
    }

    public FloatField<RuntimeModel> addFloatField(String fieldName, Float value) {
        return addField(fieldName, value, FloatField::new);
    }

    public DoubleField<RuntimeModel> addDoubleField(String fieldName, Double value) {
        return addField(fieldName, value, DoubleField::new);
    }

    public BooleanField<RuntimeModel> addBooleanField(String fieldName, Boolean value) {
        return addField(fieldName, value, BooleanField::new);
    }

    public LocalDateField<RuntimeModel> addLocalDateField(String fieldName, LocalDate value) {
        return addField(fieldName, value, LocalDateField::new);
    }

    public LocalDateTimeField<RuntimeModel> addLocalDateTimeField(String fieldName, LocalDateTime value) {
        return addField(fieldName, value, LocalDateTimeField::new);
    }

    private <T, V extends Field<RuntimeModel, T>> V addField(String fieldName,
                                                             T value,
                                                             BiFunction<String, Function<RuntimeModel, T>, V> fieldCreator) {
        if (fields.containsKey(fieldName)) {
            throw new IllegalArgumentException("Model contains already field " + fieldName);
        }

        @SuppressWarnings("unchecked")
        Function<RuntimeModel, T> propertyAccessor = r -> (T) values.get(fieldName);
        V field = fieldCreator.apply(fieldName, propertyAccessor);
        values.put(fieldName, value);
        fields.put(fieldName, field);
        return field;
    }

    @Override
    public List<? extends Field<RuntimeModel, ?>> getFields() {
        return fields.values().stream().toList();
    }
}