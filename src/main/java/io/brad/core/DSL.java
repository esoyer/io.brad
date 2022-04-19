package io.brad.core;

import io.brad.core.rules.AlwaysFalseRule;
import io.brad.core.rules.AlwaysTrueRule;
import io.brad.core.rules.Rule;

import java.util.Arrays;
import java.util.Collection;

public final class DSL {

    private DSL() {
        // static
    }

    public static <M> Rule<M> when(Rule<M> rule) {
        return rule;
    }

    public static <M> Rule<M> matchAny(Collection<? extends Rule<M>> rules) {
        Rule<M> result = alwaysFalse();

        for (Rule<M> rule : rules) {
            result = result.or(rule);
        }

        return result;
    }

    @SafeVarargs
    public static <M> Rule<M> matchAny(Rule<M>... rules) {
        return matchAny(Arrays.asList(rules));
    }

    @SafeVarargs
    public static <M> Rule<M> matchAll(Rule<M>... rules) {
        return matchAny(Arrays.stream(rules).map(Rule::negate).toList()).negate();
    }

    public static <M> Rule<M> alwaysTrue() {
        return AlwaysTrueRule.getInstance();
    }

    public static <M> Rule<M> alwaysFalse() {
        return AlwaysFalseRule.getInstance();
    }
}
