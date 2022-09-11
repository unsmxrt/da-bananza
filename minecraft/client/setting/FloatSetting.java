package client.setting;

import net.minecraft.util.MathHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class FloatSetting extends Setting<Float> {
    private float increment, min, max;

    public FloatSetting(String name, float value, float increment, float min, float max) {
        super(name);
        this.value = value;
        this.increment = increment;
        this.min = min;
        this.max = max;
    }

    @Override
    public FloatSetting showIf(Supplier<Boolean> supplier) {
        this.visibilitySupplier = supplier;
        return this;
    }

    @Override
    public FloatSetting onChange(Function<Float, Float> listener) {
        this.changeListener = listener;
        return this;
    }

    @Override
    public void set(Float value) {
        if(changeListener != null)
            this.value = MathHelper.clamp_float(changeListener.apply(value), min, max);
        else this.value = MathHelper.clamp_float(value, min, max);
    }

    public float getIncrement() {
        return increment;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}
