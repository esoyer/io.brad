package io.brad.core.rules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.brad.core.fields.Field;
import io.brad.core.operators.ComparisonOperator;
import io.brad.core.operators.Operators;

public class FieldToFieldRule<M, T> implements Rule<M> {

    private final Field<M, T> field1;
    private final ComparisonOperator<T> comparisonOperator;
    private final Field<M, T> field2;

    @JsonCreator
    public FieldToFieldRule(
            @JsonProperty("field1") Field<M, T> field1,
            @JsonProperty("operator") ComparisonOperator<T> comparisonOperator,
            @JsonProperty("field2") Field<M, T> field2) {
        this.field1 = field1;
        this.comparisonOperator = comparisonOperator;
        this.field2 = field2;
    }

    @Override
    public boolean validate(M model) {
        T field1Value = field1.getValue(model);
        if (comparisonOperator != Operators.isNull && field1Value == null) {
            return false;
        }

        return comparisonOperator.test(field1Value, field2.getValue(model));
    }

    @JsonGetter("field1")
    public Field<M, T> getField1() {
        return field1;
    }

    @JsonGetter("operator")
    public ComparisonOperator<T> getComparisonOperator() {
        return comparisonOperator;
    }

    @JsonGetter("field2")
    public Field<M, T> getField2() {
        return field2;
    }
}
