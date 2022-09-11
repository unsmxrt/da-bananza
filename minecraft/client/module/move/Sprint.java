package client.module.move;

import client.event.impl.UpdatePlayerEvent;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.util.ClientUtil;

public class Sprint extends Module {

    private final BooleanSetting test = new BooleanSetting("idfk", false);

    public Sprint() {
        super("Sprint", Category.MOVE);
    }

    @Override
    public void onEnable() {
        ClientUtil.chatMsg("size " + getSettings().size());
    }

    public void onUpdate(UpdatePlayerEvent e) {
        if (e.isPost()) return;
        if (mc.thePlayer.isCollidedHorizontally) return;
        if (mc.thePlayer.isUsingItem()) return;
        if (mc.thePlayer.moveForward <= 0) return;

        mc.thePlayer.setSprinting(true);
    }
}
