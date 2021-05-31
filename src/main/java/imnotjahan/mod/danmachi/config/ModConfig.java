package imnotjahan.mod.danmachi.config;

import imnotjahan.mod.danmachi.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

@Config(modid = Reference.MODID)
public class ModConfig
{
    @Config.LangKey("config.danmachi.drop_magic_crystals")
    public static boolean dropMagicCrystals = true;

    @Config.RequiresWorldRestart
    @Config.LangKey("config.danmachi.generate_caves_in_dungeon")
    @Config.Comment("Makes it so if you break outside of the dungeon while still in the dungeon dimension they'll be caves")
    public static boolean generateCavesInDungeon = true;

    @Config.RequiresWorldRestart
    @Config.LangKey("config.danmachi.dungeon_height")
    @Config.Comment("Can't be over max build height")
    public static int dungeonHeight = 256;

    @Config.RequiresWorldRestart
    @Config.LangKey("config.danmachi.dungeon_floor_height")
    public static int dungeonFloorHeight = 10;

    @Config.RequiresMcRestart
    @Config.LangKey("config.danmachi.spawn_monsters_in_overworld")
    public static boolean spawnMonstersInOverworld = false;

    @Config.LangKey("config.danmachi.required_skill_excelia")
    public static int requiredSkillExcelia = 10;

    @Config.LangKey("config.danmachi.skill_chance")
    @Config.Comment("The percent chance of you getting a skill when updating your status after getting the required amount of excelia")
    public static float skillChance = .01f;

    @Config.LangKey("config.danmachi.overworld_monster_spawn_chance")
    @Config.Comment({"A value from 1 to 0, the lower the number the less " +
            "the chance a monster from the dungeon will appear in the overworld", "Will be ignored if spawnMonstersInOverworld is false"})
    public static float overworldMonsterSpawnChance = 0.1f;

    @Config.LangKey("config.danmachi.generate_adamantite_floor")
    public static int generateAdamantiteFloor = 10;

    @Config.LangKey("config.danmachi.generate_orichalcum_floor")
    public static int generateOrichalcumFloor = 18;

    @Config.Comment("The order of stat increases is: strength, endurance, dextarity, agility, magic, excelia")
    @Config.LangKey("config.danmachi.status_increases")
    public static Map<String, int[]> statusIncreases = new HashMap<String, int[]>(){{
        put("goblin", new int[]{2, 2, 1, 1, 0, 2});
        put("kobold", new int[]{2, 2, 1, 2, 0, 2});
        put("killer_ant", new int[]{2, 3, 4, 1, 0, 3});
        put("needle_rabbit", new int[]{2, 3, 1, 4, 0, 2});
        put("almiraj", new int[]{2, 2, 3, 5, 0, 3});
        put("lygerfang", new int[]{2, 3, 2, 5, 0, 4});
        put("minotaur", new int[]{6, 6, 2, 4, 0, 6});
        put("unicorn", new int[]{4, 4, 2, 6, 0, 6});
        put("hellhound", new int[]{5, 4, 5, 6, 0, 6});
    }};

    @Config.Comment("The order of their stats is: dmg, health, speed, armor")
    @Config.LangKey("config.danmachi.mob_stats")
    public static Map<String, double[]> mobStats = new HashMap<String, double[]>(){{
        put("goblin", new double[]{3, 10, .25D, 2});
        put("kobold", new double[]{4, 7, .26D, 2});
        put("killer_ant", new double[]{5, 5, .2D, 4});
        put("minotaur", new double[]{14, 10, .25D, 7});
        put("lygerfang", new double[]{10, 15, .4D, 3});
        put("hellhound", new double[]{8, 10, .3D, 2});
        put("unicorn", new double[]{10, 20, .45D, 2});
        put("needle_rabbit", new double[]{8, 12, .3D, 2});
        put("almiraj", new double[]{8, 20, .3D, 2});
    }};

    @Config.LangKey("config.danmachi.guild_trades")
    public static Map<String, Integer> guildTrades = new HashMap<String, Integer>(){{
        put("item.goblin_fang", 3);
        put("item.lygerfang_fang", 5);
        put("item.kobold_nail", 3);
        put("item.almiraj_fur", 4);
        put("item.lygerfang_fur", 5);
        put("item.minotaur_horn", 7);
        put("item.unicorn_horn", 10);
    }};

    @Config.LangKey("config.danmachi.magic_stone_trades")
    public static Map<String, Integer> magicStoneTrades = new HashMap<String, Integer>(){{
        put("goblin", 1);
        put("lygerfang", 4);
        put("kobold", 1);
        put("almiraj", 2);
        put("killer ant", 2);
        put("minotaur", 5);
        put("unicorn", 7);
        put("hellhound", 3);
    }};

    @Config.LangKey("config.danmachi.required_smithing_dex")
    public static Map<String, Integer> requiredSmithingDex = new HashMap<String, Integer>(){{
        put("item.fire_magic_sword", 1);
        put("item.ice_magic_sword", 1);
        put("item.greatsword", 1);
        put("item.kotetsu", 1);
        put("item.desperate", 1);
        put("item.kodachi_futaba", 1);
        put("item.great_podao_zaga", 1);
        put("item.protagonista", 1);
        put("item.urga", 1);
        put("item.urga_blade", 1);
        put("item.hakugen", 1);
        put("item.hestia_knife", 1);
        put("item.dagger", 1);
        put("item.ushiwakamaru", 1);
        put("item.sword_air", 1);
        put("item.fortia_spear", 1);
    }};

    @Config.LangKey("config.danmachi.ore_generation_chances")
    public static Map<String, Integer> oreGenerationChances = new HashMap<String, Integer>(){{
        put("mythril", 15);
        put("adamantite", 7);
        put("orichalcum", 3);
    }};

    @Config.LangKey("config.danmachi.guide_book_text")
    @Config.Comment({"Every entry is a page",
            "You can also add special text by using json"})
    public static String[] guideBookText = new String[]{"[{\"text\":\"Monsters\",\"underlined\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":2}},{\"text\":\"\n\n\",\"color\":\"reset\"},{\"text\":\"Status\",\"underlined\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":11}},{\"text\":\"\n\n\",\"color\":\"reset\"},{\"text\":\"Smithing\",\"underlined\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":13}},{\"text\":\"\n\n\",\"color\":\"reset\"},{\"text\":\"Gods\",\"underlined\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":14}}]",
            "[{\"text\":\"Goblin\", \"bold\":\"true\"}, {\"text\":\"\nA green and plump humanoid monster with large eyes that is considered to be the weakest monster in the dungeon.\nDrops goblin fangs\", \"bold\":\"false\"}]]",
            "[{\"text\":\"Kobold\", \"bold\":\"true\"}, {\"text\":\"\nA dog headed monster\nDrops kobold nails\", \"bold\":\"false\"}]",
            "[{\"text\":\"Killer Ant\", \"bold\":\"true\"}, {\"text\":\"\nA red ant monster with four legs and two arms. It has a hard shell and has high attack, causing it to be called a new adventurer killer. It can use pheromones to attract companions in a pinch\", \"bold\":\"false\"}]",
            "[{\"text\":\"Needle Rabbit\", \"bold\":\"true\"}, {\"text\":\"\nA rabbit monster that has a horn on its head\", \"bold\":\"false\"}]",
            "[{\"text\":\"Almiraj\", \"bold\":\"true\"}, {\"text\":\"\nA rabbit monster with white fur and a horn. Contrary to its cute looks, it likes to battle. It is one of the weakest monsters in the middle floors. Level 1 adventurers can hold their own against one, but it is classified as a\", \"bold\":\"false\"}]",
            "[{\"text\":\"level 2 monster when together in a group\nDrops almiraj fur\"}]",
            "[{\"text\":\"Minotaur\", \"bold\":\"true\"}, {\"text\":\"\nA cow headed humanoid monster that is one of the strongest monsters in the Middle Floors\nDrops minotaur horn\", \"bold\":\"false\"}]",
            "[{\"text\":\"Lygerfang\", \"bold\":\"true\"}, {\"text\":\"\nA tiger monster\nDrops lygerfang fur\", \"bold\":\"false\"}]",
            "[{\"text\":\"Unicorn\", \"bold\":\"true\"}, {\"text\":\"\nAn extremely rare monster that is very rarely encountered even by high level adventurers\nDrops unicorn horns\", \"bold\":\"false\"}]",
            "[{\"text\":\"Status attributes\", \"bold\":\"true\"}, {\"text\":\"\n\u2014Strength\nIncreases how much damage you do to enemies\n\n\u2014Endurance\nIncreases max health\n\n\u2014Dexterity\nIncreases chance of forging weapons\n\n\", \"bold\":\"false\"}]",
            "[{\"text\":\"\u2014Agility\nIncreases speed\n\n\u2014Magic\nIncreases the power of spells\"}]",
            "[{\"text\":\"Smithing anvil\", \"bold\":\"true\"}, {\"text\":\"\", \"bold\":\"false\"}]",
            "[{\"text\":\"Hestia\", \"bold\":\"true\"}, {\"text\":\"\", \"bold\":\"false\"}]"};

    @Config.RequiresMcRestart
    @Config.LangKey("config.danmachi.surface_spawn_rates")
    @Config.Comment("They can go from 100 to 0")
    public static Map<String, Integer> surfaceSpawnRates = new HashMap<String, Integer>(){{
        put("goblin", 30);
        put("kobold", 30);
        put("killer_ant", 5);
        put("needle_rabbit", 5);
        put("almiraj", 2);
        put("lygerfang", 1);
        put("minotaur", 1);
        put("unicorn", 1);
        put("hellhound", 2);
    }};

    @Config.RequiresMcRestart
    @Config.LangKey("config.danmachi.spawn_rates")
    @Config.Comment({"They can go from 100 to 0",
            "Keep in mind that mobs only spawn on certain floors in the dungeon, so the spawn rate is only for if the mob rarity on that floor not the entire dungeon"})
    public static Map<String, Integer> spawnRates = new HashMap<String, Integer>(){{
        put("goblin", 100);
        put("kobold", 100);
        put("killer_ant", 100);
        put("needle_rabbit", 100);
        put("almiraj", 100);
        put("lygerfang", 100);
        put("minotaur", 100);
        put("unicorn", 10);
        put("hellhound", 100);
    }};

    @Config.RequiresMcRestart
    @Config.LangKey("config.danmachi.generate_custom_structures")
    public static boolean generateCustomStructures = true;

    @Config.RequiresMcRestart
    @Config.LangKey("config.danmachi.god_house_generation_chance")
    @Config.Comment("The higher the number the less chance of it generating")
    public static int godHouseGenerationChance = 300;

    @Config.LangKey("config.danmachi.stat_multiplier")
    public static int statMultiplier = 1;

    @Mod.EventBusSubscriber(modid = Reference.MODID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MODID)) {
                ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
