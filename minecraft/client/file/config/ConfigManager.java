package client.file.config;

import client.Client;
import client.file.FileHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllBytes;
import static org.apache.commons.io.FilenameUtils.removeExtension;

public class ConfigManager implements FileHandler {

    private final List<Config> configs = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private File configDir;

    @Override
    public void onStartup() {
        configDir = new File(Client.INSTANCE.getClientDir(), "configs");

        if(!configDir.exists())
            if(!configDir.mkdir())
                throw new IllegalStateException("Couldn't create config directory");

        final File[] configFiles = configDir.listFiles((file, name) -> file.getName().endsWith(".json"));
        if(configFiles == null) return;

        for (File configFile : configFiles) {
            try {
                configs.add(new Config(removeExtension(configFile.getName()), new String(readAllBytes(configFile.toPath()))));
            } catch (IOException ioException) {
                System.err.println("Error while loading config " + removeExtension(configFile.getName()));
                ioException.printStackTrace();
            }
        }
        load("default", false, true);
    }

    @Override
    public void onShutdown() {
        if(configDir == null) return;

        for (Config config : configs) {
            save(config.getName());
        }
        save("default");
    }

    public boolean load(String name, boolean ignoreRender, boolean applyKeybindings) {
        final Config config = configs.stream().filter(config1 -> config1.getName().equals(name))
                .findFirst().orElse(null);

        if(config == null)
            return false;

        config.apply(ignoreRender, applyKeybindings);
        return true;
    }

    public void delete(Config config) {
        final File newFile = new File(configDir, config.getName() + ".json");
        newFile.delete();
        configs.remove(config);
    }

    public void delete(String name) {
        final File newFile = new File(configDir, name + ".json");
        newFile.delete();
        configs.removeIf((conf) -> conf.getName().equals(name));
    }

    public void refresh() {
        configs.clear();
        onStartup();
    }

    public boolean save(String name) {
        Config other;

        if((other = configs.stream().filter(config -> config.getName().equalsIgnoreCase(name)).findFirst().orElse(null)) != null) {
            configs.remove(other);
        }

        final Config config = new Config(name, null);
        config.save(gson);
        try {
            final File newFile = new File(configDir, config.getName() + ".json");
            if(!newFile.exists())
                newFile.createNewFile();
            Files.write(newFile.toPath(), config.getJson().getBytes(UTF_8));
        } catch (Exception exception) {
            System.err.println("Error while saving config " + config.getName());
            exception.printStackTrace();
            return false;
        }
        configs.add(config);
        return true;
    }

    public List<Config> getConfigs() {
        return this.configs;
    }
}
