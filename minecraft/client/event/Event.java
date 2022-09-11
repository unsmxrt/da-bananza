package client.event;

public abstract class Event {
    private boolean cancelled = false;
    private EventType type;
}
