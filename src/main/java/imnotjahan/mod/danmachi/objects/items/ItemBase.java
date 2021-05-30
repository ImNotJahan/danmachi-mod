package imnotjahan.mod.danmachi.objects.items;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;

public class ItemBase extends Item implements IHasModel
{
    private final String name;

    public ItemBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);

        ItemInit.ITEMS.add(this);

        this.name = name;
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        switch (name)
        {
            case "guide_book":
                if(playerIn.world.isRemote)
                {
                    openGuidebook(playerIn);
                    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.inventory.getStackInSlot(playerIn.inventory.currentItem));
                }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void openGuidebook(EntityPlayer playerIn)
    {
        /*ItemStack book = new ItemStack(Items.WRITTEN_BOOK);

        NBTTagList bookPages = new NBTTagList();

        for (int k = 0; k < ModConfig.guideBookText.length; k++)
        {
            bookPages.appendTag(new NBTTagString(ModConfig.guideBookText[k]));
        }

        book.setTagInfo("pages", bookPages);
        book.setTagInfo("author", new NBTTagString("Albert Valdstejn"));
        book.setTagInfo("title", new NBTTagString("Guide Book"));

        Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(playerIn, book, false));*/
    }
}