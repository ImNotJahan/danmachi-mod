package imnotjahan.mod.danmachi.util.compat;

import imnotjahan.mod.danmachi.init.BlockInit;
import imnotjahan.mod.danmachi.init.ItemInit;
import net.minecraftforge.oredict.OreDictionary;

public class OreDict
{
    public static void registerOres()
    {
        OreDictionary.registerOre("oreAdamantite", BlockInit.ADAMANTITE_ORE);
        OreDictionary.registerOre("ingotAdamantite", ItemInit.ADAMANTITE_INGOT);

        OreDictionary.registerOre("oreOrichalcum", BlockInit.ORICHALCUM_ORE);
        OreDictionary.registerOre("ingotOrichalcum", ItemInit.ORICHALCUM_INGOT);

        OreDictionary.registerOre("oreMythril", BlockInit.MYTHRIL_ORE);
        OreDictionary.registerOre("ingotMythril", ItemInit.MYTHRIL_INGOT);
    }
}
