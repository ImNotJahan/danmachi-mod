package imnotjahan.mod.danmachi.gui.container.slot;

import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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
        if(ModConfig.requiredSmithingDex.containsKey(inventory.getStackInSlot(0).getUnlocalizedName()))
        {
            int requiredDex = ModConfig.requiredSmithingDex.get(inventory.getStackInSlot(0).getUnlocalizedName());

            if (playerIn.getCapability(StatusProvider.STATUS_CAP, Status.capSide).get(3) +
                    (playerIn.getCapability(StatusProvider.STATUS_CAP, Status.capSide).getLevel() - 1) * 1000 >= requiredDex)
            {
                return true;
            }
        }

        return false;
    }
}
