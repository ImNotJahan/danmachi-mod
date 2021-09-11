package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.gui.container.slots.SmithingSlot;
import imnotjahan.mod.danmachi.init.Items;
import imnotjahan.mod.danmachi.util.events.EventSubscriber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

public class SmithingContainer extends Container
{
    private IInventory smithingInventory = new Inventory(3);
    private IInventory smithingOutput = new Inventory(1);

    public SmithingContainer(int id, PlayerInventory playerInventory, PacketBuffer extraData)
    {
        this(id, playerInventory, new Inventory(6 * 9));
    }

    public SmithingContainer(int id, PlayerInventory playerInventory, IInventory inventory)
    {

        super(EventSubscriber.smithingContainer, id);

        this.addSlot(new Slot(smithingInventory, 0, 81, 10));
        this.addSlot(new Slot(smithingInventory, 1, 36, 35));
        this.addSlot(new Slot(smithingInventory, 2, 81, 58));

        this.addSlot(new SmithingSlot(smithingOutput, smithingInventory, 0, 144, 34));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public SmithingContainer(int id, PlayerInventory playerInventory)
    {
        this(id, playerInventory, new Inventory(6 * 9));
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_)
    {
        return true;
    }



    @Override
    public void broadcastChanges()
    {
        super.broadcastChanges();

        ArrayList<String> materials = new ArrayList<>();

        for(int k = 0; k < smithingInventory.getContainerSize(); k++)
        {
            materials.add(smithingInventory.getItem(k).getItem().getRegistryName().toString());
        }

        if(materialsContains("danmachi:adamantite_ingot",
                "danmachi:stick", "danmachi:flintAndSteel", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.FIRE_MAGIC_SWORD));
        } else if(materialsContains("danmachi:adamantite_ingot",
                "danmachi:stick", "tile.ice", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.ICE_MAGIC_SWORD));
        } else if(materialsContains("tile.blockIron",
                "danmachi:stick", "tile.blockIron", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.GREATSWORD));
        } else if(materialsContains("danmachi:ingotIron",
                "danmachi:stick", "danmachi:lygerfang_fang", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.KOTETSU));
        } else if(materialsContains("danmachi:orichalcum_ingot",
                "danmachi:stick", "danmachi:dyePowder.blue", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.DESPERATE));
        } else if(materialsContains("danmachi:ingotIron",
                "danmachi:ingotGold", "danmachi:ingotGold", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.KODACHI_FUTABA));
        } else if(materialsContains("tile.wood.oak",
                "tile.blockIron", "tile.blockIron", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.GREAT_PODAO_ZAGA));
        } else if(materialsContains("danmachi:ingotIron",
                "danmachi:ingotGold", "danmachi:emerald", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.PROTAGONISTA));
        } else if(materialsContains("danmachi:urga_blade",
                "danmachi:stick", "danmachi:urga_blade", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.URGA));
        } else if(materialsContains("danmachi:adamantite_ingot",
                "danmachi:stick", "danmachi:unicorn_horn", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.HAKUGEN));
        } else if(materialsContains("danmachi:adamantite_ingot",
                "danmachi:ingotIron", "danmachi:adamantite_ingot", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.URGA_BLADE));
        } else if(materialsContains("danmachi:mythril_ingot",
                "danmachi:stick", "danmachi:ichor", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.HESTIA_KNIFE));
        } else if(materialsContains("danmachi:ingotIron",
                "danmachi:stick", "tile.air", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.DAGGER));
        } else if(materialsContains("danmachi:minotaur_horn",
                "danmachi:stick", "tile.air", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.USHIWAKAMARU));
        } else if(materialsContains("danmachi:damascus_steel",
                "danmachi:stick", "danmachi:damascus_steel", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.SWORD_AIR));
        } else if(materialsContains("danmachi:adamantite_ingot",
                "danmachi:stick", "danmachi:adamantite_ingot", materials))
        {
            smithingOutput.setItem(0, new ItemStack(Items.FORTIA_SPEAR));
        } else
        {
            smithingOutput.setItem(0, ItemStack.EMPTY);
        }
    }

    private static boolean materialsContains(String item0, String item1, String item2, ArrayList<String> materials)
    {
        ArrayList<String> newMaterials = new ArrayList<>(materials);

        if(newMaterials.contains(item0))
        {
            newMaterials.remove(item0);

            if(newMaterials.contains(item1))
            {
                newMaterials.remove(item1);

                if(newMaterials.contains(item2))
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity par1EntityPlayer, int par2)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(par2);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (par2 == 3)
            {
                if (!this.moveItemStackTo(itemstack1, 4, 41, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (par2 != 0 && par2 != 1 && par2 != 2)
            {
                if (!this.moveItemStackTo(itemstack1, 0, 4, false))
                {
                    return ItemStack.EMPTY;
                } else if (par2 >= 5 && par2 < 32)
                {
                    if (!this.moveItemStackTo(itemstack1, 32, 41, false))
                    {
                        return ItemStack.EMPTY;
                    }
                } else if (par2 >= 32 && par2 < 41 && !this.moveItemStackTo(itemstack1, 5, 32, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 5, 41, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0)
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
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
