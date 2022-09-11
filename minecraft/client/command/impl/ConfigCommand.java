package client.command.impl;

import client.Client;
import client.command.Command;
import client.file.config.Config;
import client.util.ClientUtil;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("config", "Config");
    }

    @Override
    protected void run(String[] args) {
        if (args.length < 1) return;

        switch (args[0].toLowerCase()) {
            case "save":
                Client.INSTANCE.getConfigManager().save(args[1]);
                break;
            case "load":
                Client.INSTANCE.getConfigManager().load(args[1], Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
                break;
            case "list":
                for (Config config : Client.INSTANCE.getConfigManager().getConfigs()) {
                    ClientUtil.chatMsg(config.getName());
                }
                break;
            case "refresh":
                Client.INSTANCE.getConfigManager().refresh();
                break;
            case "delete":
                Client.INSTANCE.getConfigManager().delete(args[1]);

                break;
        }
    }
}
