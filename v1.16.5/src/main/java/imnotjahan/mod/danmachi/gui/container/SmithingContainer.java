package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.gui.container.slots.SmithingSlot;
import imnotjahan.mod.danmachi.init.Items;
import imnotjahan.mod.danmachi.init.Recipes;
import imnotjahan.mod.danmachi.util.events.EventSubscriber;
import imnotjahan.mod.danmachi.util.recipes.SmithingAnvilRecipe;
import imnotjahan.mod.danmachi.util.recipes.SmithingRecipeSerializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SmithingContainer extends Container
{
    private IInventory smithingInventory = new Inventory(3);
    private IInventory smithingOutput = new Inventory(1);

    public SmithingContainer(int id, PlayerInventory playerInventory, PacketBuffer extraData)
    {
        this(id, playerInventory, IWorldPosCallable.NULL);
    }

    private final IWorldPosCallable access;
    private final World world;

    public SmithingContainer(int id, PlayerInventory playerInventory, IWorldPosCallable access)
    {
        super(EventSubscriber.smithingContainer, id);

        this.access = access;
        world = playerInventory.player.level;
        Recipes.RECIPE_MANAGER = world.getRecipeManager();

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

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_)
    {
        return true;
    }

    @Override
    public void removed(PlayerEntity p_75134_1_) {
        super.removed(p_75134_1_);
        this.access.execute((p_234647_2_, p_234647_3_) -> {
            this.clearContainer(p_75134_1_, p_234647_2_, this.smithingInventory);
        });
    }

    @Override
    public void broadcastChanges()
    {
        super.broadcastChanges();

        IRecipeType<?> type = Recipes.SMITHING_TYPE;
        Map<ResourceLocation, IRecipe<?>> recipes = Recipes.getRecipes(type);
        Collection<IRecipe<?>> mainRecipes = recipes.values();

        for(IRecipe<?> recipe : mainRecipes)
        {
            if(!(recipe instanceof SmithingAnvilRecipe)) return;

            final SmithingAnvilRecipe smithingRecipe = (SmithingAnvilRecipe) recipe;

            List<ItemStack> inputs = new ArrayList<ItemStack>()
            {{
                smithingInventory.getItem(0);
                smithingInventory.getItem(1);
                smithingInventory.getItem(2);
            }};

            if(smithingRecipe.valid(inputs, world)) smithingOutput.setItem(0, smithingRecipe.getResultItem());
            else smithingOutput.setItem(0, ItemStack.EMPTY);
        }

        smithingOutput.setChanged();
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
