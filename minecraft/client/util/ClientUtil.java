package client.util;

import client.Client;
import net.minecraft.util.ChatComponentText;

public class ClientUtil extends Util {

    public static void chatMsg(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText("[" + Client.INSTANCE.getClientName() + "] " + message));
    }

    public static void log(String message) {
        System.out.println("[" + Client.INSTANCE.getClientName() + "] " + message);
    }
}
