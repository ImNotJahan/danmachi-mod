package imnotjahan.mod.danmachi.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import imnotjahan.mod.danmachi.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockStates extends BlockStateProvider
{
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private final DataGenerator gen;

    public BlockStates(DataGenerator gen)
    {
        super(gen);
        this.gen = gen;
    }

    public static final List<Block> normalBlocks = new ArrayList<Block>()
    {{
        add(Blocks.ADAMANTITE_ORE);
        add(Blocks.ADAMANTITE_BLOCK);
        add(Blocks.ADAMANTITE_WALL);
        add(Blocks.MYTHRIL_ORE);
        add(Blocks.MYTHRIL_BLOCK);
        add(Blocks.ORICHALCUM_ORE);
        add(Blocks.ORICHALCUM_BLOCK);
        add(Blocks.ORICHALCUM_WALL);
        add(Blocks.DAMASCUS_ORE);
        add(Blocks.DAMASCUS_BLOCK);
        add(Blocks.NOSTEEL_ORE);
        add(Blocks.NOSTEEL_BLOCK);
        add(Blocks.DUNGEON_PORTAL);
        add(Blocks.BASALT_ADAMANTITE_ORE);
        add(Blocks.DIRT_ADAMANTITE_ORE);
        add(Blocks.END_ADAMANTITE_ORE);
        add(Blocks.ICE_ADAMANTITE_ORE);
        add(Blocks.NETHER_ADAMANTITE_ORE);
        add(Blocks.SAND_ADAMANTITE_ORE);
    }};

    @Override
    public void run(DirectoryCache cache)
    {
        Path path = this.gen.getOutputFolder();
        Map<Block, IFinishedBlockState> states = Maps.newHashMap();
        Map<ResourceLocation, Supplier<JsonElement>> models = Maps.newHashMap();

        normalBlocks.forEach(block ->
        {
            BlockModelDefinition model = new BlockModelDefinition().with(BlockModelFields.MODEL,
                    new ResourceLocation(block.getRegistryName().getNamespace() + ":block/" + block.getRegistryName().getPath()));
            states.put(block, FinishedVariantBlockState.multiVariant(block, model));
        });

        BiConsumer<ResourceLocation, Supplier<JsonElement>> biconsumer = (p_240086_1_, p_240086_2_) -> { };

        Consumer<IFinishedBlockState> consumer = (p_240085_1_) -> {
            Block block = p_240085_1_.getBlock();
            IFinishedBlockState ifinishedblockstate = states.put(block, p_240085_1_);
            if (ifinishedblockstate != null) {
                throw new IllegalStateException("Duplicate blockstate definition for " + block);
            }
        };

        (new BlockModelGenerator(consumer, biconsumer, states.keySet())).run();

        this.write(cache, path, states, BlockStates::createBlockStatePath);
        this.write(cache, path, models, BlockStates::createModelPath);
    }

    private static Path createModelPath(Path p_240083_0_, ResourceLocation p_240083_1_) {
        return p_240083_0_.resolve("assets/" + p_240083_1_.getNamespace() + "/models/" + p_240083_1_.getPath() + ".json");
    }

    private static Path createBlockStatePath(Path p_240082_0_, Block p_240082_1_)
    {
        ResourceLocation resourcelocation = Registry.BLOCK.getKey(p_240082_1_);
        return p_240082_0_.resolve("assets/" + resourcelocation.getNamespace() + "/blockstates/" + resourcelocation.getPath() + ".json");
    }

    private <T> void write(DirectoryCache p_240081_1_, Path p_240081_2_, Map<T, ? extends Supplier<JsonElement>> p_240081_3_, BiFunction<Path, T, Path> p_240081_4_)
    {
        p_240081_3_.forEach((p_240088_3_, p_240088_4_) ->
        {
            Path path = p_240081_4_.apply(p_240081_2_, p_240088_3_);

            try
            {
                IDataProvider.save(GSON, p_240081_1_, p_240088_4_.get(), path);
            } catch (Exception exception)
            {
                LOGGER.error("Couldn't save {}", path, exception);
            }
        });
    }

    @Override
    public String getName()
    {
        return "Danmachi Block State Provider";
    }


}