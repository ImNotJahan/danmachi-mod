package imnotjahan.mod.danmachi.data;

import imnotjahan.mod.danmachi.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class ModLootTables extends BlockLootTables
{
    private static final Iterable<Block> blocks = new ArrayList<Block>()
    {{
        add(Blocks.ADAMANTITE_BLOCK);
        add(Blocks.ADAMANTITE_ORE);
        add(Blocks.ADAMANTITE_WALL);
        add(Blocks.DAMASCUS_BLOCK);
        add(Blocks.DAMASCUS_ORE);
        add(Blocks.MYTHRIL_BLOCK);
        add(Blocks.MYTHRIL_ORE);
        add(Blocks.NOSTEEL_BLOCK);
        add(Blocks.NOSTEEL_ORE);
        add(Blocks.ORICHALCUM_BLOCK);
        add(Blocks.ORICHALCUM_ORE);
        add(Blocks.ORICHALCUM_WALL);
        add(Blocks.SMITHING_ANVIL);
    }};

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return blocks;
    }

    @Override
    protected void addTables()
    {
        blocks.forEach(super::dropSelf);
    }
}
