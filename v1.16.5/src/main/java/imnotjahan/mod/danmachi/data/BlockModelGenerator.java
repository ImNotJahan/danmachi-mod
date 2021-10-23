package imnotjahan.mod.danmachi.data;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockModelGenerator extends BlockModelProvider
{
    BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput;
    Iterable<Block> blocks;
    public BlockModelGenerator(Consumer<IFinishedBlockState> consumer, BiConsumer<ResourceLocation, Supplier<JsonElement>> p_i232514_2_, Iterable<Block> blocks)
    {
        super(consumer, p_i232514_2_, Sets.newHashSet()::add);
        modelOutput = p_i232514_2_;
        this.blocks = blocks;
    }

    private void createTrivialBlock(Block p_239956_1_, TexturedModel.ISupplier p_239956_2_) {
        createSimpleBlock(p_239956_1_, p_239956_2_.create(p_239956_1_, this.modelOutput));
    }

    private static FinishedVariantBlockState createSimpleBlock(Block p_239978_0_, ResourceLocation p_239978_1_) {
        return FinishedVariantBlockState.multiVariant(p_239978_0_, BlockModelDefinition.variant().with(BlockModelFields.MODEL, p_239978_1_));
    }

    @Override
    public void run()
    {
        blocks.forEach(block -> createTrivialBlock(block, TexturedModel.CUBE));
    }
}
