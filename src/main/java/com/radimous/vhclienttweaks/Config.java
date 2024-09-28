package com.radimous.vhclienttweaks;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.BooleanValue PRESS_SHIFT_TO_SEE_RESEARCHED = CLIENT_BUILDER
        .comment("Must press shift to see researched")
        .define("pressShiftToSeeResearched", true);
    public static ForgeConfigSpec.BooleanValue DEMAGNETIZER_SPHERE = CLIENT_BUILDER
        .comment("Enable demagnetizer sphere")
        .define("demagnetizerSphere", false);
    public static ForgeConfigSpec CLIENT_SPEC = CLIENT_BUILDER.build();

}
