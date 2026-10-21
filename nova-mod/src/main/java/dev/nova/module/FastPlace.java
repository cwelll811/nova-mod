package dev.nova.module;

import dev.nova.mixin.MinecraftClientAccessor;
import net.minecraft.client.MinecraftClient;

public class FastPlace extends Module {
    public FastPlace() { super("FastPlace"); }

    @Override
    public void onTick(MinecraftClient mc) {
        ((MinecraftClientAccessor) mc).nova$setItemUseCooldown(0);
    }
}
