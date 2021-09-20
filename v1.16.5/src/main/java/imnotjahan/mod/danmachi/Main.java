package imnotjahan.mod.danmachi;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusStorage;
import imnotjahan.mod.danmachi.init.Entities;
import imnotjahan.mod.danmachi.init.Items;
import imnotjahan.mod.danmachi.init.Paintings;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.events.ClientEventSubscriber;
import imnotjahan.mod.danmachi.world.OreGen;
import imnotjahan.mod.danmachi.world.dimension.Dimensions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod(Reference.MODID)
public class Main
{
    public static final ItemGroup ToolGroup =
            new MenuGroup(Reference.MODID + "_tools", () -> new ItemStack(Items.DESPERATE));

    public static final ItemGroup MaterialsGroup =
            new MenuGroup(Reference.MODID + "_materials", () -> new ItemStack(Items.MINOTAUR_HORN));

    public static final ItemGroup BlockGroup =
            new MenuGroup(Reference.MODID + "_blocks", () -> new ItemStack(Items.ADAMANTITE_ORE));

    public static final ItemGroup EggGroup =
            new MenuGroup(Reference.MODID + "_eggs", () -> new ItemStack(Items.GOBLIN_SPAWN_EGG));

    public Main()
    {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::SetupClient);
        eventBus.addListener(this::SetupCommon);

        eventBus.addGenericListener(Structure.class, this::registerStructures);

        registerDeferredRegistries(eventBus);
        Paintings.PAINTING_TYPES.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, OreGen::gen);
    }

    public void SetupClient(final FMLClientSetupEvent event)
    {
        ClientEventSubscriber.init();
    }

    public void SetupCommon(final FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(IStatus.class, new StatusStorage(), Status::new);

        // init
        PacketHandler.init();
        Dimensions.init();
    }

    public static void registerDeferredRegistries(IEventBus modBus)
    {
        Entities.ENTITY_DEFERRED.register(modBus);
    }

    public void registerStructures(final RegistryEvent.Register<Structure<?>> event)
    {

    }

    /*
     * Helper method to quickly register features, blocks, items, structures, biomes, anything that can be registered.
     */
    public static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T entry, String registryKey) {
        entry.setRegistryName(new ResourceLocation(Reference.MODID, registryKey));
        registry.register(entry);
        return entry;
    }
}
