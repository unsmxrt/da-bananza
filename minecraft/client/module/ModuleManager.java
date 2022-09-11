package client.module;

import client.Client;
import client.event.Subscriber;
import client.event.impl.KeyEvent;
import client.module.move.Sprint;
import client.module.visual.HUD;
import client.setting.Setting;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ModuleManager {

    private final HashMap<Class<? extends Module>, Module> moduleHashmap = new HashMap<>();

    public ModuleManager()  {

    }

    public void init() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Client.INSTANCE.getEventManager().registerSubscription(this);
        initModule(HUD.class);
        initModule(Sprint.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<? extends Module> moduleClass) {
        return (T) moduleHashmap.get(moduleClass);
    }

    public <T> T getModuleByName(String name) {
        for (Module module : moduleHashmap.values()) {
            if (module.getName().equalsIgnoreCase(name)) return (T) module;
        }
        return null;
    }

    public Collection<Module> getModules() {
        return moduleHashmap.values();
    }

    private void initModule(Class<? extends Module> moduleClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Module modInst = moduleClass.getDeclaredConstructor().newInstance();

        moduleHashmap.put(moduleClass, modInst);

        addFields(moduleClass, modInst);
    }

    private void addFields(Class<? extends Module> moduleClass, Module modInst) {
        if (moduleClass.getDeclaredFields().length == 0) return;

        modInst.getSettings().addAll(Arrays.stream(moduleClass.getDeclaredFields())
                .filter((field -> Setting.class.isAssignableFrom(field.getType())))
                .map(field -> {
                    Setting<?> setting = null;
                    try {
                        setting = (Setting<?>) field.get(modInst);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return setting;
                })
                .collect(Collectors.toList()));
    }

    @Subscriber
    public void onKey(KeyEvent e) {
        for (Module module : moduleHashmap.values()) {
            if (module.getKeyBinding() == e.key) {
                module.toggle();
            }
        }
    }
}
