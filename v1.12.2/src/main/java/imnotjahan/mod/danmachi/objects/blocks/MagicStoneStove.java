package imnotjahan.mod.danmachi.objects.blocks;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.block.BlockFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MagicStoneStove extends BlockFurnace implements IHasModel
{

    public MagicStoneStove()
    {
        super(false);

        setUnlocalizedName("magic_stone_stove");
        setRegistryName("magic_stone_stove");
        setCreativeTab(Main.creativeTab);

        setHardness(3.5f);

        BlockInit.BLOCKS.add(this);

        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }
}
