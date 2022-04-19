package io.brad.core;

public class AlwaysTrueRule implements Rule<Object> {

    public static final Rule<?> INSTANCE = new AlwaysTrueRule();

    @SuppressWarnings("unchecked")
    public static <M> Rule<M> getInstance() {
        return (Rule<M>) INSTANCE;
    }

    private AlwaysTrueRule() {
        // private
    }

    @Override
    public boolean validate(Object model) {
        return true;
    }
}
