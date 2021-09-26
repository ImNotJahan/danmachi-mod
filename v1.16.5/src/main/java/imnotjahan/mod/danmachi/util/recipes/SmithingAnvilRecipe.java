package imnotjahan.mod.danmachi.util.recipes;

import imnotjahan.mod.danmachi.init.Recipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.ArrayList;
import java.util.List;

public class SmithingAnvilRecipe implements ISmithingAnvilRecipe
{
    ResourceLocation id;

    Ingredient inputs;
    ItemStack output;
    public int requiredDex;

    public SmithingAnvilRecipe(ResourceLocation id, Ingredient inputs, ItemStack output, int requiredDex)
    {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.requiredDex = requiredDex;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World world)
    {
        for(int k = 0; k < inv.getContainerSize(); k++)
        {
            if(inv.countItem(inputs.getItems()[k].getItem()) < 1) return false;
        }

        return true;
    }

    public boolean valid(List<ItemStack> inputs, World world)
    {
        ItemStack[] materials = this.inputs.getItems();

        List<Item> items = new ArrayList<>();
        inputs.forEach(item -> items.add(item.getItem()));

        for (ItemStack mat : materials)
        {
            Item item = mat.getItem();
            if (!items.contains(item)) return false;
            items.remove(item);
        }

        return true;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv)
    {
        return output;
    }

    @Override
    public ItemStack getResultItem()
    {
        return output;
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return Recipes.SMITHING_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.of(Ingredient.EMPTY, inputs);
    }
}
