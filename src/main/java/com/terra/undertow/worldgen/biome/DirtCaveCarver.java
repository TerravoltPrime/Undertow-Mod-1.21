package com.terra.undertow.worldgen.biome;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.function.Function;

public class DirtCaveCarver extends CaveWorldCarver {

    public DirtCaveCarver(Codec<CaveCarverConfiguration> codec) {
        super(codec);
    }

    @Override
    protected boolean carveBlock(
            CarvingContext context,
            CaveCarverConfiguration config,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeGetter,
            CarvingMask carvingMask,
            BlockPos.MutableBlockPos pos,
            BlockPos.MutableBlockPos checkPos,
            Aquifer aquifer,
            MutableBoolean reachedSurface
    ) {
        // Check if the current block can be carved/replaced by this carver
        if (this.canReplaceBlock(config, chunk.getBlockState(pos))) {
            BlockState goldState;

            // Optional: place Deepslate Gold Ore below Y=0 and regular Gold Ore above
            if (pos.getY() < 0) {
                goldState = Blocks.DEEPSLATE_GOLD_ORE.defaultBlockState();
            } else {
                goldState = Blocks.GOLD_ORE.defaultBlockState();
            }

            // Replace the block with gold ore instead of CAVE_AIR
            chunk.setBlockState(pos, goldState, false);
            return true;
        }

        return false;
    }
}