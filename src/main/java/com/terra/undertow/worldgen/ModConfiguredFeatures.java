package com.terra.undertow.worldgen;


import com.sun.jna.platform.unix.X11;
import com.terra.block.ModBlocks;
import com.terra.undertow.UnderTow;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ReplaceBlobsFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import org.lwjgl.system.windows.DISPLAY_DEVICE;

import java.util.List;

import static com.terra.block.ModBlocks.UNDER_SOIL;
import static net.minecraft.data.worldgen.features.MiscOverworldFeatures.FREEZE_TOP_LAYER;
import static net.minecraft.world.item.ItemDisplayContext.GROUND;


public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOIL_KEY = registerKey ("soil");
    private static ReplaceSphereConfiguration ReplaceSphereConfiguration;


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest mossReplaceables = new TagMatchTest(BlockTags.MOSS_REPLACEABLE);

        List<OreConfiguration.TargetBlockState> soil = List.of(
                OreConfiguration.target(mossReplaceables, UNDER_SOIL.get().defaultBlockState()));


        FeatureUtils.register(
                context,
                SOIL_KEY,
                Feature.DISK,
                new DiskConfiguration(
                        new RuleBasedBlockStateProvider(
                                BlockStateProvider.simple(UNDER_SOIL.get()),
                                List.of(
                                        new RuleBasedBlockStateProvider.Rule(
                                                BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.AIR), BlockStateProvider.simple(Blocks.SANDSTONE)
                                        )
                                )
                        ),
                        BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)),
                        UniformInt.of(2, 6),
                        2
                )
        );
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}