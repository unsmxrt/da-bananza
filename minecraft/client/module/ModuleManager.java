package client.module;

import client.module.visual.HUD;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;

public class ModuleManager {
    private final HashMap<Class<? extends Module>, Module> moduleHashmap = new HashMap<>();

    public ModuleManager() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        initModule(HUD.class);
    }

    public <T> T getModule(Class<? extends Module> moduleClass) {
        return (T) moduleHashmap.get(moduleClass);
    }

    public Collection<Module> getModules() {
        return moduleHashmap.values();
    }

    private void initModule(Class<? extends Module> moduleClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        moduleHashmap.put(moduleClass, moduleClass.getDeclaredConstructor().newInstance());
    }
}
