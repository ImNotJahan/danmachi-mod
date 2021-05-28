package imnotjahan.mod.danmachi.util.handlers;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.commands.CommandDanmachiDebug;
import imnotjahan.mod.danmachi.commands.CommandDungeon;
import imnotjahan.mod.danmachi.init.*;
import imnotjahan.mod.danmachi.keybinds.KeyInputHandler;
import imnotjahan.mod.danmachi.keybinds.Keybinds;
import imnotjahan.mod.danmachi.network.NetworkHandler;
import imnotjahan.mod.danmachi.proxy.ClientProxy;
import imnotjahan.mod.danmachi.util.compat.OreDict;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import imnotjahan.mod.danmachi.world.gen.GenerateOres;
import imnotjahan.mod.danmachi.world.gen.WorldGenCustomStructures;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onEnchantRegister(RegistryEvent.Register<Enchantment> event)
    {
        event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }
    }

    public static void preInitRegistries()
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
        DimensionInit.RegisterDimensions();
        EntityInit.RegisterEntities();
        RecipeHandler.registerSmelting();
    }

    public static void otherRegistries()
    {
        GameRegistry.registerWorldGenerator(new GenerateOres(), 0);

        BiomeInit.RegisterBiomes();
    }

    public static void initRegistries()
    {
        SoundHandler.registerSounds();
        NetworkHandler.init();
        RecipeHandler.registerSmelting();
        OreDict.registerOres();
    }

    public static void serverInitRegistries(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDungeon());
        event.registerServerCommand(new CommandDanmachiDebug());
    }
}
