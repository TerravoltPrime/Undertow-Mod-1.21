package com.terra.undertow.worldgen;


import com.terra.block.ModBlocks;
import com.terra.undertow.UnderTow;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOIL_PLACEMENT_KEY =
            registerKey("overworld_custom_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // RuleTest for checking if a target block is stone-based
        TagMatchTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        // RuleTest for checking if a target block is dirt-based
        TagMatchTest dirtReplaceables = new TagMatchTest(BlockTags.DIRT);

        // List out all blocks this ore is allowed to replace
        List<OreConfiguration.TargetBlockState> targetList = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.UNDER_SOIL.get().defaultBlockState()),
                OreConfiguration.target(dirtReplaceables, ModBlocks.UNDER_SOIL.get().defaultBlockState())
        );

        // Register the feature with a vein size (e.g., 9 blocks per vein)
        context.register(SOIL_PLACEMENT_KEY, new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(targetList, 9)
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name));
    }
}
