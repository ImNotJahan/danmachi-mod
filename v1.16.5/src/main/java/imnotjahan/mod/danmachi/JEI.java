package imnotjahan.mod.danmachi;

import imnotjahan.mod.danmachi.init.Recipes;
import imnotjahan.mod.danmachi.util.recipes.SmithingCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Map;

@JeiPlugin
public class JEI implements IModPlugin
{
    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(Main.MODID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        IModPlugin.super.registerCategories(registration);
        registration.addRecipeCategories(new SmithingCategory(registration.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        IModPlugin.super.registerRecipes(registration);

        Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipes =
                ObfuscationReflectionHelper.getPrivateValue(RecipeManager.class,
                        Minecraft.getInstance().level.getRecipeManager(), "recipes");

        registration.addRecipes(recipes.get(Recipes.SMITHING_TYPE).values(),
                new ResourceLocation(Main.MODID, "smithing"));
    }
}
