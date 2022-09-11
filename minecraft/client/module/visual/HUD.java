package client.module.visual;

import client.Client;
import client.event.Subscriber;
import client.event.impl.Render2DEvent;
import client.event.impl.UpdatePlayerEvent;
import client.module.Category;
import client.module.Module;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

public class HUD extends Module {


    public HUD() {
        super("HUD", Category.VISUAL);
    }

    @Subscriber
    public void onRender2d(Render2DEvent e) {
        final FontRenderer fr = mc.fontRendererObj;

        fr.drawString(Client.INSTANCE.getClientName(), 5, 5, Color.WHITE.getRGB(), true);
    }

    @Subscriber
    public void onUpdatePlayer(UpdatePlayerEvent e) {

    }
}
