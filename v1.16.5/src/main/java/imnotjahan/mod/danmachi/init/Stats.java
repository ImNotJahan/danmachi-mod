package imnotjahan.mod.danmachi.init;

import imnotjahan.mod.danmachi.Main;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class Stats
{
    public static final StatType<ResourceLocation> CUSTOM = makeRegistryStatType("custom", Registry.CUSTOM_STAT);
    public static final ResourceLocation LEVEL = makeCustomStat("level", IStatFormatter.DEFAULT);


    private static ResourceLocation makeCustomStat(String p_199084_0_, IStatFormatter p_199084_1_)
    {
        ResourceLocation resourcelocation = new ResourceLocation(Main.MODID, p_199084_0_);
        Registry.register(Registry.CUSTOM_STAT, p_199084_0_, resourcelocation);
        CUSTOM.get(resourcelocation, p_199084_1_);
        return resourcelocation;
    }

    private static <T> StatType<T> makeRegistryStatType(String p_199085_0_, Registry<T> p_199085_1_)
    {
        return Registry.register(Registry.STAT_TYPE, p_199085_0_, new StatType<>(p_199085_1_));
    }
}
