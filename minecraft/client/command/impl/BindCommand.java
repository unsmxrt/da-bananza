package client.command.impl;

import client.Client;
import client.command.Command;
import client.module.Module;
import client.util.ClientUtil;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", "Bind");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 3) return;
        for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
            if (!module.getName().equalsIgnoreCase(args[1])) continue;
            int keybind = Keyboard.getKeyIndex(args[2].toUpperCase());

            module.bindTo(keybind);
            ClientUtil.chatMsg("set keybind to " + keybind);
            break;
        }
    }
}
