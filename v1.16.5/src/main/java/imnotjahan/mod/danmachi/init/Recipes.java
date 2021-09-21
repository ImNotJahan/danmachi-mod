package imnotjahan.mod.danmachi.init;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.util.recipes.ISmithingAnvilRecipe;
import imnotjahan.mod.danmachi.util.recipes.SmithingAnvilRecipe;
import imnotjahan.mod.danmachi.util.recipes.SmithingRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class Recipes
{
    public static final IRecipeSerializer<SmithingAnvilRecipe> SMITHING_RECIPE_SERIALIZER = new SmithingRecipeSerializer();
    public static final IRecipeType<ISmithingAnvilRecipe> SMITHING_TYPE = registerType(ISmithingAnvilRecipe.TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    public static final RegistryObject<IRecipeSerializer<?>> SMITHING_SERIALIZER =
            RECIPE_SERIALIZERS.register("smithing",
            () -> SMITHING_RECIPE_SERIALIZER);

    public static RecipeManager RECIPE_MANAGER;

    public static void init(){}

    @Nonnull
    public static Map<ResourceLocation, IRecipe<?>> getRecipes (IRecipeType<?> recipeType)
    {
        final Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> recipesMap =
                ObfuscationReflectionHelper.getPrivateValue(RecipeManager.class, RECIPE_MANAGER, "recipes");

        if(recipesMap != null && recipesMap.get(recipeType) != null) return recipesMap.get(recipeType);
        else return new HashMap<>();
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T>
    {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId)
    {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }
}
