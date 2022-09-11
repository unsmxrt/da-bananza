package client.command;

import client.command.impl.BindCommand;
import client.command.impl.ConfigCommand;
import client.command.impl.SettingCommand;
import client.command.impl.ToggleCommand;
import client.event.Subscriber;
import client.event.impl.ChatEvent;
import client.util.ClientUtil;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<Command> commands = new ArrayList<>();
    public String prefix = ".";

    public CommandManager() {
        commands.add(new BindCommand());
        commands.add(new SettingCommand());
        commands.add(new ToggleCommand());
        commands.add(new ConfigCommand());
    }

    @Subscriber
    public void onChat(ChatEvent e) {
        if (!e.message.startsWith(prefix)) return;

        e.cancel();
        for (Command command : commands) {
            final String first = prefix + command.name;

            if(e.message.toLowerCase().startsWith(first.toLowerCase())) {
                command.run(e.message.substring(Math.min(e.message.length(), first.length() + 1)).split(" "));
                return;
            }

            for (String alias : command.getAliases()) {
                final String second = prefix + alias;

                if(e.message.toLowerCase().startsWith(second.toLowerCase())) {
                    command.run(e.message.substring(Math.min(e.message.length(), second.length() + 1)).split(" "));
                    return;
                }
            }
        }
        ClientUtil.chatMsg("Couldn't find a command with such name.");
    }
}
