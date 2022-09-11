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
        if(INSTANCE != null)
            throw new RuntimeException("Client already instantiated!");
    }

    public void start() {
        try {
            moduleManager = new ModuleManager();
            eventManager = new EventManager();
            eventManager.registerSubscription(new Test());
            eventManager.fire(new Render2DEvent());
        } catch (Exception levzHasAutism) {
            System.err.println("couldnt load client so sad");
            levzHasAutism.printStackTrace();
        }
    }
}
