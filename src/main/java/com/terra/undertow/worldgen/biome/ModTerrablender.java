package com.terra.undertow.worldgen.biome;

import com.terra.undertow.UnderTow;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion( ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, "overworld"), 5));
    }
}