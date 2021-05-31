package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.gui.container.slot.GuildOutputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class SmithingContainer extends Container
{
    private IInventory guildInventory = new InventoryBasic(new TextComponentString("Guild Member"), 3);
    private IInventory guildOutput = new InventoryBasic(new TextComponentString("Guild Output"), 1);

    public SmithingContainer(InventoryPlayer playerInventory)
    {
        this.addSlotToContainer(new Slot(guildInventory, 0, 81, 10));
        this.addSlotToContainer(new Slot(guildInventory, 1, 36, 35));
        this.addSlotToContainer(new Slot(guildInventory, 2, 81, 58));

        this.addSlotToContainer(new GuildOutputSlot(guildOutput, guildInventory, 0, 144, 34));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.guildInventory.isUsableByPlayer(playerIn);
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

        int value = 0;

        for(int k = 0; k < guildInventory.getSizeInventory(); k++)
        {
            ItemStack stack = guildInventory.getStackInSlot(k);
            for(int j = 0; j < ModConfig.guildTrades.size(); j++)
            {
                if (ModConfig.guildTrades.containsKey(stack.getItem().getUnlocalizedName()))
                {
                    value += ModConfig.guildTrades.get(stack.getItem().getUnlocalizedName())
                            * stack.getCount();
                    break;
                } else if(stack.getItem().getUnlocalizedName().equals("item.magic_stone"))
                {
                    String droppedFrom = stack.getTagCompound().getCompoundTag("display").getTag("Lore").toString();
                    droppedFrom = droppedFrom.replace("]", "");
                    droppedFrom = droppedFrom.replace("\"", "");
                    droppedFrom = droppedFrom.split(" ")[2];

                    if (ModConfig.magicStoneTrades.containsKey(droppedFrom))
                    {
                        value += ModConfig.magicStoneTrades.get(droppedFrom)
                                * stack.getCount();
                        break;
                    }
                }
            }
        }

        guildOutput.setInventorySlotContents(0, new ItemStack(Items.EMERALD, value));
    }
}
