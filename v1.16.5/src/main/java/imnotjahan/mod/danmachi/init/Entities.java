package imnotjahan.mod.danmachi.init;

import com.google.common.collect.Lists;
import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.*;
import imnotjahan.mod.danmachi.entities.gods.Hermes;
import imnotjahan.mod.danmachi.entities.gods.Hestia;
import imnotjahan.mod.danmachi.entities.gods.Loki;
import imnotjahan.mod.danmachi.entities.gods.Soma;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

/*
- Entites not yet converted from 1.12.2
Dealer
Hobgoblin
Infant dragon
Killer ant
Lygerfang
Sword stag
 */
public class Entities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);
    public static final List<Item> SPAWN_EGGS = Lists.newArrayList();

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
    public static final RegistryObject<EntityType<Unicorn>> UNICORN = createEntity("unicorn", Unicorn::new,
            1, 1, 0, 16777215);
    public static final RegistryObject<EntityType<Kobold>> KOBOLD = createEntity("kobold", Kobold::new,
            1, 1, 9736335, 5196618);
    public static final RegistryObject<EntityType<Goliath>> GOLIATH = createEntity("goliath", Goliath::new,
            3, 8, 9736335, 5196618);

    // Rakia
    public static final RegistryObject<EntityType<RakianSoldier>> RAKIAN_SOLDIER = createEntity("rakian_soldier",
            RakianSoldier::new, 1, 2, 10027008, 16302911);
    public static final RegistryObject<EntityType<RakianBlacksmith>> RAKIAN_BLACKSMITH = createEntity("rakian_blacksmith",
            RakianBlacksmith::new, 1, 2, 10027008, 0);

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
        ResourceLocation location = new ResourceLocation(Main.MODID, name);
        EntityType<T> entity = EntityType.Builder.of(factory, EntityClassification.MONSTER).sized(width, height)
                .setTrackingRange(64).setUpdateInterval(1).build(location.toString());
        Item spawnEgg = new SpawnEggItem(entity, primaryEgg, secondaryEgg, (new Item.Properties()).tab(Main.EggGroup));
        spawnEgg.setRegistryName(new ResourceLocation(Main.MODID, name + "_spawn_egg"));
        SPAWN_EGGS.add(spawnEgg);

        return ENTITY_DEFERRED.register(name, () -> entity);
    }
}
