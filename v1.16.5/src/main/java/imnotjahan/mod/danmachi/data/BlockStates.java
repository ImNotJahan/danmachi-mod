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
import java.util.function.BiFunction;
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

    @Override
    public void run(DirectoryCache cache)
    {
        Path path = this.gen.getOutputFolder();
        Map<Block, IFinishedBlockState> map = Maps.newHashMap();

        List<Block> normalBlocks = new ArrayList<Block>()
        {{
            add(Blocks.ADAMANTITE_ORE);
        }};

        normalBlocks.forEach(block ->
        {
            BlockModelDefinition model = new BlockModelDefinition().with(BlockModelFields.MODEL,
                    new ResourceLocation(block.getRegistryName().getNamespace() + ":block/" + block.getRegistryName().getPath()));
            map.put(block, FinishedVariantBlockState.multiVariant(block, model));
        });

        this.write(cache, path, map, BlockStates::createBlockStatePath);
    }

    private static Path createBlockStatePath(Path p_240082_0_, Block p_240082_1_) {
        ResourceLocation resourcelocation = Registry.BLOCK.getKey(p_240082_1_);
        return p_240082_0_.resolve("assets/" + resourcelocation.getNamespace() + "/blockstates/" + resourcelocation.getPath() + ".json");
    }

    private <T> void write(DirectoryCache p_240081_1_, Path p_240081_2_, Map<T, ? extends Supplier<JsonElement>> p_240081_3_, BiFunction<Path, T, Path> p_240081_4_) {
        p_240081_3_.forEach((p_240088_3_, p_240088_4_) -> {
            Path path = p_240081_4_.apply(p_240081_2_, p_240088_3_);

            try {
                IDataProvider.save(GSON, p_240081_1_, p_240088_4_.get(), path);
            } catch (Exception exception) {
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