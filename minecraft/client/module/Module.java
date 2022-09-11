package client.module;

import client.Client;
import client.setting.Setting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.List;

public abstract class Module {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    private List<Setting> settings;
    private String name, displayName;
    private int keybind;
    private Category category;
    private boolean state = false;

    protected Module(String name, Category category) {
        this.name = name;
        this.displayName = name;
        this.category = category;
        this.keybind = Keyboard.KEY_NONE;
    }

    public void toggle() {
        if (this.state) {
            this.disable();
        }
        else {
            this.enable();
        }
    }

    public void enable() {
        Client.INSTANCE.getEventManager().resumeSubscription(this);
        this.state = true;
        this.onEnable();
    }

    public void disable() {
        Client.INSTANCE.getEventManager().suspendSubscription(this);
        this.state = false;
        this.onDisable();
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public boolean getState() {
        return this.state;
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public int getKeybind() {
        return this.keybind;
    }

    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }
}
