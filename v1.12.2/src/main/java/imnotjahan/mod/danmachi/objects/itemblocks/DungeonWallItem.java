package imnotjahan.mod.danmachi.objects.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class DungeonWallItem extends ItemBlock
{
    public DungeonWallItem(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + (stack.getMetadata() != 0 ? "." + stack.getMetadata() : "");
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
