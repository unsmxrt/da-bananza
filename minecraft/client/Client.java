package client;

import client.command.CommandManager;
import client.event.EventManager;
import client.file.FileHandler;
import client.file.config.ConfigManager;
import client.module.ModuleManager;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static final Client INSTANCE = new Client();

    private ModuleManager moduleManager;
    private EventManager eventManager;

    private ConfigManager configManager;

    private CommandManager commandManager;

    private final String clientName = "Client";

    private final File clientDir = new File(Minecraft.getMinecraft().mcDataDir, clientName);

    private final List<FileHandler> fileHandlers = new ArrayList<>();

    private Client() {

    }

    public void start() {
        try {
            if(!clientDir.exists())
                if(!clientDir.mkdir())
                    throw new IllegalStateException("Couldn't create client directory");

            this.eventManager = new EventManager();
            this.moduleManager = new ModuleManager();
            this.configManager = new ConfigManager();
            this.commandManager = new CommandManager();

            moduleManager.init();
            eventManager.registerSubscription(commandManager);


            fileHandlers.add(configManager);

            //keep this at the end
            fileHandlers.forEach(FileHandler::onStartup);
            configManager.save("test");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> fileHandlers.forEach(FileHandler::onShutdown)));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public File getClientDir() {
        return clientDir;
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public String getClientName() {
        return this.clientName;
    }
}
