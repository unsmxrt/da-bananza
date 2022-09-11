package client;

import client.command.CommandManager;
import client.event.EventManager;
import client.event.impl.Render2DEvent;
import client.module.ModuleManager;

public class Client {
    public static final Client INSTANCE = new Client();

    private ModuleManager moduleManager;
    private EventManager eventManager;
    private CommandManager commandManager;
    private final String clientName = "Bananza";

    private Client() {

    }

    public void start() {
        try {
            this.moduleManager = new ModuleManager();
            this.eventManager = new EventManager();
            this.commandManager = new CommandManager();
            moduleManager.init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public String getClientName() {
        return this.clientName;
    }
}
