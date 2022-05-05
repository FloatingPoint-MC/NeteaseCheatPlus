package me.vlouboos.neteasecheatplus.systems.module.value;

public class Value<V> {
    private final String name;
    private V value;

    public Value(String name, V value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
