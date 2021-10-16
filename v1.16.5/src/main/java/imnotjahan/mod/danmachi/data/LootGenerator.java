package imnotjahan.mod.danmachi.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootGenerator extends LootTableProvider
{
    public LootGenerator(DataGenerator generator)
    {
        super(generator);
    }

    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker context)
    {
        map.forEach((name, table) -> LootTableManager.validate(context, name, table));
    }

    @Override
    public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
    {
        return ImmutableList.of(Pair.of(ModLootTables::new, LootParameterSets.BLOCK));
    }

    @Override
    public String getName()
    {
        return "Danmachi mod loot tables";
    }
}