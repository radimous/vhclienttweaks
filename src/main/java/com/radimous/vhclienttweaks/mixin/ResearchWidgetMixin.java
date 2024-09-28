package com.radimous.vhclienttweaks.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.radimous.vhclienttweaks.Config;
import iskallia.vault.client.gui.screen.player.legacy.widget.ResearchWidget;
import iskallia.vault.research.ResearchTree;
import iskallia.vault.research.type.Research;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.function.Consumer;

@Mixin(value = ResearchWidget.class, remap = false)
public class ResearchWidgetMixin {
    @Shadow @Final private ResearchTree researchTree;

    @Redirect(method = "renderHover", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", ordinal = 0))
    private void hide(List<Research> instance, Consumer<Research> consumer) {
        instance.stream().filter(research -> !researchTree.isResearched(research)).forEach(consumer);
    }

    @Inject(method = "renderHover", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void addInGreen(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci,
                            List<FormattedCharSequence> tTip, List<Research> preconditions, String tTipRequirement) {
        List<Research> researched = preconditions.stream()
            .filter(research -> researchTree.isResearched(research))
            .toList();

        if (researched.isEmpty()) {
            return;
        }

        if (Config.PRESS_SHIFT_TO_SEE_RESEARCHED.get() && !Screen.hasShiftDown()) {
            tTip.add(
                FormattedCharSequence.forward("Shift to see researched", Style.EMPTY.withColor(ChatFormatting.GRAY)));
            return;
        }

        researched.forEach(
            research -> tTip.add(
                FormattedCharSequence.forward("- " + research.getName(), Style.EMPTY.withColor(ChatFormatting.GREEN)))
        );
    }

}
