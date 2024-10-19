package com.terra.undertow.worldgen.biome.surface;

import com.terra.block.ModBlocks;
import com.terra.undertow.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import org.spongepowered.include.com.google.common.collect.ImmutableList;


public class ModSurfaceRules {


    private static final SurfaceRules.RuleSource MOD_DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource MOD_GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource MOD_STONE_BLOCK = makeStateRule(ModBlocks.CINNABAR.get());
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterlevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource myRules = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.UNDERGROVE), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MOD_GRASS_BLOCK)
        );

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterlevel, SurfaceRules.sequence(
                myRules,
                MOD_GRASS_BLOCK
        )),MOD_DIRT);


        SurfaceRules.RuleSource modBiomeRules = SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.UNDERGROVE),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(grassSurface,myRules))),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MOD_DIRT)
                ),
                grassSurface
        );

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        builder.add(modBiomeRules);

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }

    //ORIGINAL
    /*public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.GHOSTLY_BIOME),
                                //SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, RAW_SAPPHIRE)),
                        //SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SAPPHIRE)),

                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        )));
    }*/

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

}