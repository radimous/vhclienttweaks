package com.radimous.vhclienttweaks;

import iskallia.vault.init.ModBlocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("vhclienttweaks")
@Mod.EventBusSubscriber(modid = "vhclienttweaks", bus = Mod.EventBusSubscriber.Bus.MOD)
public class Vhclienttweaks {

    public Vhclienttweaks() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
    }

    @SubscribeEvent
    public static void ohRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.DEMAGNETIZER_TILE_ENTITY, DemagnetizerRenderer::new);

    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        OverlayRegistry.registerOverlayBottom(NoMagnetOverlay.class.getSimpleName(), NoMagnetOverlay.NO_MAGNET_OVERLAY);
    }

}
