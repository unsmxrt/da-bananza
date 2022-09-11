package client;

import client.event.EventManager;
import client.event.impl.Render2DEvent;
import client.file.FileHandler;
import client.file.config.Config;
import client.file.config.ConfigManager;
import client.module.ModuleManager;
import client.module.move.Sprint;
import client.module.visual.HUD;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static final Client INSTANCE = new Client();

    private ModuleManager moduleManager;
    private EventManager eventManager;

    private ConfigManager configManager;

    private final String clientName = "Client";

    private final File clientDir = new File(Minecraft.getMinecraft().mcDataDir, clientName);

    private final List<FileHandler> fileHandlers = new ArrayList<>();

    private Client() {

    }

    public void start() {
        try {
            this.moduleManager = new ModuleManager();
            this.eventManager = new EventManager();
            this.configManager = new ConfigManager();

            fileHandlers.add(configManager);

            //keep this at the end
            fileHandlers.forEach(FileHandler::onStartup);
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
