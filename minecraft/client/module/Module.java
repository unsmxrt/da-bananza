package client.module;

import client.Client;
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
    private final Category category;
    private boolean state;

    private boolean registeredSubscription;

    public Module(String name, Category category) {
        this.name = name;
        this.displayName = name;
        this.category = category;
    }

    public void toggle() {
        this.state = !this.state;
        if (this.state) {
            this.onEnable();
            if(!registeredSubscription) {
                Client.INSTANCE.getEventManager().registerSubscription(this);
                registeredSubscription = true;
            } else {
                Client.INSTANCE.getEventManager().resumeSubscription(this);
            }
        }
        else {
            this.onDisable();
            Client.INSTANCE.getEventManager().suspendSubscription(this);
        }
    }

    public void setState(boolean state) {
        if(this.state != state)
            toggle();
    }

    protected void onEnable() {

    }

    protected void onDisable() {

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
