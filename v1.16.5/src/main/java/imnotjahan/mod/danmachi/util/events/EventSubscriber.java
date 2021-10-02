package imnotjahan.mod.danmachi.util.events;

import com.google.common.base.Preconditions;
import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.*;
import imnotjahan.mod.danmachi.entities.gods.Hermes;
import imnotjahan.mod.danmachi.entities.gods.Hestia;
import imnotjahan.mod.danmachi.entities.gods.Loki;
import imnotjahan.mod.danmachi.entities.gods.Soma;
import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import imnotjahan.mod.danmachi.gui.container.SmithingContainer;
import imnotjahan.mod.danmachi.init.Blocks;
import imnotjahan.mod.danmachi.materials.ArmorMaterials;
import imnotjahan.mod.danmachi.materials.ItemTiers;
import imnotjahan.mod.danmachi.objects.armor.SalamanderType;
import imnotjahan.mod.danmachi.objects.blocks.DungeonPortal;
import imnotjahan.mod.danmachi.objects.blocks.SmithingAnvil;
import imnotjahan.mod.danmachi.objects.armor.HadesHead;
import imnotjahan.mod.danmachi.objects.armor.SalamanderWool;
import imnotjahan.mod.danmachi.objects.armor.SizeableArmor;
import imnotjahan.mod.danmachi.objects.blocks.tileentities.SmithingTile;
import imnotjahan.mod.danmachi.objects.items.MagicStone;
import imnotjahan.mod.danmachi.objects.items.Needle;
import imnotjahan.mod.danmachi.objects.sword.Hakugen;
import imnotjahan.mod.danmachi.objects.sword.SwordAir;
import imnotjahan.mod.danmachi.objects.sword.TooltipSword;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static imnotjahan.mod.danmachi.init.Blocks.SMITHING_ANVIL;
import static imnotjahan.mod.danmachi.init.Entities.*;
import static net.minecraft.item.Items.GLASS_BOTTLE;
import static net.minecraft.item.Items.SMITHING_TABLE;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EventSubscriber
{
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                // ingots
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "orichalcum_ingot"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "adamantite_ingot"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "mythril_ingot"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "damascus_steel"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "nosteel_ingot"),

                // mob drops
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "goblin_fang"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "kobold_nail"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "almiraj_fur"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "lygerfang_fang"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "lygerfang_fur"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "minotaur_horn"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "bugbear_nail"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "metal_rabbit_fur"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "sword_stag_antler"),
                setup(new MagicStone(new Item.Properties().tab(Main.MaterialsGroup)), "magic_stone"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "special_magic_stone"),

                // other
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "ichor"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "urga_blade"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "guide_book"),
                setup(new Item(new Item.Properties().tab(Main.MaterialsGroup)), "grimoire"),
                setup(new Needle(), "needle"),

                // weapons & tools
                //short
                setup(new SwordItem(ItemTiers.HESTIA_KNIFE, 4, -2F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "hestia_knife"),
                setup(new Hakugen(), "hakugen"),
                setup(new SwordItem(ItemTiers.HAKUGEN, 4, -2.2F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "ushiwakamaru"),
                setup(new SwordAir(), "sword_air"),
                setup(new SwordItem(ItemTiers.DAGGER, 4, -2.8F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "dagger"),

                //long
                setup(new SwordItem(ItemTiers.DESPERATE, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "desperate"),
                setup(new SwordItem(ItemTiers.URGA, 4, -4.1F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "urga"),
                setup(new TooltipSword(ItemTiers.MAGIC_SWORD, 4, -3.4F,
                        (new Item.Properties()).tab(Main.ToolGroup),
                        new StringTextComponent("Right click to spray fire")), "fire_magic_sword"),
                setup(new TooltipSword(ItemTiers.MAGIC_SWORD, 4, -3.4F,
                        (new Item.Properties()).tab(Main.ToolGroup),
                        new StringTextComponent("Right click to spread ice")), "ice_magic_sword"),
                setup(new SwordItem(ItemTiers.GREATSWORD, 4, -3.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "greatsword"),
                setup(new SwordItem(ItemTiers.KOTETSU, 4, -2.F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "kotetsu"),
                setup(new SwordItem(ItemTiers.KODACHI_FUTABA, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "kodachi_futaba"),
                setup(new SwordItem(ItemTiers.GREAT_PODAO_ZAGA, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "great_podao_zaga"),
                setup(new SwordItem(ItemTiers.PROTAGONISTA, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "protagonista"),
                setup(new SwordItem(ItemTiers.FORTIA_SPEAR, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "fortia_spear"),
                setup(new SwordItem(ItemTiers.KINGS_BLADE, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "kings_blade"), //change tier
                setup(new SwordItem(ItemTiers.SUPREME_BLACK_SWORD, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "supreme_black_sword"), //change tier
                setup(new SwordItem(ItemTiers.ALFS_LUMINA, 4, -2.4F,
                        (new Item.Properties()).tab(Main.ToolGroup)), "alfs_lumina"), //change tier

                //armor
                setup(new SizeableArmor(ArmorMaterials.RABBIT, EquipmentSlotType.CHEST,
                        (new Item.Properties()).tab(Main.ToolGroup)), "rabbit_armor_chestplate"),
                setup(new SizeableArmor(ArmorMaterials.RABBIT, EquipmentSlotType.LEGS,
                        (new Item.Properties()).tab(Main.ToolGroup)), "rabbit_armor_leggings"),
                setup(new SizeableArmor(ArmorMaterials.RABBIT, EquipmentSlotType.FEET,
                        (new Item.Properties()).tab(Main.ToolGroup)), "rabbit_armor_boots"),

                setup(new SizeableArmor(ArmorMaterials.DRESS, EquipmentSlotType.HEAD,
                        (new Item.Properties()).tab(Main.ToolGroup)), "armor_dress_helmet"),
                setup(new SizeableArmor(ArmorMaterials.DRESS, EquipmentSlotType.CHEST,
                        (new Item.Properties()).tab(Main.ToolGroup)), "armor_dress_chest"),
                setup(new SizeableArmor(ArmorMaterials.DRESS, EquipmentSlotType.LEGS,
                        (new Item.Properties()).tab(Main.ToolGroup)), "armor_dress_leggings"),
                setup(new SizeableArmor(ArmorMaterials.DRESS, EquipmentSlotType.FEET,
                        (new Item.Properties()).tab(Main.ToolGroup)), "armor_dress_boots"),

                setup(new SizeableArmor(ArmorMaterials.AMAZON, EquipmentSlotType.CHEST,
                        (new Item.Properties()).tab(Main.ToolGroup)), "amazon_armor_chest"),
                setup(new SizeableArmor(ArmorMaterials.AMAZON, EquipmentSlotType.LEGS,
                        (new Item.Properties()).tab(Main.ToolGroup)), "amazon_armor_leggings"),
                setup(new SizeableArmor(ArmorMaterials.AMAZON, EquipmentSlotType.FEET,
                        (new Item.Properties()).tab(Main.ToolGroup)), "amazon_armor_boots"),

                setup(new HadesHead(ArmorMaterials.HADES, EquipmentSlotType.HEAD,
                        (new Item.Properties()).tab(Main.ToolGroup)), "hades_head"),

                setup(new SalamanderWool(ArmorMaterials.FIRE_SALAMANDER, SalamanderType.Fire), "fire_salamander_wool"),
                setup(new SalamanderWool(ArmorMaterials.WATER_SALAMANDER, SalamanderType.Water), "water_salamander_wool"),
                setup(new SalamanderWool(ArmorMaterials.POISON_SALAMANDER, SalamanderType.Poison), "poison_salamander_wool"),

                //food
                setup(new Item((new Item.Properties()).tab(Main.MaterialsGroup)
                                .food(imnotjahan.mod.danmachi.init.Foods.JAGAMARUKUN)), "jagamarukun"),
                setup(new Item((new Item.Properties()).tab(Main.MaterialsGroup)
                                .food(imnotjahan.mod.danmachi.init.Foods.CRYSTAL_DROP)), "crystal_drop"),

                //blocks
                setup(new BlockItem(Blocks.ORICHALCUM_BLOCK,
                        (new Item.Properties()).tab(Main.BlockGroup)), "orichalcum_block"),
                setup(new BlockItem(Blocks.ADAMANTITE_BLOCK,
                        (new Item.Properties()).tab(Main.BlockGroup)), "adamantite_block"),
                setup(new BlockItem(Blocks.MYTHRIL_BLOCK,
                        (new Item.Properties()).tab(Main.BlockGroup)), "mythril_block"),
                setup(new BlockItem(Blocks.ORICHALCUM_ORE,
                        (new Item.Properties()).tab(Main.BlockGroup)), "orichalcum_ore"),
                setup(new BlockItem(Blocks.ADAMANTITE_ORE,
                        (new Item.Properties()).tab(Main.BlockGroup)), "adamantite_ore"),
                setup(new BlockItem(Blocks.MYTHRIL_ORE,
                        (new Item.Properties()).tab(Main.BlockGroup)), "mythril_ore"),
                setup(new BlockItem(Blocks.DAMASCUS_ORE,
                        (new Item.Properties()).tab(Main.BlockGroup)), "damascus_ore"),
                setup(new BlockItem(Blocks.NOSTEEL_ORE,
                        (new Item.Properties()).tab(Main.BlockGroup)), "nosteel_ore"),
                setup(new BlockItem(Blocks.ORICHALCUM_WALL,
                        (new Item.Properties()).tab(Main.BlockGroup)), "orichalcum_wall"),
                setup(new BlockItem(Blocks.ADAMANTITE_WALL,
                        (new Item.Properties()).tab(Main.BlockGroup)), "adamantite_wall"),
                setup(new BlockItem(Blocks.DAMASCUS_BLOCK,
                        (new Item.Properties()).tab(Main.BlockGroup)), "damascus_block"),
                setup(new BlockItem(Blocks.NOSTEEL_BLOCK,
                        (new Item.Properties()).tab(Main.BlockGroup)), "nosteel_block"),
                setup(new BlockItem(SMITHING_ANVIL,
                        (new Item.Properties()).tab(Main.BlockGroup)), "smithing_anvil")
            );
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().registerAll(
                setup(new Potion(new EffectInstance(Effects.HEAL, 2, 4)), "mermaid_blood"),
                setup(new Potion(new EffectInstance(Effects.CONFUSION, 600, 4),
                        new EffectInstance(Effects.BLINDNESS, 600, 0),
                        new EffectInstance(Effects.WEAKNESS, 600, 1),
                        new EffectInstance(Effects.HEALTH_BOOST, 600, 4)), "soma")
        );
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(
                setup(new Block(Block.Properties.of(Material.HEAVY_METAL)
                        .strength(50)), "orichalcum_block"),
                setup(new Block(Block.Properties.of(Material.HEAVY_METAL)
                        .strength(20)), "adamantite_block"),
                setup(new Block(Block.Properties.of(Material.METAL)
                        .strength(5)), "mythril_block"),
                setup(new Block(Block.Properties.of(Material.METAL)
                        .strength(5)), "nosteel_block"),
                setup(new Block(Block.Properties.of(Material.METAL)
                        .strength(5)), "damascus_block"),
                setup(new Block(Block.Properties.of(Material.STONE)
                        .strength(25)), "damascus_ore"),
                setup(new Block(Block.Properties.of(Material.STONE)
                        .strength(25)), "nosteel_ore"),
                setup(new Block(Block.Properties.of(Material.STONE)
                        .strength(25)), "orichalcum_ore"),
                setup(new Block(Block.Properties.of(Material.STONE)
                        .strength(10)), "adamantite_ore"),
                setup(new Block(Block.Properties.of(Material.STONE)
                        .strength(3)), "mythril_ore"),
                setup(new Block(Block.Properties.of(Material.HEAVY_METAL)
                        .strength(45)), "orichalcum_wall"),
                setup(new Block(Block.Properties.of(Material.HEAVY_METAL)
                        .strength(15)), "adamantite_wall"),
                setup(new DungeonPortal(), "dungeon_portal"),
                setup(new SmithingAnvil(AbstractBlock.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL)),
                        "smithing_anvil")
        );
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
    {
        return setup(entry, new ResourceLocation(Reference.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }

    //Entities
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
        EntitySpawnPlacementRegistry.register(UNICORN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Unicorn::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(KOBOLD.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, Kobold::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(RAKIAN_SOLDIER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, RakianSoldier::checkMobSpawnRules);
        EntitySpawnPlacementRegistry.register(RAKIAN_BLACKSMITH.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, RakianBlacksmith::checkMobSpawnRules);
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
        event.put(UNICORN.get(), Unicorn.createAttributes().build());
        event.put(KOBOLD.get(), Kobold.createAttributes().build());
        event.put(RAKIAN_SOLDIER.get(), RakianSoldier.createAttributes().build());
        event.put(RAKIAN_BLACKSMITH.get(), RakianBlacksmith.createAttributes().build());
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

    public static ContainerType<SmithingContainer> smithingContainer;
    public static ContainerType<GuildContainer> guildContainer;

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event)
    {
        smithingContainer = IForgeContainerType.create(SmithingContainer::new);
        smithingContainer.setRegistryName("smithing_container");
        event.getRegistry().register(smithingContainer);

        guildContainer = IForgeContainerType.create(GuildContainer::new);
        guildContainer.setRegistryName("guild_container");
        event.getRegistry().register(guildContainer);
    }

    public static TileEntityType<SmithingTile> smithingTile;

    @SubscribeEvent
    public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        smithingTile = TileEntityType.Builder.of(SmithingTile::new, SMITHING_ANVIL).build(null);
        smithingTile.setRegistryName("danmachi:smithing_tile");

        event.getRegistry().register(smithingTile);
    }
}