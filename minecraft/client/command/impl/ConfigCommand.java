package client.command.impl;

import client.Client;
import client.command.Command;
import client.file.config.Config;

import static client.util.ClientUtil.chatMsg;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("config", "Config");
    }

    @Override
    protected void run(String[] args) {
        if (args.length < 1) return;

        switch (args[0].toLowerCase()) {
            case "save":
                if(args.length < 2) {
                    chatMsg("Not enough arguments. Need a name for the config.");
                    return;
                }
                Client.INSTANCE.getConfigManager().save(args[1]);
                chatMsg("Saved config " + args[1]);
                break;
            case "load":
                if(args.length < 2) {
                    chatMsg("Not enough arguments. Need a name for the config.");
                    return;
                }
                if(args.length == 2) {
                    Client.INSTANCE.getConfigManager().load(args[1], false, false);
                    chatMsg("Loaded config " + args[1]);
                } else if(args.length >= 4) {
                    Client.INSTANCE.getConfigManager().load(args[1], Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
                    chatMsg("Loaded config " + args[1]);
                } else {
                    chatMsg("Not enough arguments. Usage: load <name> (ignoreRender) (applyKeybindings)");
                }
                break;
            case "list":
                for (Config config : Client.INSTANCE.getConfigManager().getConfigs()) {
                    chatMsg(config.getName());
                }
                break;
            case "refresh":
                Client.INSTANCE.getConfigManager().refresh();
                chatMsg("Refreshed configs!");
                break;
            case "delete":
                if(args.length < 2) {
                    chatMsg("Not enough arguments. Need a name for the config.");
                    return;
                }
                Client.INSTANCE.getConfigManager().delete(args[1]);
                chatMsg("Deleted config" + args[1]);
                break;
        }
    }
}
