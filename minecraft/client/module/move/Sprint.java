package client.module.move;

import client.event.Subscriber;
import client.event.impl.UpdatePlayerEvent;
import client.module.Category;
import client.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.MOVE);
    }

    @Subscriber
    public void onUpdate(UpdatePlayerEvent e) {
        if (e.isPost()) return;
        if (mc.thePlayer.isCollidedHorizontally) return;
        if (mc.thePlayer.isUsingItem()) return;
        if (mc.thePlayer.moveForward <= 0) return;

        mc.thePlayer.setSprinting(true);
    }
}
