package dev.nova.gui;

import dev.nova.module.Module;
import dev.nova.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClickGui extends Screen {
    public ClickGui() { super(Text.literal("Nova")); }

    private static Text label(Module m) {
        return Text.literal(m.getName() + ": " + (m.isEnabled() ? "\u00a7aON" : "\u00a7cOFF"));
    }

    @Override
    protected void init() {
        int y = 45;
        for (Module m : ModuleManager.all()) {
            addDrawableChild(ButtonWidget.builder(label(m), btn -> {
                m.toggle();
                btn.setMessage(label(m));
            }).dimensions(width / 2 - 70, y, 140, 20).build());
            y += 24;
        }
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        super.render(ctx, mouseX, mouseY, delta);
        ctx.drawCenteredTextWithShadow(textRenderer, "Nova - ClickGUI", width / 2, 22, 0x55FFFF);
    }

    @Override
    public boolean shouldPause() { return false; }
}
