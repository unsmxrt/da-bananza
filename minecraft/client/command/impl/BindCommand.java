package client.command.impl;

import client.command.Command;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", "Bind");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 3) return;

    }
}
