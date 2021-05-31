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
        System.out.println(materials);
        if(materials.contains("item.adamantite_ingot") &&
                materials.contains("item.stick") &&
                materials.contains("item.flintAndSteel"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.FIRE_MAGIC_SWORD));
        } else if(materials.contains("item.adamantite_ingot") &&
                materials.contains("item.stick") &&
                materials.contains("tile.ice"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.ICE_MAGIC_SWORD));
        } else if(materials.contains("tile.blockIron") &&
                materials.contains("item.stick") &&
                materials.contains("tile.blockIron"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.GREATSWORD));
        } else if(materials.contains("item.ingotIron") &&
                materials.contains("item.stick") &&
                materials.contains("item.lygerfang_fang"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.KOTETSU));
        } else if(materials.contains("item.orichalcum_ingot") &&
                materials.contains("item.stick") &&
                materials.contains("item.dyePowder.blue"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.DESPERATE));
        } else if(materials.contains("item.ingotIron") &&
                materials.contains("item.ingotGold") &&
                materials.contains("item.ingotGold"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.KODACHI_FUTABA));
        } else if(materials.contains("tile.planks") &&
                materials.contains("tile.blockIron") &&
                materials.contains("tile.blockIron"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.GREAT_PODAO_ZAGA));
        } else if(materials.contains("item.ingotIron") &&
                materials.contains("item.ingotGold") &&
                materials.contains("item.ingotEmerald"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.PROTAGONISTA));
        } else if(materials.contains("item.urga_blade") &&
                materials.contains("item.stick") &&
                materials.contains("item.urga_blade"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.URGA));
        } else if(materials.contains("item.adamantite_ingot") &&
                materials.contains("item.stick") &&
                materials.contains("item.unicorn_horn"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.HAKUGEN));
        } else if(materials.contains("item.adamantite_ingot") &&
                materials.contains("item.ingotIron") &&
                materials.contains("item.adamantite_ingot"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.URGA_BLADE));
        } else if(materials.contains("item.mythril_ingot") &&
                materials.contains("item.stick") &&
                materials.contains("item.ichor"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.HESTIA_KNIFE));
        } else if(materials.contains("item.ingotIron") &&
                materials.contains("item.stick") &&
                materials.contains("item.ingotIron"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.DAGGER));
        } else if(materials.contains("item.minotaur_horn") &&
                materials.contains("item.stick"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.USHIWAKAMARU));
        } else if(materials.contains("item.damascus_steel") &&
                materials.contains("item.stick") &&
                materials.contains("item.damascus_steel"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.SWORD_AIR));
        } else if(materials.contains("item.adamantite_ingot") &&
                materials.contains("item.stick") &&
                materials.contains("item.adamantite_ingot"))
        {
            smithingOutput.setInventorySlotContents(0, new ItemStack(ItemInit.FORTIA_SPEAR));
        } else
        {
            smithingOutput.setInventorySlotContents(0, ItemStack.EMPTY);
        }
    }
}
