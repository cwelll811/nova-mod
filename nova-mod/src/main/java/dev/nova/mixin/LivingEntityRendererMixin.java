package dev.nova.mixin;

import dev.nova.module.ModuleManager;
import dev.nova.module.Nametags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(method = "hasLabel", at = @At("HEAD"), cancellable = true)
    private void nova$alwaysShowNametag(LivingEntity entity, double distance, CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.get(Nametags.class).isEnabled()
                && entity instanceof PlayerEntity
                && entity != MinecraftClient.getInstance().player) {
            cir.setReturnValue(true);
        }
    }
}
