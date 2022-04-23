package io.brad.core.fields;

import io.brad.core.rules.Rule;

public interface EvaluableBooleanField<M> extends EvaluableField<M, Boolean> {

    default Rule<M> isTrue() {
        return eq(true);
    }

    default Rule<M> isFalse() {
        return eq(false);
    }
}
