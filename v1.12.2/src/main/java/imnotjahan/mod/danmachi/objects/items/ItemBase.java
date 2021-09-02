package imnotjahan.mod.danmachi.objects.items;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.ClientThings;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if(getRegistryName() == null) return super.onItemRightClick(worldIn, playerIn, handIn);
        switch (getRegistryName().toString())
        {
            case "danmachi:guide_book":
                if(playerIn.world.isRemote)
                {
                    ClientThings.openGuidebook(playerIn);
                    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.inventory.getStackInSlot(playerIn.inventory.currentItem));
                }
                break;

            case "danmachi:grimoire":
                playerIn.inventory.getCurrentItem().setCount(0);
                playerIn.getCapability(StatusProvider.STATUS_CAP, Status.capSide).grantMagic();
                return new ActionResult<>(EnumActionResult.SUCCESS, new ItemStack(Items.AIR));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}