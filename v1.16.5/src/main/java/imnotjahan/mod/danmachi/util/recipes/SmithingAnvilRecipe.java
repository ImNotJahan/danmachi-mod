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

import java.util.List;

public class SmithingAnvilRecipe implements ISmithingAnvilRecipe
{
    private static final int MAX_SIZE = 3;

    ResourceLocation id;
    Ingredient inputs;
    ItemStack output;

    public SmithingAnvilRecipe(ResourceLocation id, Ingredient inputs, ItemStack output)
    {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World world)
    {
        for(int k = 0; k < MAX_SIZE; k++)
        {
            if(inv.countItem(inputs.getItems()[k].getItem()) < 1) return false;
        }

        return true;
    }

    public boolean valid(List<ItemStack> inputs, World world)
    {
        ItemStack[] materials = this.inputs.getItems();
        for(int k = 0; k < MAX_SIZE; k++)
        {
            ItemStack mat = materials[k];
            if(!inputs.stream().anyMatch(c -> c.sameItem(mat))) return false;
            inputs.remove(materials[k]);
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
