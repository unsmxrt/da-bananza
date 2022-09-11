package client.command;

import net.minecraft.client.Minecraft;

public abstract class Command {
    protected String name, displayName;
    protected static final Minecraft mc = Minecraft.getMinecraft();

    protected abstract void run(String[] args);

    protected Command(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}
