package client.command.impl;

import client.command.Command;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("t", "Toggle");
    }

    @Override
    protected void run(String[] args) {
        if (args.length != 2) return;
    }
}
