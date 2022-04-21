package io.brad.core;

import io.brad.core.rules.Rule;
import io.brad.sample.Pojo;
import org.junit.jupiter.api.Test;

import static io.brad.assertions.RuleAssertion.assertThat;
import static io.brad.sample.PojoFields.field1;
import static io.brad.sample.PojoFields.field2;

public class RuleWithStringFieldsTest {

    private static final Rule<Pojo> field1_eq_value = field1.eq("value");
    private static final Rule<Pojo> field1_eq_field2 = field1.eq(field2);

    @Test
    void field1_eq_value() {
        Pojo pojo = new Pojo();

        assertThat(field1_eq_value)
                .validates(pojo.setField1("value"))
                .doesNotValidate(pojo.setField1("not_value"));
    }

    @Test
    void field1_eq_field2() {
        Pojo pojo = new Pojo();

        assertThat(field1_eq_field2)
                .validates(pojo.setField1("value").setField2("value"))
                .doesNotValidate(pojo.setField1("value1").setField2("value2"));
    }
}
