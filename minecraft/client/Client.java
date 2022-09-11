package client;

import client.event.EventManager;
import client.event.impl.Render2DEvent;
import client.file.FileHandler;
import client.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static final Client INSTANCE = new Client();

    private ModuleManager moduleManager;
    private EventManager eventManager;
    private String clientName;

    private final List<FileHandler> fileHandlers = new ArrayList<>();

    private Client() {

    }

    public void start() {
        try {
            this.moduleManager = new ModuleManager();
            this.eventManager = new EventManager();

            //keep this at the end

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
