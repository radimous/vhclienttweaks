package com.radimous.vhclienttweaks.mixin.botania;

import com.mojang.blaze3d.vertex.PoseStack;
import com.radimous.vhclienttweaks.RenderFluidTank;
import net.coderbot.iris.Iris;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.common.block.tile.mana.TilePool;

@Mixin(RenderTilePool.class)
public class RenderTilePoolMixin {
    @Inject(method = "render(Lvazkii/botania/common/block/tile/mana/TilePool;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 2), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void shaderWorkaround(TilePool pool, float f, PoseStack ms, MultiBufferSource buffers, int light, int overlay, CallbackInfo ci, boolean fab, int mana, int cap, float waterLevel, float s, float v, float w) {
        if (Iris.getCurrentPack().isPresent()) {
            ms.translate(-0.5, -1.0F - (0.43F - waterLevel), -0.5);
            RenderFluidTank.renderManaInPool(ms, buffers, light, overlay, MiscellaneousModels.INSTANCE.manaWater.sprite());
        }
    }
}
