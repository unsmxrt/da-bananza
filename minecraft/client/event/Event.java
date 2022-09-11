package client.event;

public abstract class Event {
    protected boolean cancelled = false;
    protected EventType type;
}
