package client.command.impl;

import client.Client;
import client.command.Command;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.FloatSetting;
import client.setting.IntSetting;
import client.setting.Setting;
import client.util.ClientUtil;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", "Bind");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 2) return;
        for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
            if (!module.getName().equalsIgnoreCase(args[0])) continue;
            int keybind = Keyboard.getKeyIndex(args[1].toUpperCase());

            module.bindTo(keybind);
            ClientUtil.chatMsg("set keybind to " + Keyboard.getKeyName(keybind));
            break;
        }
    }
}
