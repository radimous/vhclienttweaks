package com.radimous.vhclienttweaks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class RenderFluidTank {


    public static void renderManaInPool(PoseStack stack, MultiBufferSource buffer, int light, int overlay, TextureAtlasSprite tex) {
        VertexConsumer builder = buffer.getBuffer(Sheets.translucentCullBlockSheet());

        Matrix4f matrix = stack.last().pose();
        Matrix3f normal = stack.last().normal();
        float min = 1F / 16F; // 1 pixel for the pool block itself
        float max = 15F / 16F; // 1 pixel for the pool block itself

        var u1 = tex.getU(1);
        var u15 = tex.getU(15);
        var v1 = tex.getV(1);
        var v15 = tex.getV(15);

        builder.vertex(matrix, max, 0, min).color(-1).uv(u1, v1).overlayCoords(overlay).uv2(light).normal(normal, min, max, min).endVertex();
        builder.vertex(matrix, min, 0, min).color(-1).uv(u15, v1).overlayCoords(overlay).uv2(light).normal(normal, min, max, min).endVertex();
        builder.vertex(matrix, min, 0, max).color(-1).uv(u15, v15).overlayCoords(overlay).uv2(light).normal(normal, min, max, min).endVertex();
        builder.vertex(matrix, max, 0, max).color(-1).uv(u1, v15).overlayCoords(overlay).uv2(light).normal(normal, min, max, min).endVertex();
    }
}