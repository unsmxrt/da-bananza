package client;

import client.event.EventManager;
import client.event.Test;
import client.event.impl.Render2DEvent;
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
        this.eventManager.registerSubscription(new Test());
        this.eventManager.fire(new Render2DEvent(1, null)); //todo dont forger
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }
}
