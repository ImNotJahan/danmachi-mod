package imnotjahan.mod.danmachi.util.config;

import imnotjahan.mod.danmachi.util.STD;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class Config
{
    public static class Common
    {
        //d stands for default
        public static final Map<String, Integer[]> dStatusIncreases = new HashMap<String, Integer[]>()
        {{
            put("goblin", new Integer[]{2, 2, 1, 1, 0, 2});
            put("kobold", new Integer[]{2, 2, 1, 2, 0, 2});
            put("killer_ant", new Integer[]{2, 3, 4, 1, 0, 3});
            put("needle_rabbit", new Integer[]{2, 3, 1, 4, 0, 2});
            put("almiraj", new Integer[]{2, 2, 3, 5, 0, 3});
            put("lygerfang", new Integer[]{2, 3, 2, 5, 0, 4});
            put("minotaur", new Integer[]{6, 6, 2, 4, 0, 6});
            put("unicorn", new Integer[]{4, 4, 2, 6, 0, 6});
            put("hellhound", new Integer[]{5, 4, 5, 6, 0, 6});
            put("infant_dragon", new Integer[]{5, 4, 5, 6, 0, 6}); //change this
            put("dragon", new Integer[]{5, 4, 5, 6, 0, 6}); //change this
            put("bugbear", new Integer[]{6, 6, 2, 6, 0, 7});
            put("hobgoblin", new Integer[]{6, 6, 2, 6, 0, 7}); //change this
            put("sword_stag", new Integer[]{6, 6, 2, 6, 0, 7}); //change this
            put("metal_rabbit", new Integer[]{6, 6, 2, 6, 0, 7}); //change this
        }};

        public static Map<String, double[]> dMobStats = new HashMap<String, double[]>(){{
            put("goblin", new double[]{3, 10, .25D, 2});
            put("kobold", new double[]{4, 7, .26D, 2});
            put("killer_ant", new double[]{5, 5, .2D, 4});
            put("minotaur", new double[]{14, 10, .25D, 7});
            put("lygerfang", new double[]{10, 15, .4D, 3});
            put("hellhound", new double[]{8, 10, .3D, 2});
            put("unicorn", new double[]{10, 20, .45D, 2});
            put("needle_rabbit", new double[]{8, 12, .3D, 2});
            put("almiraj", new double[]{8, 20, .3D, 2});
            put("infant_dragon", new double[]{8, 20, .3D, 2}); //change this
            put("dragon", new double[]{8, 20, .3D, 2}); //change this
            put("bugbear", new double[]{14, 10, .3D, 7});
            put("hobgoblin", new double[]{14, 13, .25D, 4});
            put("metal_rabbit", new double[]{10, 5, .4D, 10});
            put("sword_stag", new double[]{15, 20, .35D, 3});
        }};

        public static Map<String, Integer> dRequiredSmithingDex = new HashMap<String, Integer>(){{
            put("danmachi:fire_magic_sword", 2000);
            put("danmachi:ice_magic_sword", 2000);
            put("danmachi:greatsword", 300);
            put("danmachi:kotetsu", 1000);
            put("danmachi:desperate", 3000);
            put("danmachi:kodachi_futaba", 1000);
            put("danmachi:great_podao_zaga", 700);
            put("danmachi:protagonista", 1000);
            put("danmachi:urga", 300);
            put("danmachi:urga_blade", 3000);
            put("danmachi:hakugen", 1000);
            put("danmachi:hestia_knife", 5000);
            put("danmachi:dagger", 0);
            put("danmachi:ushiwakamaru", 1000);
            put("danmachi:sword_air", 500);
            put("danmachi:fortia_spear", 1400);
        }};


        public final ForgeConfigSpec.ConfigValue<String> statusIncreases;
        public final ForgeConfigSpec.ConfigValue<String> mobStats;
        public final ForgeConfigSpec.ConfigValue<String> requiredSmithingDex; //Dex stands for dexterity


        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("Monsters");
            this.statusIncreases = builder.comment("The order of stat increases is: strength, endurance, dextarity, agility, magic, excelia")
                    .define("Status Increases", STD.SIADictToString(dStatusIncreases));
            this.mobStats = builder.comment("The order of their stats is: dmg, health, speed, armor")
                    .define("Mob Stats", STD.SDADictToString(dMobStats));
            this.requiredSmithingDex = builder.define("Required Smithing Dexterity", STD.SIDictToString(dRequiredSmithingDex));
            builder.pop();
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }
}
