package imnotjahan.mod.danmachi.init;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.*;
import imnotjahan.mod.danmachi.entities.gods.Hermes;
import imnotjahan.mod.danmachi.entities.gods.Hestia;
import imnotjahan.mod.danmachi.entities.gods.Loki;
import imnotjahan.mod.danmachi.entities.gods.Soma;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

/*
- Entites not yet converted from 1.12.2
Dealer
Hobgoblin
Infant dragon
Killer ant
Kobold
Lygerfang
Sword stag
Minotaur
 */
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Entities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MODID);
    private static final List<Item> SPAWN_EGGS = Lists.newArrayList();

    //Monsters
    public static final RegistryObject<EntityType<Goblin>> GOBLIN = createEntity("goblin", Goblin::new,
            1F, 1.7F, 32806, 9240576);
    public static final RegistryObject<EntityType<Minotaur>> MINOTAUR = createEntity("minotaur", Minotaur::new,
            1F, 2.2F, 6968398, 14111828);
    public static final RegistryObject<EntityType<Almiraj>> ALMIRAJ = createEntity("almiraj", Almiraj::new,
            0.5F, 0.5F, 11796480, 16777215);
    public static final RegistryObject<EntityType<MetalRabbit>> METAL_RABBIT = createEntity("metal_rabbit", MetalRabbit::new,
            0.5F, 0.5F, 11184557, 11796480);
    public static final RegistryObject<EntityType<Bugbear>> BUGBEAR = createEntity("bugbear", Bugbear::new,
            1, 1, 726570, 13121539);
    public static final RegistryObject<EntityType<Hellhound>> HELLHOUND = createEntity("hellhound", Hellhound::new,
            1, 1, 0, 16711680);

    //Gods
    public static final RegistryObject<EntityType<Hestia>> HESTIA = createEntity("hestia", Hestia::new,
            1F, 2F, 15527148, 4609420);
    public static final RegistryObject<EntityType<Hermes>> HERMES = createEntity("hermes", Hermes::new,
            1F, 2F, 13087862, 2773329);
    public static final RegistryObject<EntityType<Soma>> SOMA = createEntity("soma", Soma::new,
            1F, 2F, 6256533, 3947572);
    public static final RegistryObject<EntityType<Loki>> LOKI = createEntity("loki", Loki::new,
            1F, 2F, 2105895, 5530296);

    //Other
    public static final RegistryObject<EntityType<GuildMember>> GUILD_MEMBER = createEntity("guild", GuildMember::new,
            1, 2, 9849600, 16766720);

    private static <T extends Entity> RegistryObject<EntityType<T>>
    createEntity(String name, EntityType.IFactory<T> factory, float width, float height, int primaryEgg, int secondaryEgg)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        EntityType<T> entity = EntityType.Builder.of(factory, EntityClassification.MONSTER).sized(width, height)
                .setTrackingRange(64).setUpdateInterval(1).build(location.toString());
        Item spawnEgg = new SpawnEggItem(entity, primaryEgg, secondaryEgg, (new Item.Properties()).tab(Main.EggGroup));
        spawnEgg.setRegistryName(new ResourceLocation(Reference.MODID, name + "_spawn_egg"));
        SPAWN_EGGS.add(spawnEgg);

        return ENTITY_DEFERRED.register(name, () -> entity);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        EntitySpawnPlacementRegistry.register(GOBLIN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Goblin::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(MINOTAUR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Minotaur::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(HESTIA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Hestia::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(HERMES.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Hermes::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(LOKI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Loki::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(SOMA.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Soma::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(ALMIRAJ.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Almiraj::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(METAL_RABBIT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, MetalRabbit::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(BUGBEAR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Bugbear::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(GUILD_MEMBER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, GuildMember::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(HELLHOUND.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Hellhound::checkMobSpawnRules);
    }

    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event)
    {
        event.put(GOBLIN.get(), Goblin.createAttributes().build());
        event.put(MINOTAUR.get(), Minotaur.createAttributes().build());
        event.put(HESTIA.get(), Hestia.createAttributes().build());
        event.put(HERMES.get(), Hermes.createAttributes().build());
        event.put(SOMA.get(), Soma.createAttributes().build());
        event.put(LOKI.get(), Loki.createAttributes().build());
        event.put(ALMIRAJ.get(), Almiraj.createAttributes().build());
        event.put(METAL_RABBIT.get(), MetalRabbit.createAttributes().build());
        event.put(BUGBEAR.get(), Bugbear.createAttributes().build());
        event.put(GUILD_MEMBER.get(), GuildMember.createAttributes().build());
        event.put(HELLHOUND.get(), Hellhound.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event)
    {
        for (Item spawnEgg : SPAWN_EGGS)
        {
            Preconditions.checkNotNull(spawnEgg.getRegistryName(), "registryName");
            event.getRegistry().register(spawnEgg);
        }
    }
}
