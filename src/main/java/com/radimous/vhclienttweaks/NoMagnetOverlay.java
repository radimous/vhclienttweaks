package com.radimous.vhclienttweaks;

import com.mojang.blaze3d.systems.RenderSystem;
import iskallia.vault.block.entity.DemagnetizerTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.IIngameOverlay;

public class NoMagnetOverlay {
    private static final ResourceLocation DEMAG = new ResourceLocation("vhclienttweaks", "textures/demag.png");
    public static final IIngameOverlay NO_MAGNET_OVERLAY = (matrices, poseStack, client, width, height) -> {
        Player player = Minecraft.getInstance().player;
        if (player != null && DemagnetizerTileEntity.hasDemagnetizerAround(player)) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, DEMAG);
            GuiComponent.blit(poseStack, 75, height - 14, 0, 0, 12, 12, 12, 12);
        }
    };
}
