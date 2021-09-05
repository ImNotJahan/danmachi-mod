package imnotjahan.mod.danmachi.util.config;

import imnotjahan.mod.danmachi.util.STDTS;
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

        public final ForgeConfigSpec.ConfigValue<String> statusIncreases;


        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("Monsters");
            this.statusIncreases = builder.comment("The order of stat increases is: strength, endurance, dextarity, agility, magic, excelia")
                    .define("Status Increases", STDTS.SIADictToString(dStatusIncreases));
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
