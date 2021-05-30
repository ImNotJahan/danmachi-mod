package imnotjahan.mod.danmachi.gui.recipes;

import com.google.common.collect.Maps;
import imnotjahan.mod.danmachi.init.ItemInit;
import net.minecraft.client.gui.Gui;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Map;

public class GuildRecipes
{
    private static final GuildRecipes guildBase = new GuildRecipes();
    /** The list of grinding results. */
    private final Map grindingList = Maps.newHashMap();
    private final Map experienceList = Maps.newHashMap();

    public static GuildRecipes instance()
    {
        return guildBase;
    }

    private GuildRecipes()
    {
        addGrindingRecipe(new ItemStack(ItemInit.GOBLIN_FANG),
                new ItemStack(Items.EMERALD), 0.7f);
    }

    public void addGrindingRecipe(ItemStack parItemStackIn,
                                  ItemStack parItemStackOut, float parExperience)
    {
        grindingList.put(parItemStackIn, parItemStackOut);
        experienceList.put(parItemStackOut, Float.valueOf(parExperience));
    }

    /**
     * Returns the grinding result of an item.
     */
    public ItemStack getGrindingResult(ItemStack parItemStack)
    {
        Iterator iterator = grindingList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, (ItemStack)entry
                .getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack parItemStack1,
                                       ItemStack parItemStack2)
    {
        return parItemStack2.getItem() == parItemStack1.getItem()
                && (parItemStack2.getMetadata() == 32767
                || parItemStack2.getMetadata() == parItemStack1
                .getMetadata());
    }

    public Map getGrindingList()
    {
        return grindingList;
    }

    public float getGrindingExperience(ItemStack parItemStack)
    {
        Iterator iterator = experienceList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack,
                (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}