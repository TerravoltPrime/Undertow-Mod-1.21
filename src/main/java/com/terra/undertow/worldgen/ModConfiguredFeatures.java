package com.terra.undertow.worldgen;
import com.terra.undertow.worldgen.biome.ModBiomes;

import com.terra.block.ModBlocks;
import com.terra.undertow.UnderTow;
import com.mojang.logging.LogUtils;
import com.terra.undertow.UnderTow;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    // Configured Features -> Placed Features -> Placed inside of world Via BiomeModifiers
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOSTLY_KEY = registerKey("ghostly");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        LogUtils.getLogger().debug("BOOTSTRAP: Registering Configured Features for GHOSTLY_KEY");

        // Add debug logs to ensure this runs only once.
        LogUtils.getLogger().debug("Registering Configured Feature: GHOSTLY_KEY");
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        LogUtils.getLogger().debug("REGISTER_KEY: Creating ResourceKey for " + name);
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        LogUtils.getLogger().debug("REGISTER: Registering ConfiguredFeature with key " + key.location());
                context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
