package client.module;

import client.setting.Setting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    private final List<Setting<?>> settings = new ArrayList<>();
    private String name, displayName;
    private int keyBinding;
    private Category category;
    private boolean state = false;

    public Module(String name, Category category) {
        this.name = name;
        this.displayName = name;
        this.category = category;
    }

    public void toggle() {
        if (this.state) {
            this.disable();
        }
        else {
            this.enable();
        }
    }

    public void setState(boolean state) {
        if(this.state != state)
            toggle();
    }

    public void enable() {
        this.state = true;
        this.onEnable();
    }

    public void disable() {
        this.state = false;
        this.onDisable();
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public int getKeyBinding() {
        return keyBinding;
    }

    public void bindTo(int key) {
        this.keyBinding = key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean getState() {
        return this.state;
    }

    public List<Setting<?>> getSettings() {
        return this.settings;
    }
}
