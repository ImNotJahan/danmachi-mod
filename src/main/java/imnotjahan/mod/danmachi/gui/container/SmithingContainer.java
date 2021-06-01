package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.gui.container.slot.SmithingSlot;
import imnotjahan.mod.danmachi.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.Sys;

import java.util.ArrayList;

public class SmithingContainer extends Container
{
    private IInventory smithingInventory = new InventoryBasic(new TextComponentString("Guild Member"), 3);
    private IInventory smithingOutput = new InventoryBasic(new TextComponentString("Guild Output"), 1);

    public SmithingContainer(InventoryPlayer playerInventory)
    {
        this.addSlotToContainer(new Slot(smithingInventory, 0, 81, 10));
        this.addSlotToContainer(new Slot(smithingInventory, 1, 36, 35));
        this.addSlotToContainer(new Slot(smithingInventory, 2, 81, 58));

        this.addSlotToContainer(new SmithingSlot(smithingOutput, smithingInventory, 0, 144, 34));

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
        return this.smithingInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!playerIn.world.isRemote)
        {
            for(int k = 0; k < smithingInventory.getSizeInventory(); k++)
            {
                ItemStack itemstack = this.smithingInventory.removeStackFromSlot(k);

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

        ArrayList<String> materials = new ArrayList<>();

        for(int k = 0; k < smithingInventory.getSizeInventory(); k++)
        {
            materials.add(smithingInventory.getStackInSlot(k).getUnlocalizedName());
        }

        if(materialsContains("item.adamantite_ingot",
                "item.stick", "item.flintAndSteel", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.FIRE_MAGIC_SWORD));
        } else if(materialsContains("item.adamantite_ingot",
                "item.stick", "tile.ice", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.ICE_MAGIC_SWORD));
        } else if(materialsContains("tile.blockIron",
                "item.stick", "tile.blockIron", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.GREATSWORD));
        } else if(materialsContains("item.ingotIron",
                "item.stick", "item.lygerfang_fang", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.KOTETSU));
        } else if(materialsContains("item.orichalcum_ingot",
                "item.stick", "item.dyePowder.blue", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.DESPERATE));
        } else if(materialsContains("item.ingotIron",
                "item.ingotGold", "item.ingotGold", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.KODACHI_FUTABA));
        } else if(materialsContains("tile.wood.oak",
                "tile.blockIron", "tile.blockIron", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.GREAT_PODAO_ZAGA));
        } else if(materialsContains("item.ingotIron",
                "item.ingotGold", "item.emerald", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.PROTAGONISTA));
        } else if(materialsContains("item.urga_blade",
                "item.stick", "item.urga_blade", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.URGA));
        } else if(materialsContains("item.adamantite_ingot",
                "item.stick", "item.unicorn_horn", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.HAKUGEN));
        } else if(materialsContains("item.adamantite_ingot",
                "item.ingotIron", "item.adamantite_ingot", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.URGA_BLADE));
        } else if(materialsContains("item.mythril_ingot",
                "item.stick", "item.ichor", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.HESTIA_KNIFE));
        } else if(materialsContains("item.ingotIron",
                "item.stick", "tile.air", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.DAGGER));
        } else if(materialsContains("item.minotaur_horn",
                "item.stick", "tile.air", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.USHIWAKAMARU));
        } else if(materialsContains("item.damascus_steel",
                "item.stick", "item.damascus_steel", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.SWORD_AIR));
        } else if(materialsContains("item.adamantite_ingot",
                "item.stick", "item.adamantite_ingot", materials))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.FORTIA_SPEAR));
        } else
        {
            smithingOutput.setInventorySlotContents(0, ItemStack.EMPTY);
        }
    }

    private static boolean materialsContains(String item0, String item1, String item2, ArrayList<String> materials)
    {
        ArrayList<String> newMaterials = new ArrayList<>(materials);

        System.out.println(newMaterials);

        if(newMaterials.contains(item0))
        {
            newMaterials.remove(item0);
            System.out.println(0);

            if(newMaterials.contains(item1))
            {
                newMaterials.remove(item1);
                System.out.println(1);

                if(newMaterials.contains(item2))
                {
                    System.out.println(2);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 3)
            {
                if (!this.mergeItemStack(itemstack1, 4, 41, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 0 && par2 != 1 && par2 != 2)
            {
                if (!this.mergeItemStack(itemstack1, 0, 4, false))
                {
                    return ItemStack.EMPTY;
                } else if (par2 >= 5 && par2 < 32)
                {
                    if (!this.mergeItemStack(itemstack1, 32, 41, false))
                    {
                        return ItemStack.EMPTY;
                    }
                } else if (par2 >= 32 && par2 < 41 && !this.mergeItemStack(itemstack1, 5, 32, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 5, 41, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}