package client.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.MathHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class IntSetting extends Setting<Integer> {

    private final int increment, min, max;

    public IntSetting(String name, int value, int increment, int min, int max) {
        super(name);
        this.value = value;
        this.increment = increment;
        this.min = min;
        this.max = max;
    }

    @Override
    public IntSetting showIf(Supplier<Boolean> supplier) {
        this.visibilitySupplier = supplier;
        return this;
    }

    @Override
    public IntSetting onChange(Function<Integer, Integer> listener) {
        this.changeListener = listener;
        return this;
    }

    @Override
    public void set(Integer value) {
        if(changeListener != null)
            this.value = MathHelper.clamp_int(changeListener.apply(value), min, max);
        else this.value = MathHelper.clamp_int(value, min, max);
    }

    public int getIncrement() {
        return increment;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void fromJson(JsonElement element) {
        if(element.isJsonPrimitive())
            set(element.getAsInt());
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(get());
    }
}
