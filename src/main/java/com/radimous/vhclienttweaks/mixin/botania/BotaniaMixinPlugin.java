package com.radimous.vhclienttweaks.mixin.botania;

import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class BotaniaMixinPlugin implements IMixinConfigPlugin {
    @Override public void onLoad(String mixinPackage) {

    }

    @Override public String getRefMapperConfig() {
        return "";
    }

    @Override public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return LoadingModList.get().getModFileById("botania") != null && LoadingModList.get().getModFileById("oculus") != null;
    }

    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
