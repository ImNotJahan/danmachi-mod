package imnotjahan.mod.danmachi.util.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class SmithingRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
        implements IRecipeSerializer<SmithingAnvilRecipe>
{
    @Override
    public SmithingAnvilRecipe fromJson(ResourceLocation id, JsonObject json)
    {
        Ingredient inputs = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "inputs"));
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);

        return new SmithingAnvilRecipe(id, inputs, output);
    }

    @Nullable
    @Override
    public SmithingAnvilRecipe fromNetwork(ResourceLocation id, PacketBuffer buf)
    {
        Ingredient inputs = Ingredient.fromNetwork(buf);
        ItemStack output = buf.readItem();

        return new SmithingAnvilRecipe(id, inputs, output);
    }

    @Override
    public void toNetwork(PacketBuffer buf, SmithingAnvilRecipe recipe)
    {
        recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(buf));
        buf.writeItemStack(recipe.getResultItem(), false);
    }
}
