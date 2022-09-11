package client.module;

import client.module.move.Sprint;
import client.module.visual.HUD;
import client.setting.Setting;
import net.minecraft.client.Minecraft;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ModuleManager {

    private final HashMap<Class<? extends Module>, Module> moduleHashmap = new HashMap<>();

    public ModuleManager() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        initModule(HUD.class);
        initModule(Sprint.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<? extends Module> moduleClass) {
        return (T) moduleHashmap.get(moduleClass);
    }

    public Collection<Module> getModules() {
        return moduleHashmap.values();
    }

    private void initModule(Class<? extends Module> moduleClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Module modInst = moduleClass.getDeclaredConstructor().newInstance();

        moduleHashmap.put(moduleClass, modInst);

        modInst.getSettings().addAll(Arrays.stream(moduleClass.getDeclaredFields())
                .filter((field -> Setting.class.isAssignableFrom(field.getType())))
                .map(field -> {
                    Setting<?> setting = null;
                    try {
                        field.setAccessible(true);
                        setting = (Setting<?>) field.get(modInst);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return setting;
                })
                .collect(Collectors.toList()));

    }
}
