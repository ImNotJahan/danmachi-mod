package imnotjahan.mod.danmachi.objects.items;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import imnotjahan.mod.danmachi.world.gen.WorldGenCustomStructures;
import imnotjahan.mod.danmachi.world.gen.generators.WorldGenStructure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class StructureBase extends Item implements IHasModel
{
    private WorldGenStructure structure;

    public StructureBase(WorldGenStructure structure)
    {
        setUnlocalizedName(structure.name);
        setRegistryName(structure.name);
        setCreativeTab(Main.creativeTab);

        this.maxStackSize = 1;

        ItemInit.ITEMS.add(this);

        this.structure = structure;
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        structure.generateStructure(worldIn, playerIn.getPosition());

        if(playerIn.isCreative())
        {
            playerIn.inventory.getStackInSlot(playerIn.inventory.currentItem).setCount(0);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
