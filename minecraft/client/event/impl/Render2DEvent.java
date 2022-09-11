package client.event.impl;

import client.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class Render2DEvent extends Event {

    public final float partialTicks;
    public final ScaledResolution scaledResolution;

    public Render2DEvent(float partialTicks, ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }
}
