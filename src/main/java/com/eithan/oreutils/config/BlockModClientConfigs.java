package com.eithan.oreutils.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class BlockModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> Blocked_Words;
    public static final ForgeConfigSpec.ConfigValue<Double> FirstPosX;
    public static final ForgeConfigSpec.ConfigValue<Double> FirstPosZ;
    public static final ForgeConfigSpec.ConfigValue<Double> SecondPosX;
    public static final ForgeConfigSpec.ConfigValue<Double> SecondPosZ;
    public static final ForgeConfigSpec.ConfigValue<Boolean> KickOnSight;
    public static final ForgeConfigSpec.ConfigValue<Boolean> HideBlocked;

    public static final ForgeConfigSpec.ConfigValue<Boolean> AutoWB;

    static {
        BUILDER.push("Configs for Mod");

        Blocked_Words = BUILDER.comment("List of words that are blocked from being used by the mod").defineList("Blocked_Words", List.of(), entry -> true);
        
        FirstPosX = BUILDER.comment("x value of the first position").define("x1: ", 0d);
        FirstPosZ = BUILDER.comment("z value of the first position").define("z1: ", 0d);
        
        SecondPosX = BUILDER.comment("x value of the second position").define("x2: ", 0d);
        SecondPosZ = BUILDER.comment("z value of the second position").define("z2: ", 0d);
        
        KickOnSight = BUILDER.comment("should the mod attempt to kick a player from your plot on sight? ").define("bool1: ", false);
        HideBlocked = BUILDER.comment("should the mod hide blocked usernames? ").define("bool2: ", false);
        AutoWB = BUILDER.comment("do you want to say wb automatically? ").define("bool3: ", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
