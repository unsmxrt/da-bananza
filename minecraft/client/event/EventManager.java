package client.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {

    private final Map<Class<? extends Event>, CopyOnWriteArrayList<EventSubscription>> listeners = new HashMap<>();

    public void registerSubscription(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            final boolean wasAccessible = method.isAccessible();
            method.setAccessible(true);

            if(!method.isAnnotationPresent(Subscriber.class)) continue;

            final Class<?>[] params = method.getParameterTypes();
            if(params.length != 1 || !Event.class.isAssignableFrom(params[0])) continue;

            //noinspection unchecked
            final Class<? extends Event> type = (Class<? extends Event>) params[0];

            listeners.computeIfPresent(type, (__, subs) -> {
                subs.add(new EventSubscription(method, listener, method.isAnnotationPresent(Persistent.class)));
                return subs;
            });

            listeners.computeIfAbsent(type, (__) -> {
                final CopyOnWriteArrayList<EventSubscription> list = new CopyOnWriteArrayList<>();
                list.add(new EventSubscription(method, listener, method.isAnnotationPresent(Persistent.class)));
                return list;
            });

            method.setAccessible(wasAccessible);
        }
    }

    public void unregisterSubscription(Object listener) {
        listeners.values().forEach(subs -> subs.removeIf(sub -> sub.getMethodOwner() == listener));
    }

    public void suspendSubscription(Object listener) {
        listeners.values().forEach(subs -> subs.forEach(sub -> {
            if(sub.getMethodOwner() == listener)
                sub.setActive(false);
        }));
    }

    public void resumeSubscription(Object listener) {
        listeners.values().forEach(subs -> subs.forEach(sub -> {
            if(sub.getMethodOwner() == listener)
                sub.setActive(true);
        }));
    }

    public void fire(Event event) {
        listeners.computeIfPresent(event.getClass(), (type, subs) -> {
            subs.forEach(sub -> {
                try {
                    sub.getMethod().invoke(sub.getMethodOwner(), event);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            return subs;
        });
    }

}
