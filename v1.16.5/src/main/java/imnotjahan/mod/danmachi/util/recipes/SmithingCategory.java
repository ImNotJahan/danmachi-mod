package imnotjahan.mod.danmachi.util.recipes;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.init.Items;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SmithingCategory implements IRecipeCategory<SmithingAnvilRecipe>
{
    private final IJeiHelpers helper;

    public SmithingCategory(IJeiHelpers helper)
    {
        this.helper = helper;
    }

    @Override
    public ResourceLocation getUid()
    {
        return new ResourceLocation(Main.MODID, "smithing");
    }

    @Override
    public Class<? extends SmithingAnvilRecipe> getRecipeClass()
    {
        return SmithingAnvilRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return "Smithing Anvil";
    }

    @Override
    public IDrawable getBackground()
    {
        return helper.getGuiHelper().createDrawable(new ResourceLocation(Main.MODID
                + ":textures/gui/container/smithing_jei.png"), 0, 0, 176, 85);
    }

    @Override
    public IDrawable getIcon()
    {
        return helper.getGuiHelper().createDrawableIngredient(new ItemStack(Items.URGA));
    }

    @Override
    public void setIngredients(SmithingAnvilRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SmithingAnvilRecipe recipe, IIngredients ingredients)
    {
        recipeLayout.getItemStacks().init(0, true, 80, 9);
        recipeLayout.getItemStacks().init(1, true, 35, 34);
        recipeLayout.getItemStacks().init(2, true, 80, 57);
        recipeLayout.getItemStacks().init(3, false, 143, 33);

        for(int k = 0; k < recipe.getIngredients().get(0).getItems().length; k++)
        {
            recipeLayout.getItemStacks().set(k, recipe.getIngredients().get(0).getItems()[k]);
        }

        recipeLayout.getItemStacks().set(3, recipe.getResultItem());
    }
}
