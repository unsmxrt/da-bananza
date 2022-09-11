package client.event;

import java.lang.reflect.Method;

public class EventSubscription {

    private final Method method;

    private final Object methodOwner;

    private final boolean isPersistent;

    private boolean isActive;

    public EventSubscription(Method method, Object methodOwner, boolean isPersistent) {
        this.method = method;
        this.methodOwner = methodOwner;
        this.isPersistent = isPersistent;
        this.isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPersistent() {
        return isPersistent;
    }

    public Method getMethod() {
        return method;
    }

    public Object getMethodOwner() {
        return methodOwner;
    }
}
