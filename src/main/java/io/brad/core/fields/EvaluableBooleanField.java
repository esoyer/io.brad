package io.brad.core.fields;

import io.brad.core.rules.Rule;

interface EvaluableBooleanField<M> extends EvaluableField<M, Boolean> {

    default Rule<M> isTrue() {
        return eq(true);
    }

    default Rule<M> isFalse() {
        return eq(false);
    }
}
