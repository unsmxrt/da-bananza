package client.event.impl;

import client.event.Event;

public class UpdatePlayerEvent extends Event {
    public double x, y, z;
    public float yaw, pitch;
    public boolean onGround;

    public UpdatePlayerEvent() {

    }
}
