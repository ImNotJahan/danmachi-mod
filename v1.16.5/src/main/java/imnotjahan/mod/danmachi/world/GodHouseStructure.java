package imnotjahan.mod.danmachi.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class GodHouseStructure extends Structure<ProbabilityConfig>
{

    public GodHouseStructure(Codec<ProbabilityConfig> p_i231997_1_)
    {
        super(p_i231997_1_);
    }

    public Structure.IStartFactory<ProbabilityConfig> getStartFactory()
    {
        return GodHouseStructure.Start::new;
    }

    public static class Start extends StructureStart<ProbabilityConfig>
    {
        public Start(Structure<ProbabilityConfig> p_i225806_1_, int p_i225806_2_, int p_i225806_3_, MutableBoundingBox p_i225806_4_, int p_i225806_5_, long p_i225806_6_) {
            super(p_i225806_1_, p_i225806_2_, p_i225806_3_, p_i225806_4_, p_i225806_5_, p_i225806_6_);
        }

        @Override
        public void generatePieces(DynamicRegistries p_230364_1_, ChunkGenerator p_230364_2_, TemplateManager p_230364_3_, int p_230364_4_, int p_230364_5_, Biome p_230364_6_, ProbabilityConfig p_230364_7_)
        {
            int i = p_230364_4_ * 16;
            int j = p_230364_5_ * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            this.pieces.add(new Piece(blockpos));
            this.calculateBoundingBox();
        }
    }

    public static class Piece extends StructurePiece
    {
        public Piece(BlockPos p_i48882_1_)
        {
            super(IStructurePieceType.BURIED_TREASURE_PIECE, 0);
            this.boundingBox = new MutableBoundingBox(p_i48882_1_.getX(), p_i48882_1_.getY(), p_i48882_1_.getZ(), p_i48882_1_.getX(), p_i48882_1_.getY(), p_i48882_1_.getZ());
        }

        public Piece(TemplateManager p_i50677_1_, CompoundNBT p_i50677_2_)
        {
            super(IStructurePieceType.BURIED_TREASURE_PIECE, p_i50677_2_);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT p_143011_1_)
        {

        }

        @Override
        public boolean postProcess(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_)
        {
            return false;
        }
    }
}