package imnotjahan.mod.danmachi.gui.container.slot;

import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.stream.IntStream;

public class SmithingSlot extends Slot
{
    IInventory inventoryIn;

    public SmithingSlot(IInventory inventoryIn, IInventory otherInvIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventoryIn = otherInvIn;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
    {
        for(int k = 0; k < 3; k++)
        {
            inventoryIn.setInventorySlotContents(k, ItemStack.EMPTY);
        }
        return stack;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        ItemStack output = inventory.getStackInSlot(0);

        NBTTagList tag = new NBTTagList();

        if(output.hasTagCompound() && output.getTagCompound().hasKey("displayd") && output.getTagCompound().getCompoundTag("displayd").hasKey("a"))
        {
            tag = output.getTagCompound().getCompoundTag("display")
                    .getTagList("Lore", Constants.NBT.TAG_STRING);
        }

        if(ModConfig.requiredSmithingDex.containsKey(output.getItem()
                .getRegistryName().toString()))
        {
            int requiredDex = ModConfig.requiredSmithingDex.get(output.getItem()
                    .getRegistryName().toString());

            if (playerIn.getCapability(StatusProvider.STATUS_CAP, Status.capSide).get(3) +
                    (playerIn.getCapability(StatusProvider.STATUS_CAP, Status.capSide).getLevel() - 1) * 1000 >= requiredDex)
            {
                resetTags(tag);
                return true;
            } else
            {
                resetTags(tag);
                tag.appendTag(new NBTTagString("Not enough dexterity"));
            }
        }

        return false;
    }

    private static void resetTags(NBTTagList tags)
    {
        IntStream.range(0, tags.tagCount()).forEach(tags::removeTag);
    }
}
