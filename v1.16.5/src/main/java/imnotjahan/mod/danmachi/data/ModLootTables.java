package imnotjahan.mod.danmachi.data;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ModLootTables extends BlockLootTables
{
    private static final List<Block> blocks = new ArrayList<Block>()
    {{
        add(Blocks.SMITHING_ANVIL);
    }};

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return Main.concatenateLists(blocks, BlockStates.normalBlocks);
    }

    @Override
    protected void addTables()
    {
        Main.concatenateLists(blocks, BlockStates.normalBlocks).forEach(super::dropSelf);
    }
}
