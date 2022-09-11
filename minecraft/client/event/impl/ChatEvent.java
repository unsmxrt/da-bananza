package client.event.impl;

import client.event.Event;

public class ChatEvent extends Event {
    public final String message;

    public ChatEvent(String message) {
        this.message = message;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
