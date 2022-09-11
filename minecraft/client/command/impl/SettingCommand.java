package client.command.impl;

import client.Client;
import client.command.Command;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.FloatSetting;
import client.setting.IntSetting;
import client.setting.Setting;

public class SettingCommand extends Command {

    public SettingCommand() {
        super("v", "Value");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 4) return;

        for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
            if (!module.getName().equalsIgnoreCase(args[1])) return;
            for (Setting setting : module.getSettings()) {
                if (!setting.getName().equalsIgnoreCase(args[2])) return;

                if (setting instanceof BooleanSetting) {
                    setting.set(Boolean.valueOf(args[3]));
                }
                else if (setting instanceof FloatSetting) {
                    setting.set(Float.valueOf(args[3]));
                }
                else if (setting instanceof IntSetting) {
                    setting.set(Integer.valueOf(args[3]));
                }

                break;
            }
            break;
        }
    }
}
