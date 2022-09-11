package client.file.config;

import client.Client;
import client.module.Category;
import client.module.Module;
import client.setting.Setting;
import com.google.gson.*;
import org.lwjgl.input.Keyboard;

import java.io.File;

public class Config {

    private final String name;

    private String json;

    public Config(String name, String json) {
        this.name = name;
        this.json = json;
    }

    public void apply(boolean ignoreRender, boolean setKeybindings) {
        try {
            if(this.json == null) return;

            final JsonObject object = new JsonParser().parse(this.json).getAsJsonObject();
            for (JsonElement moduleElement : object.getAsJsonArray("modules")) {
                final JsonObject moduleObject = moduleElement.getAsJsonObject();
                Client.INSTANCE.getModuleManager().getModules().stream()
                        .filter(mod -> (mod.getCategory() != Category.VISUAL || !ignoreRender) && mod.getName().equals(moduleObject.get("name").getAsString()))
                        .findFirst().ifPresent(mod -> {
                            mod.setState(moduleObject.get("enabled").getAsBoolean());

                            if(setKeybindings)
                                mod.bindTo(Keyboard.getKeyIndex(moduleObject.get("key").getAsString()));

                            for (JsonElement settingElement : moduleObject.getAsJsonArray("settings")) {
                                final JsonObject setting = settingElement.getAsJsonObject();

                                mod.getSettings().stream()
                                        .filter(setting1 -> setting1.getName().equals(setting.get("name").getAsString()))
                                        .findFirst().ifPresent(s -> s.fromJson(setting.get("value")));
                            }
                        });
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void save(Gson gson) {
        final JsonObject config = new JsonObject();
        final JsonArray modules = new JsonArray();

        for (Module module : Client.INSTANCE.getModuleManager().getModules()) {
            final JsonObject moduleObject = new JsonObject();
            moduleObject.addProperty("name", module.getName());
            moduleObject.addProperty("displayName", module.getDisplayName());
            moduleObject.addProperty("enabled", module.getState());
            moduleObject.addProperty("key", Keyboard.getKeyName(module.getKeyBinding()));

            final JsonArray settings = new JsonArray();
            for (Setting<?> setting : module.getSettings()) {
                final JsonObject settingObject = new JsonObject();
                settingObject.addProperty("name", setting.getName());
                settingObject.add("value", setting.toJson());
                settings.add(settingObject);
            }
            moduleObject.add("settings", settings);
            modules.add(moduleObject);
        }
        config.add("modules", modules);
        this.json = gson.toJson(config);
    }

    public String getJson() {
        return json;
    }

    public String getName() {
        return name;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
