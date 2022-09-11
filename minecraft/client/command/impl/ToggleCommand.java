package client.command.impl;

import client.Client;
import client.command.Command;
import client.module.Module;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("t", "Toggle");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 1) return;

        for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
            if (!module.getName().equalsIgnoreCase(args[0])) continue;
            module.toggle();
            break;
        }
    }
}
