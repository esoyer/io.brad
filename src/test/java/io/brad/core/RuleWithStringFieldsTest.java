package io.brad.core;

import io.brad.core.fields.StringField;
import io.brad.core.rules.Rule;
import org.junit.jupiter.api.Test;

import static io.brad.assertions.RuleAssertion.assertThat;

public class RuleWithStringFieldsTest {

    private final RuntimeModel model = new RuntimeModel();

    private final StringField<RuntimeModel> field1 = model.addStringField("field1", "value");
    private final StringField<RuntimeModel> field2 = model.addStringField("field2", "value");

    private final Rule<RuntimeModel> field1_eq_value = field1.eq("value");
    private final Rule<RuntimeModel> field1_eq_field2 = field1.eq(field2);
    private final Rule<RuntimeModel> field1_not_eq_value = field1.notEq("value");
    private final Rule<RuntimeModel> field1_not_eq_field2 = field1.notEq(field2);
    private final Rule<RuntimeModel> field1_is_null = field1.isNull();
    private final Rule<RuntimeModel> field1_is_not_null = field1.isNotNull();
    private final Rule<RuntimeModel> field1_in_value1_value2 = field1.in("value1", "value2");
    private final Rule<RuntimeModel> field1_not_in_value1_value2 = field1.notIn("value1", "value2");
    private final Rule<RuntimeModel> field1_contains_value = field1.contains("value");
    private final Rule<RuntimeModel> field1_does_not_contain_value = field1.doesNotContain("value");
    private final Rule<RuntimeModel> field1_contains_field2 = field1.contains(field2);
    private final Rule<RuntimeModel> field1_does_not_contain_field2 = field1.doesNotContain(field2);
    private final Rule<RuntimeModel> field1_starts_with_value = field1.startsWith("value");
    private final Rule<RuntimeModel> field1_does_not_start_with_value = field1.doesNotStartWith("value");
    private final Rule<RuntimeModel> field1_starts_with_field2 = field1.startsWith(field2);
    private final Rule<RuntimeModel> field1_does_not_start_with_field2 = field1.doesNotStartWith(field2);
    private final Rule<RuntimeModel> field1_ends_with_value = field1.endsWith("value");
    private final Rule<RuntimeModel> field1_does_not_end_with_value = field1.doesNotEndWith("value");
    private final Rule<RuntimeModel> field1_ends_with_field2 = field1.endsWith(field2);
    private final Rule<RuntimeModel> field1_does_not_end_with_field2 = field1.doesNotEndWith(field2);

    @Test
    void field1_eq_value() {
        assertThat(field1_eq_value, model)
                .validates(model.set(field1, "value"))
                .doesNotValidate(model.set(field1, "not_value"));
    }

    @Test
    void field1_not_eq_value() {
        assertThat(field1_not_eq_value, model)
                .validates(model.set(field1, "not_value"))
                .doesNotValidate(model.set(field1, "value"));
    }

    @Test
    void field1_eq_field2() {
        assertThat(field1_eq_field2, model)
                .validates(model.set(field1, "value").set(field2, "value"))
                .doesNotValidate(model.set(field1, "value1").set(field2, "value2"));
    }

    @Test
    void field1_not_eq_field2() {
        assertThat(field1_not_eq_field2, model)
                .validates(model.set(field1, "value").set(field2, "not_value"))
                .doesNotValidate(model.set(field1, "value").set(field2, "value"));
    }

    @Test
    void field1_is_null() {
        assertThat(field1_is_null, model)
                .validates(model.set(field1, null))
                .doesNotValidate(model.set(field1, "value"));
    }

    @Test
    void field1_is_not_null() {
        assertThat(field1_is_not_null, model)
                .validates(model.set(field1, "value"))
                .doesNotValidate(model.set(field1, null));
    }

    @Test
    void field1_in_value1_value2() {
        assertThat(field1_in_value1_value2, model)
                .validates(model.set(field1, "value1"))
                .doesNotValidate(model.set(field1, "value3"));
    }

    @Test
    void field1_not_in_value1_value2() {
        assertThat(field1_not_in_value1_value2, model)
                .validates(model.set(field1, "value3"))
                .doesNotValidate(model.set(field1, "value1"));
    }

    @Test
    void field1_contains_value() {
        assertThat(field1_contains_value, model)
                .validates(model.set(field1, "contains_value"))
                .doesNotValidate(model.set(field1, "doesnt"));
    }

    @Test
    void field1_does_not_contain_value() {
        assertThat(field1_does_not_contain_value, model)
                .validates(model.set(field1, "doesnt"))
                .doesNotValidate(model.set(field1, "contains_value"));
    }

    @Test
    void field1_contains_field2() {
        assertThat(field1_contains_field2, model)
                .validates(model.set(field1, "contains_value").set(field2, "value"))
                .doesNotValidate(model.set(field1, "doesnt").set(field2, "value"));
    }

    @Test
    void field1_does_not_contain_field2() {
        assertThat(field1_does_not_contain_field2, model)
                .validates(model.set(field1, "doesnt").set(field2, "value"))
                .doesNotValidate(model.set(field1, "contains_value").set(field2, "value"));
    }

    @Test
    void field1_starts_with_value() {
        assertThat(field1_starts_with_value, model)
                .validates(model.set(field1, "value_blabla"))
                .doesNotValidate(model.set(field1, "blabla_value"));
    }

    @Test
    void field1_does_not_start_with_value() {
        assertThat(field1_does_not_start_with_value, model)
                .validates(model.set(field1, "blabla_value"))
                .doesNotValidate(model.set(field1, "value_blabla"));
    }

    @Test
    void field1_starts_with_field2() {
        assertThat(field1_starts_with_field2, model)
                .validates(model.set(field1, "value_blabla").set(field2, "value"))
                .doesNotValidate(model.set(field1, "blabla_value").set(field2, "value"));
    }

    @Test
    void field1_does_not_start_with_field2() {
        assertThat(field1_does_not_start_with_field2, model)
                .validates(model.set(field1, "blabla_value").set(field2, "value"))
                .doesNotValidate(model.set(field1, "value_blabla").set(field2, "value"));
    }

    @Test
    void field1_ends_with_value() {
        assertThat(field1_ends_with_value, model)
                .validates(model.set(field1, "blabla_value"))
                .doesNotValidate(model.set(field1, "value_blabla"));
    }

    @Test
    void field1_does_not_end_with_value() {
        assertThat(field1_does_not_end_with_value, model)
                .validates(model.set(field1, "value_blabla"))
                .doesNotValidate(model.set(field1, "blabla_value"));
    }

    @Test
    void field1_ends_with_field2() {
        assertThat(field1_ends_with_field2, model)
                .validates(model.set(field1, "blabla_value").set(field2, "value"))
                .doesNotValidate(model.set(field1, "value_blabla").set(field2, "value"));
    }

    @Test
    void field1_does_not_end_with_field2() {
        assertThat(field1_does_not_end_with_field2, model)
                .validates(model.set(field1, "value_blabla").set(field2, "value"))
                .doesNotValidate(model.set(field1, "blabla_value").set(field2, "value"));
    }
}
