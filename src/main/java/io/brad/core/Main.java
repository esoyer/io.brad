package io.brad.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.brad.core.rules.Rule;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        String serialize = PojoFields.field1.size().isGreaterThan(1).serialize();
        Rule<Pojo> deserialize = Rule.deserialize(serialize, new PojoFields());

        System.out.println(deserialize.validate(new Pojo("aaaa")));

        System.out.println(serialize);
    }
}
