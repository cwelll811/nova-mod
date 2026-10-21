package dev.nova.module;

import net.minecraft.client.MinecraftClient;

public class Sprint extends Module {
    public Sprint() { super("Sprint"); }

    @Override
    public void onTick(MinecraftClient mc) {
        if (mc.player != null && mc.player.input.hasForwardMovement()
                && !mc.player.horizontalCollision && !mc.player.isSneaking()) {
            mc.player.setSprinting(true);
        }
    }
}
