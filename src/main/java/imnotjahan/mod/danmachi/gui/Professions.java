package imnotjahan.mod.danmachi.gui;

import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Random;

@GameRegistry.ObjectHolder(Reference.MODID)
public class Professions
{
    public final static VillagerRegistry.VillagerProfession guild = null;
    public static VillagerRegistry.VillagerCareer guild_member;

    @Mod.EventBusSubscriber(modid=Reference.MODID)
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<VillagerRegistry.VillagerProfession> event)
        { ;
            final IForgeRegistry<VillagerRegistry.VillagerProfession> registry = event.getRegistry();
            registry.register(new VillagerRegistry.VillagerProfession(Reference.MODID + ":guild", Reference.MODID + ":textures/entity/guild.png", Reference.MODID + ":textures/entity/guild-zombie.png"));
        }
    }

    public static void associateCareersAndTrades()
    {
        guild_member = (new VillagerRegistry.VillagerCareer(guild, "guild_member"))
                .addTrade(1, new MagicStoneTrade())
                .addTrade(1, new MapTrade());
    }

    public static class MagicStoneTrade implements EntityVillager.ITradeList
    {
        public ItemStack stack;
        public EntityVillager.PriceInfo priceInfo;

        public MagicStoneTrade()
        {
            stack = new ItemStack(Items.EMERALD, 2, 0);
            priceInfo = new EntityVillager.PriceInfo(1, 1);
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 1;

            if(priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(ItemInit.MAGIC_STONE, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));
        }
    }

    public static class MapTrade implements EntityVillager.ITradeList
    {
        public ItemStack stack;
        public EntityVillager.PriceInfo priceInfo;

        public MapTrade()
        {
            stack = new ItemStack(ItemInit.ORICHALCUM_INGOT, 1, 0);
            priceInfo = new EntityVillager.PriceInfo(10, 20);
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 1;

            if(priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));
        }
    }
}
