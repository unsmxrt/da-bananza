package client.command;

import net.minecraft.client.Minecraft;

public abstract class Command {
    protected String name, displayName;
    protected static final Minecraft mc = Minecraft.getMinecraft();

    protected final String[] aliases;

    protected abstract void run(String[] args);

    protected Command(String name, String displayName, String... aliases) {
        this.name = name;
        this.displayName = displayName;
        this.aliases = aliases;
    }

    public String[] getAliases() {
        return aliases;
    }
}
