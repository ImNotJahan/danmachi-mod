package imnotjahan.mod.danmachi.util.recipes;

import imnotjahan.mod.danmachi.Reference;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface ISmithingAnvilRecipe extends IRecipe<RecipeWrapper>
{
    ResourceLocation TYPE_ID = new ResourceLocation(Reference.MODID, "smithing");

    @Override
    default IRecipeType<?> getType()
    {
        return Registry.RECIPE_TYPE.get(TYPE_ID);
    }

    @Override
    default boolean canCraftInDimensions(int width, int height)
    {
        return false;
    }
}
