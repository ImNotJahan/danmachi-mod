package imnotjahan.mod.danmachi.init;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.entity.*;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void RegisterEntities()
    {
        RegisterEntity("goblin", EntityGoblin.class, Reference.GOBLIN_ID, 32806,9240576);
        RegisterEntity("kobold", EntityKobold.class, Reference.KOBOLD_ID, 9736335,5196618);
        RegisterEntity("killer_ant", EntityKillerAnt.class, Reference.KILLER_ANT, 9520427, 5261118);
        RegisterEntity("needle_rabbit", EntityNeedleRabbit.class, Reference.NEEDLE_RABBIT, 16777215, 11796480);
        RegisterEntity("almiraj", EntityAlmiraj.class, Reference.ALMIRAJ, 11796480, 16777215);
        RegisterEntity("minotaur", EntityMinotaur.class, Reference.MINOTAUR, 6968398, 14111828);
        RegisterEntity("lygerfang", EntityLygerfang.class, Reference.LYGERFANG, 12025153, 2761239);
        RegisterEntity("unicorn", EntityUnicorn.class, Reference.UNICORN, 0, 16777215);
        RegisterEntity("hestia", EntityHestia.class, Reference.HESTIA, 15527148, 4609420);
        RegisterEntity("loki", EntityLoki.class, Reference.LOKI, 2105895, 5530296);
        RegisterEntity("guild_member", EntityGuildMember.class, Reference.GUILD_MEMBER, 9849600, 16766720);
        RegisterEntity("hellhound", EntityHellhound.class, Reference.HELLHOUND, 0, 16711680);
        RegisterEntity("dealer", EntityDealer.class, Reference.BLACK_MARKET, 2105895, 5530296);

        if(ModConfig.spawnMonstersInOverworld)
        {
            EntityRegistry.addSpawn(EntityKobold.class, ModConfig.surfaceSpawnRates.get("kobold"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityGoblin.class, ModConfig.surfaceSpawnRates.get("goblin"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityUnicorn.class, ModConfig.surfaceSpawnRates.get("unicorn"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityAlmiraj.class, ModConfig.surfaceSpawnRates.get("almiraj"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityNeedleRabbit.class, ModConfig.surfaceSpawnRates.get("needle_rabbit"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityKillerAnt.class, ModConfig.surfaceSpawnRates.get("killer_ant"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityMinotaur.class, ModConfig.surfaceSpawnRates.get("minotaur"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityLygerfang.class, ModConfig.surfaceSpawnRates.get("lygerfang"), 0, 10, EnumCreatureType.MONSTER);
            EntityRegistry.addSpawn(EntityHellhound.class, ModConfig.surfaceSpawnRates.get("hellhound"), 0, 10, EnumCreatureType.MONSTER);
        }
    }

    private static void RegisterEntity(String name, Class<? extends Entity> entity, int id, int color, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, 100, 1, true, color, color2);
    }
}
