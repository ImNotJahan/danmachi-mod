package imnotjahan.mod.danmachi.gui.container.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;

public class ArmorDressSlot extends Slot
{
    IInventory inventoryIn;

    public ArmorDressSlot(IInventory inventoryIn, IInventory otherInvIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
        this.inventoryIn = otherInvIn;
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return stack.getItem() instanceof PotionItem;
    }
}