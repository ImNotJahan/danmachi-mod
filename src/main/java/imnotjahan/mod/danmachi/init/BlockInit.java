package imnotjahan.mod.danmachi.init;

import imnotjahan.mod.danmachi.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //metal blocks
    public static final Block ORICHALCUM_BLOCK = new BlockBase("orichalcum_block", Material.IRON, 50);
    public static final Block ADAMANTITE_BLOCK = new BlockBase("adamantite_block", Material.IRON, 20);
    public static final Block MYTHRIL_BLOCK = new BlockBase("mythril_block", Material.IRON, 5);

    //ores
    public static final Block ORICHALCUM_ORE = new BlockBase("orichalcum_ore", Material.ROCK, 25);
    public static final Block ADAMANTITE_ORE = new BlockBase("adamantite_ore", Material.ROCK, 10);
    public static final Block MYTHRIL_ORE = new BlockBase("mythril_ore", Material.ROCK, 3);

    //building
    public static final Block ORICHALCUM_WALL = new BlockBase("orichalcum_wall", Material.ROCK, 45);
    public static final Block ADAMANTITE_WALL = new BlockBase("adamantite_wall", Material.ROCK, 15);
    public static final Block DUNGEON_WALL = new DungeonWall();

    //smithing
    public static final Block SMITHING_ANVIL = new SmithingAnvil();

    //other
    public static final Block MAGIC_STONE_STOVE = new MagicStoneStove();
    public static final Block MAGIC_STONE_LAMP = new BlockBase("magic_stone_lamp", Material.ROCK, 3.5f, 1);
    public static final Block RAGING_INFERNO = new RagingInferno();
    public static final Block DUNGEON_PORTAL = new BlockBase("dungeon_portal", Material.ROCK, 100, false);
}