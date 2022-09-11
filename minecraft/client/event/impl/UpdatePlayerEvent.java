package client.event.impl;

import client.event.Event;
import client.event.EventType;

public class UpdatePlayerEvent extends Event {
    public double x, y, z;
    public float yaw, pitch;
    public boolean onGround;

    public UpdatePlayerEvent(double x, double y, double z, float yaw, float pitch, boolean onGround, EventType type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.type = EventType.PRE;
    }

    public boolean isPre() {
        return this.type == EventType.PRE;
    }

    public boolean isPost() {
        return this.type == EventType.POST;
    }

    public EventType getType() {
        return this.type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
