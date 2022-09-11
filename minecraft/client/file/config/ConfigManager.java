package client.file.config;

import client.Client;
import client.file.FileHandler;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static org.apache.commons.io.FilenameUtils.removeExtension;

public class ConfigManager implements FileHandler {

    private final List<Config> configs = new ArrayList<>();
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
    }

    @Override
    public void onShutdown() {
        if(configDir == null) return;

    }

    public boolean load(String name, boolean ignoreRender, boolean applyKeybindings) {
        final Config config = configs.stream().filter(config1 -> config1.getName().equals(name))
                .findFirst().orElse(null);

        if(config == null)
            return false;

        config.apply(ignoreRender, applyKeybindings);
        return true;
    }

}
