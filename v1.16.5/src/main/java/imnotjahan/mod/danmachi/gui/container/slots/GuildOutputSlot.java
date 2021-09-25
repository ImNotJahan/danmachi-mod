package imnotjahan.mod.danmachi.gui.container.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GuildOutputSlot extends Slot
{
    IInventory inventoryIn;

    public GuildOutputSlot(IInventory inventoryIn, IInventory otherInvIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventoryIn = otherInvIn;
    }

    @Override
    public boolean mayPlace(ItemStack p_75214_1_)
    {
        return false;
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack)
    {
        for(int k = 0; k < 5; k++)
        {
            inventoryIn.setItem(k, ItemStack.EMPTY);
        }
        return stack;
    }
}