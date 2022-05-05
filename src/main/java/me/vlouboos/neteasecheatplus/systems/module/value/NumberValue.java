package me.vlouboos.neteasecheatplus.systems.module.value;

public class NumberValue<N extends Number> extends Value<N> {
    private final N minimum;
    private final N maximum;

    public NumberValue(String name, N minimum, N maximum, N value) {
        super(name, value);
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public static class DoubleValue extends NumberValue<Double> {
        public DoubleValue(String name, Double minimum, Double maximum, Double value) {
            super(name, minimum, maximum, value);
        }
    }

    public static class IntegerValue extends NumberValue<Integer> {
        public IntegerValue(String name, Integer minimum, Integer maximum, Integer value) {
            super(name, minimum, maximum, value);
        }
    }
}
