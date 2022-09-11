package client.command.impl;

import client.command.Command;
import client.util.ClientUtil;

import java.util.Arrays;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", "Bind");
    }

    @Override
    protected void run(String[] args) {
        ClientUtil.chatMsg(Arrays.toString(args));
    }
}
