package dev.nova.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Hand;

/** Automatycznie atakuje najblizszego wrogiego moba w zasiegu (gdy cooldown ataku jest naladowany). */
public class AutoKiller extends Module {
    private static final double RANGE = 3.5;

    public AutoKiller() { super("AutoKiller"); }

    @Override
    public void onTick(MinecraftClient mc) {
        ClientPlayerEntity p = mc.player;
        if (p == null || mc.world == null || mc.interactionManager == null) return;
        if (p.getAttackCooldownProgress(0.5f) < 1.0f) return;

        LivingEntity best = null;
        double bestDist = RANGE * RANGE;
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof HostileEntity h) || !h.isAlive()) continue;
            double d = p.squaredDistanceTo(h);
            if (d < bestDist) { bestDist = d; best = h; }
        }
        if (best != null) {
            mc.interactionManager.attackEntity(p, best);
            p.swingHand(Hand.MAIN_HAND);
        }
    }
}
