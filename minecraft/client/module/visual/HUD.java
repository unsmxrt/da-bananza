package client.module.visual;

import client.event.impl.Render2DEvent;
import client.event.impl.UpdatePlayerEvent;
import client.module.Category;
import client.module.Module;
import net.minecraft.client.gui.FontRenderer;

public class HUD extends Module {


    public HUD() {
        super("HUD", Category.VISUAL);
    }

    public void onRender2d(Render2DEvent e) {
        final FontRenderer fr = mc.fontRendererObj;
    }

    public void onUpdatePlayer(UpdatePlayerEvent e) {

    }
}
