package com.terra.undertow.worldgen;

import com.terra.block.ModBlocks;
import com.terra.undertow.UnderTow;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> GHOSTLY_PLACED_KEY = registerKey("ghostly_placed");



    private static ResourceKey<PlacedFeature> registerKey(String name) {
        LogUtils.getLogger().debug("REGISTER_KEY: Creating ResourceKey for " + name);
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        LogUtils.getLogger().debug("REGISTER: Registering PlacedFeature with key " + key.location());
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
