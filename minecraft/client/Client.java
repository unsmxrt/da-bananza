package client;

import client.event.EventManager;
import client.module.ModuleManager;

import java.lang.reflect.InvocationTargetException;

public class Client {
    public static final Client INSTANCE = new Client();

    private ModuleManager moduleManager;
    private EventManager eventManager;

    public Client() {

    }

    public void start() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.moduleManager = new ModuleManager();
        this.eventManager = new EventManager();
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }
}
