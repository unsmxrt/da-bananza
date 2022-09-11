package client.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.minecraft.util.MathHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, boolean value) {
        super(name);
        this.value = value;
    }

    @Override
    public BooleanSetting showIf(Supplier<Boolean> supplier) {
        this.visibilitySupplier = supplier;
        return this;
    }

    @Override
    public BooleanSetting onChange(Function<Boolean, Boolean> listener) {
        this.changeListener = listener;
        return this;
    }

    @Override
    public void set(Boolean value) {
        if(changeListener != null)
            this.value = changeListener.apply(value);
        else this.value = value;
    }

    public void toggle() {
        set(!this.value);
    }

    @Override
    public void fromJson(JsonElement element) {
        if(element.isJsonPrimitive())
          set(element.getAsBoolean());
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(get());
    }
}
