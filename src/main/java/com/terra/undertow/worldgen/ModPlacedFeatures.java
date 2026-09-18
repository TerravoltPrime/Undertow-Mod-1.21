package com.terra.undertow.worldgen;

import com.terra.block.ModBlocks;
import com.terra.undertow.UnderTow;
import net.minecraft.core.Holder;
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
    public static final ResourceKey<PlacedFeature> SOIL_PLACED_KEY = registerKey("soil_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, configuredFeatures.getOrThrow(ModConfiguredFeatures.SOIL_PLACEMENT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                        ModBlocks.SOIL.get()));

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(ModPlacedFeatures.SOIL_PLACED_KEY, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
