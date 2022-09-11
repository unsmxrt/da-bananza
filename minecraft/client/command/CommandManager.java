package client.command;

import client.command.impl.BindCommand;
import client.command.impl.SettingCommand;
import client.command.impl.ToggleCommand;
import client.event.Subscriber;
import client.event.impl.ChatEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final List<Command> commands = new ArrayList<>();
    public String prefix = ".";

    public CommandManager() {
        commands.add(new BindCommand());
        commands.add(new SettingCommand());
        commands.add(new ToggleCommand());
    }

    @Subscriber
    public void onChat(ChatEvent e) {
        if (!e.message.startsWith(prefix)) return;

        for (Command command : commands) {
            if (!e.message.toLowerCase().startsWith(prefix + command.name)) return;
            command.run(e.message.split("\\+s"));
            break;
        }
        e.cancel();
    }
}
