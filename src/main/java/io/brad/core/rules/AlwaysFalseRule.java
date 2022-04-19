package io.brad.core.rules;

public class AlwaysFalseRule implements Rule<Object> {

    private static final Rule<?> INSTANCE = new AlwaysFalseRule();

    @SuppressWarnings("unchecked")
    public static <M> Rule<M> getInstance() {
        return (Rule<M>) INSTANCE;
    }

    private AlwaysFalseRule() {
        // private
    }

    @Override
    public boolean validate(Object model) {
        return false;
    }
}
