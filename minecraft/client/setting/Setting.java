package client.setting;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Setting<T> {
    protected final String name;

    protected T value;

    protected Supplier<Boolean> visibilitySupplier;
    protected Function<T, T> changeListener;

    public Setting(String name) {
        this.name = name;
    }

    public abstract Setting<T> showIf(Supplier<Boolean> supplier);
    public abstract Setting<T> onChange(Function<T, T> listener);

    public abstract void set(T value);

    public T get() {
        return value;
    }


    public String getName() {
        return this.name;
    }
}
