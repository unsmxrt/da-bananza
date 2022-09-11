package client.command.impl;

import client.command.Command;

public class SettingCommand extends Command {

    public SettingCommand() {
        super("v", "Value");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 3) return;


    }
}
