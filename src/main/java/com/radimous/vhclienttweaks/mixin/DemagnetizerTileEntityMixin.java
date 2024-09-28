package com.radimous.vhclienttweaks.mixin;

import iskallia.vault.block.entity.DemagnetizerTileEntity;
import iskallia.vault.init.ModConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = DemagnetizerTileEntity.class, remap = false)
public class DemagnetizerTileEntityMixin extends BlockEntity {
    public DemagnetizerTileEntityMixin(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return super.getRenderBoundingBox().inflate(ModConfigs.MAGNET_CONFIG.getDemagnetizerRadius());
    }
}
