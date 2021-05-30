package imnotjahan.mod.danmachi.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class GuildContainer extends Container
{
    private IInventory guildInventory = new InventoryBasic(new TextComponentString("Guild Member"), 5);
    private IInventory guildOutput = new InventoryBasic(new TextComponentString("Guild Output"), 3);

    public GuildContainer(InventoryPlayer playerInventory)
    {
        for (int k = 0; k < 5; ++k)
        {
            this.addSlotToContainer(new Slot(guildInventory, k, 8 + k * 18, 20));
        }

        for (int k = 0; k < 3; ++k)
        {
            this.addSlotToContainer(new Slot(guildOutput, k, 116 + k * 18, 20));
        }

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 109));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.guildInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {

        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!playerIn.world.isRemote)
        {
            for(int k = 0; k < guildInventory.getSizeInventory(); k++)
            {
                ItemStack itemstack = this.guildInventory.removeStackFromSlot(k);

                if (!itemstack.isEmpty())
                {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }
}
