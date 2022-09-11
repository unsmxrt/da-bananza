package client.event;

import client.event.impl.Render2DEvent;

public class Test {

    @Subscriber
    public void the(Render2DEvent event) {
        System.out.println("ok");
    }

}
