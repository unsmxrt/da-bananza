package client.event.impl;

import client.event.Event;

public class UpdatePlayerEvent extends Event {
    public double x, y, z;
    public float yaw, pitch;
    public boolean onGround;

    public UpdatePlayerEvent(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
}
