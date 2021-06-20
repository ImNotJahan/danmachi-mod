package imnotjahan.mod.danmachi.util.handlers;

import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.HashMap;
import java.util.Map;

public class LootTableHandler
{
    public static final Map<String, ResourceLocation> mobDrops = new HashMap<String, ResourceLocation>(){{
        put("goblin", LootTableList.register(new ResourceLocation(Reference.MODID, "goblin")));
        put("kobold", LootTableList.register(new ResourceLocation(Reference.MODID, "kobold")));
        put("unicorn", LootTableList.register(new ResourceLocation(Reference.MODID, "unicorn")));
        put("needle_rabbit", LootTableList.register(new ResourceLocation(Reference.MODID, "needle_rabbit")));
        put("almiraj", LootTableList.register(new ResourceLocation(Reference.MODID, "almiraj")));
        put("minotaur", LootTableList.register(new ResourceLocation(Reference.MODID, "minotaur")));
        put("lygerfang", LootTableList.register(new ResourceLocation(Reference.MODID, "lygerfang")));
        put("killer_ant", LootTableList.register(new ResourceLocation(Reference.MODID, "killer_ant")));
        put("hellhound", LootTableList.register(new ResourceLocation(Reference.MODID, "hellhound")));
        put("dragon", LootTableList.register(new ResourceLocation(Reference.MODID, "dragon")));
        put("infant_dragon", LootTableList.register(new ResourceLocation(Reference.MODID, "infant_dragon")));
    }};

    public static final Map<String, ResourceLocation> magicStones = new HashMap<String, ResourceLocation>(){{
        put("goblin", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/goblin")));
        put("kobold", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/kobold")));
        put("unicorn", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/unicorn")));
        put("needle_rabbit", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/needle_rabbit")));
        put("almiraj", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/almiraj")));
        put("minotaur", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/minotaur")));
        put("lygerfang", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/lygerfang")));
        put("killer_ant", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/killer_ant")));
        put("hellhound", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/hellhound")));
        put("dragon", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/dragon")));
        put("infant_dragon", LootTableList.register(new ResourceLocation(Reference.MODID, "stones/infant_dragon")));
    }};
}
