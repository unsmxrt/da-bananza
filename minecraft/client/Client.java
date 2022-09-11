package client;

import client.module.ModuleManager;

import java.lang.reflect.InvocationTargetException;

public class Client {
    public static final Client INSTANCE = new Client();

    private ModuleManager moduleManager;

    public Client() {

    }

    public void start() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.moduleManager = new ModuleManager();
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }
}
