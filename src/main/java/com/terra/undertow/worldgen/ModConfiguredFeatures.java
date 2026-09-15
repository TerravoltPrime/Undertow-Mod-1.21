package com.terra.undertow.worldgen;


import com.terra.block.ModBlocks;
import com.terra.undertow.UnderTow;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOIL_KEY = registerKey("soil_key");

public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
    RuleTest mossReplaceables = new TagMatchTest(BlockTags.MOSS_REPLACEABLE);


    List<OreConfiguration.TargetBlockState> soilFormation = List.of(
            OreConfiguration.target(mossReplaceables, ModBlocks.UNDER_SOIL.get().defaultBlockState()));

    register(context, SOIL_KEY, Feature.ORE, new OreConfiguration(soilFormation, 9));}

public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name));
}

private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                      ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
    context.register(key, new ConfiguredFeature<>(feature, configuration));
}
}