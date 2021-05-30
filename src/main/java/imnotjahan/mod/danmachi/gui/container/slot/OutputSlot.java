package imnotjahan.mod.danmachi.gui.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class OutputSlot extends Slot
{
    IInventory inventoryIn;

    public OutputSlot(IInventory inventoryIn, IInventory otherInvIn, int index, int xPosition, int yPosition)
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
        for(int k = 0; k < 5; k++)
        {
            inventoryIn.setInventorySlotContents(k, ItemStack.EMPTY);
        }
        return stack;
    }
}
