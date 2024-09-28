package com.radimous.vhclienttweaks;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import iskallia.vault.block.entity.DemagnetizerTileEntity;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.init.ModConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DemagnetizerRenderer implements BlockEntityRenderer<DemagnetizerTileEntity> {
    public DemagnetizerRenderer(Context context) {
    }

    private static final Map<Integer, List<Vec3i>> coordLists = new ConcurrentHashMap<>();
    @Override
    public void render(DemagnetizerTileEntity demagnetizerTileEntity, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int i, int i1) {
        if (!Config.DEMAGNETIZER_SPHERE.get()) {
            return;
        }
        LocalPlayer pl = Minecraft.getInstance().player;
        Level world = demagnetizerTileEntity.getLevel();

        if (world == null || pl == null) {
            return;
        }
        if (!pl.isShiftKeyDown()) {
            return;
        }
        ItemStack main = pl.getMainHandItem();
        ItemStack off = pl.getOffhandItem();
        var demagItem = ModBlocks.DEMAGNETIZER_BLOCK.asItem();
        if (!(main.getItem().equals(demagItem)) && !(off.getItem().equals(demagItem))) {
            return;
        }

        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.lines());

        int radius = ModConfigs.MAGNET_CONFIG.getDemagnetizerRadius();
        Vec3i base = new Vec3i(0, 0, 0);
        var coords = coordLists.computeIfAbsent(radius, k -> {
            List<Vec3i> list = new ArrayList<>();
            double radiusSq = radius * radius;
            int iRadius = Mth.ceil(radius);
            for (int x = -iRadius; x <= iRadius; x++) {
                for (int y = -iRadius; y <= iRadius; y++) {
                    for (int z = -iRadius; z <= iRadius; z++) {
                        var curr = new Vec3i(x, y, z);
                        var nextX = new Vec3i(x + (x >= 0 ? 1 : -1), y, z);
                        var nextY = new Vec3i(x, y + (y >= 0 ? 1 : -1), z);
                        var nextZ = new Vec3i(x, y, z + (z >= 0 ? 1 : -1));
                        if (base.distSqr(curr) <= radiusSq &&
                            (base.distSqr(nextX) > radiusSq || base.distSqr(nextY) > radiusSq ||
                                base.distSqr(nextZ) > radiusSq)) {
                            list.add(curr);
                        }
                    }
                }
            }
            return list;
        });
        for (Vec3i coord : coords) {
            float color = 0.2F;
            float opacity = 0.2F;
            LevelRenderer.renderLineBox(poseStack, vertexconsumer, coord.getX(), coord.getY(), coord.getZ(),
                coord.getX() + 1, coord.getY() + 1, coord.getZ() + 1, color, color, color, opacity, color, color, color);
        }

    }

    @Override
    public boolean shouldRender(DemagnetizerTileEntity p_173568_, Vec3 p_173569_) {
        return true;
    }

    @Override
    public boolean shouldRenderOffScreen(DemagnetizerTileEntity p_173568_) {
        return true;
    }
}
