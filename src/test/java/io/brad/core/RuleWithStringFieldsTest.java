package io.brad.core;

import io.brad.core.rules.Rule;
import io.brad.sample.Pojo;
import io.brad.sample.PojoFields;
import org.junit.jupiter.api.Test;

import static io.brad.assertions.RuleAssertion.assertThat;
import static io.brad.sample.PojoFields.field1;
import static io.brad.sample.PojoFields.field2;

public class RuleWithStringFieldsTest {

    private static final Rule<Pojo> field1_eq_value = field1.eq("value");
    private static final Rule<Pojo> field1_eq_field2 = field1.eq(field2);
    private static final Rule<Pojo> field1_not_eq_value = field1.notEq("value");
    private static final Rule<Pojo> field1_not_eq_field2 = field1.notEq(field2);
    private static final Rule<Pojo> field1_is_null = field1.isNull();
    private static final Rule<Pojo> field1_is_not_null = field1.isNotNull();
    private static final Rule<Pojo> field1_in_value1_value2 = field1.in("value1", "value2");
    private static final Rule<Pojo> field1_not_in_value1_value2 = field1.notIn("value1", "value2");
    private static final Rule<Pojo> field1_contains_value = field1.contains("value");
    private static final Rule<Pojo> field1_does_not_contain_value = field1.doesNotContain("value");
    private static final Rule<Pojo> field1_contains_field2 = field1.contains(field2);
    private static final Rule<Pojo> field1_does_not_contain_field2 = field1.doesNotContain(field2);
    private static final Rule<Pojo> field1_starts_with_value = field1.startsWith("value");
    private static final Rule<Pojo> field1_does_not_start_with_value = field1.doesNotStartWith("value");
    private static final Rule<Pojo> field1_starts_with_field2 = field1.startsWith(field2);
    private static final Rule<Pojo> field1_does_not_start_with_field2 = field1.doesNotStartWith(field2);
    private static final Rule<Pojo> field1_ends_with_value = field1.endsWith("value");
    private static final Rule<Pojo> field1_does_not_end_with_value = field1.doesNotEndWith("value");
    private static final Rule<Pojo> field1_ends_with_field2 = field1.endsWith(field2);
    private static final Rule<Pojo> field1_does_not_end_with_field2 = field1.doesNotEndWith(field2);

    private final Pojo pojo = new Pojo();
    private final ModelFields<Pojo> pojoFields = new PojoFields();

    @Test
    void field1_eq_value() {
        assertThat(field1_eq_value, pojoFields)
                .validates(pojo.setField1("value"))
                .doesNotValidate(pojo.setField1("not_value"));
    }

    @Test
    void field1_not_eq_value() {
        assertThat(field1_not_eq_value, pojoFields)
                .validates(pojo.setField1("not_value"))
                .doesNotValidate(pojo.setField1("value"));
    }

    @Test
    void field1_eq_field2() {
        assertThat(field1_eq_field2, pojoFields)
                .validates(pojo.setField1("value").setField2("value"))
                .doesNotValidate(pojo.setField1("value1").setField2("value2"));
    }

    @Test
    void field1_not_eq_field2() {
        assertThat(field1_not_eq_field2, pojoFields)
                .validates(pojo.setField1("value").setField2("not_value"))
                .doesNotValidate(pojo.setField1("value").setField2("value"));
    }

    @Test
    void field1_is_null() {
        assertThat(field1_is_null, pojoFields)
                .validates(pojo.setField1(null))
                .doesNotValidate(pojo.setField1("value"));
    }

    @Test
    void field1_is_not_null() {
        assertThat(field1_is_not_null, pojoFields)
                .validates(pojo.setField1("value"))
                .doesNotValidate(pojo.setField1(null));
    }

    @Test
    void field1_in_value1_value2() {
        assertThat(field1_in_value1_value2, pojoFields)
                .validates(pojo.setField1("value1"))
                .doesNotValidate(pojo.setField1("value3"));
    }

    @Test
    void field1_not_in_value1_value2() {
        assertThat(field1_not_in_value1_value2, pojoFields)
                .validates(pojo.setField1("value3"))
                .doesNotValidate(pojo.setField1("value1"));
    }

    @Test
    void field1_contains_value() {
        assertThat(field1_contains_value, pojoFields)
                .validates(pojo.setField1("contains_value"))
                .doesNotValidate(pojo.setField1("doesnt"));
    }

    @Test
    void field1_does_not_contain_value() {
        assertThat(field1_does_not_contain_value, pojoFields)
                .validates(pojo.setField1("doesnt"))
                .doesNotValidate(pojo.setField1("contains_value"));
    }

    @Test
    void field1_contains_field2() {
        assertThat(field1_contains_field2, pojoFields)
                .validates(pojo.setField1("contains_value").setField2("value"))
                .doesNotValidate(pojo.setField1("doesnt").setField2("value"));
    }

    @Test
    void field1_does_not_contain_field2() {
        assertThat(field1_does_not_contain_field2, pojoFields)
                .validates(pojo.setField1("doesnt").setField2("value"))
                .doesNotValidate(pojo.setField1("contains_value").setField2("value"));
    }

    @Test
    void field1_starts_with_value() {
        assertThat(field1_starts_with_value, pojoFields)
                .validates(pojo.setField1("value_blabla"))
                .doesNotValidate(pojo.setField1("blabla_value"));
    }

    @Test
    void field1_does_not_start_with_value() {
        assertThat(field1_does_not_start_with_value, pojoFields)
                .validates(pojo.setField1("blabla_value"))
                .doesNotValidate(pojo.setField1("value_blabla"));
    }

    @Test
    void field1_starts_with_field2() {
        assertThat(field1_starts_with_field2, pojoFields)
                .validates(pojo.setField1("value_blabla").setField2("value"))
                .doesNotValidate(pojo.setField1("blabla_value").setField2("value"));
    }

    @Test
    void field1_does_not_start_with_field2() {
        assertThat(field1_does_not_start_with_field2, pojoFields)
                .validates(pojo.setField1("blabla_value").setField2("value"))
                .doesNotValidate(pojo.setField1("value_blabla").setField2("value"));
    }

    @Test
    void field1_ends_with_value() {
        assertThat(field1_ends_with_value, pojoFields)
                .validates(pojo.setField1("blabla_value"))
                .doesNotValidate(pojo.setField1("value_blabla"));
    }

    @Test
    void field1_does_not_end_with_value() {
        assertThat(field1_does_not_end_with_value, pojoFields)
                .validates(pojo.setField1("value_blabla"))
                .doesNotValidate(pojo.setField1("blabla_value"));
    }

    @Test
    void field1_ends_with_field2() {
        assertThat(field1_ends_with_field2, pojoFields)
                .validates(pojo.setField1("blabla_value").setField2("value"))
                .doesNotValidate(pojo.setField1("value_blabla").setField2("value"));
    }

    @Test
    void field1_does_not_end_with_field2() {
        assertThat(field1_does_not_end_with_field2, pojoFields)
                .validates(pojo.setField1("value_blabla").setField2("value"))
                .doesNotValidate(pojo.setField1("blabla_value").setField2("value"));
    }
}
