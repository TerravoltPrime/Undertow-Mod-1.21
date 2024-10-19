package com.terra.undertow.worldgen.biome;

import com.terra.undertow.ModSounds;
import com.terra.undertow.UnderTow;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

import static com.terra.undertow.ModSounds.SLEEPLESS;

public class ModBiomes {
    public static final ResourceKey<Biome> UNDERGROVE = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(UnderTow.MOD_ID, "Undergrove"));

    public static void bootstrap(BootstrapContext<Biome> context){}

public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
    BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
    BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
    BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
    BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
    BiomeDefaultFeatures.addDefaultSprings(builder);
    BiomeDefaultFeatures.addSurfaceFreezing(builder);
}

    public static Biome undergrove(BootstrapContext<Biome> context) {
    MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

    spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 5, 4, 4));

    BiomeDefaultFeatures.farmAnimals(spawnBuilder);
    BiomeDefaultFeatures.commonSpawns(spawnBuilder);

    BiomeGenerationSettings.Builder biomeBuilder =
            new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
    //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
    globalOverworldGeneration(biomeBuilder);
    BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
    BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
    BiomeDefaultFeatures.addFerns(biomeBuilder);
    BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
    BiomeDefaultFeatures.addExtraGold(biomeBuilder);


    BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
    BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic((Holder<SoundEvent>) SLEEPLESS.get())).build())

                .build();
    }


}