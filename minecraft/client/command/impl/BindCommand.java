package client.command.impl;

import client.Client;
import client.command.Command;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.FloatSetting;
import client.setting.IntSetting;
import client.setting.Setting;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", "Bind");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 3) return;
        for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
            if (!module.getName().equalsIgnoreCase(args[1])) return;
            module.setKeybind(0);
            break;
        }
    }
}
