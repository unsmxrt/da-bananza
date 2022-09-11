package client.setting;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModeSetting extends Setting<String> {

    private final List<String> options;

    public ModeSetting(String name, String defaultValue, String... range) {
        super(name);
        this.value = defaultValue;
        this.options = Arrays.asList(range);

        if(!options.contains(defaultValue))
            this.value = options.get(0);
    }

    @Override
    public ModeSetting showIf(Supplier<Boolean> supplier) {
        this.visibilitySupplier = supplier;
        return this;
    }

    @Override
    public ModeSetting onChange(Function<String, String> listener) {
        this.changeListener = listener;
        return this;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

    @Override
    public void fromJson(JsonElement element) {
        if(element.isJsonPrimitive())
            set(element.getAsString());
    }

    @Override
    public void set(String value) {
        if(options.contains(value)) {
            if(this.changeListener != null)
                this.value = changeListener.apply(value);
            else this.value = value;
        }
    }

    public void next() {
        int index = options.indexOf(get());

        if(index + 1 >= options.size())
            index = 0;
        else index++;

        set(options.get(index));
    }

    public void prev() {
        int index = options.indexOf(get());

        if(index - 1 < 0)
            index = options.size() - 1;
        else index--;

        set(options.get(index));
    }
}
