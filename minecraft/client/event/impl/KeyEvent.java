package client.event.impl;

import client.event.Event;

public class KeyEvent extends Event {
    public final int key;

    public KeyEvent(int key) {
        this.key = key;
    }
}
