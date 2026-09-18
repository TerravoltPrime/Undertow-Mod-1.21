package com.terra.undertow.worldgen.biome;

import com.terra.undertow.UnderTow;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public class ModBiomes {
    // 1. Biome ResourceKey
    public static final ResourceKey<Biome> UNDERGROVE =
            ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, "undergove"));

    // 2. Custom Configured Carver ResourceKey (e.g., custom cave)
    public static final ResourceKey<ConfiguredWorldCarver<?>> DIRT_CAVE_CARVER
 =
            ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, "custom_cave"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(UNDERGROVE, createCustomBiome(context));
    }

    private static Biome createCustomBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER) // Configured carver lookup required
        );

        // Add default vanilla carvers (optional)
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);

        // 3. Add your custom carver to the AIR generation step
        generationSettings.addCarver(
                GenerationStep.Carving.AIR,
                context.lookup(Registries.CONFIGURED_CARVER).getOrThrow(DIRT_CAVE_CARVER
)
        );

        // Add standard generation features
        BiomeDefaultFeatures.addDefaultOres(generationSettings);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7f)
                .downfall(0.8f)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .fogColor(0xC0D8FF)
                        .skyColor(0x78A7FF)
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}