package dev.nova;

import dev.nova.gui.ClickGui;
import dev.nova.module.Module;
import dev.nova.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NovaClient implements ClientModInitializer {
    private static KeyBinding openGui;

    @Override
    public void onInitializeClient() {
        openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nova.gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.nova"));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (openGui.wasPressed()) mc.setScreen(new ClickGui());
            if (mc.player == null || mc.world == null) return;
            for (Module m : ModuleManager.all()) if (m.isEnabled()) m.onTick(mc);
        });

        HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            ctx.drawTextWithShadow(mc.textRenderer, "Nova", 4, 4, 0x55FFFF);
            int y = 16;
            for (Module m : ModuleManager.all()) {
                if (!m.isEnabled()) continue;
                ctx.drawTextWithShadow(mc.textRenderer, m.getName(), 4, y, 0xFFFFFF);
                y += 10;
            }
        });
    }
}
