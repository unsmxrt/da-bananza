package client.module.visual;

import client.Client;
import client.event.Subscriber;
import client.event.impl.Render2DEvent;
import client.event.impl.UpdatePlayerEvent;
import client.module.Category;
import client.module.Module;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.util.ArrayList;

public class HUD extends Module {

    private final ArrayList<Module> moduleList = new ArrayList<>();

    public HUD() {
        super("HUD", Category.VISUAL);
    }

    @Subscriber
    public void onRender2d(Render2DEvent e) {
        final FontRenderer fr = mc.fontRendererObj;

        fr.drawString(Client.INSTANCE.getClientName(), 5, 5, Color.WHITE.getRGB(), true);


        float yCoord = 0;
        for (Module module : moduleList) {
            final float nameWidth = fr.getStringWidth(module.getDisplayName());

            fr.drawString(module.getDisplayName(), e.scaledResolution.getScaledWidth() - 5 - nameWidth, 5 + yCoord, Color.WHITE.getRGB(), true);
            yCoord += fr.FONT_HEIGHT + 2;
        }
    }

    @Subscriber
    public void onUpdatePlayer(UpdatePlayerEvent e) {
        if (e.isPost()) return;

        moduleList.clear();
        moduleList.addAll(Client.INSTANCE.getModuleManager().getModules());
        moduleList.removeIf(module -> !module.getState());
        moduleList.sort((mod1, mod2) -> mod1.getDisplayName().length() < mod2.getDisplayName().length() ? 1 : -1);
    }
}
