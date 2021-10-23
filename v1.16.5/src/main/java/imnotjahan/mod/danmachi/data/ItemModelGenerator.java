package imnotjahan.mod.danmachi.data;

import com.google.gson.JsonElement;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ItemModelGenerator extends ItemModelProvider
{
    public ItemModelGenerator(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer)
    {
        super(consumer);
        output = consumer;
    }

    @Override
    public void run()
    {
        //this.generateFlatItem(StockModelShapes.FLAT_ITEM);
    }
    private void generateFlatItem(Item p_240076_1_, ModelsUtil p_240076_2_) {
        p_240076_2_.create(ModelsResourceUtil.getModelLocation(p_240076_1_), ModelTextures.layer0(p_240076_1_), this.output);
    }

    private void generateFlatItem(Item p_240077_1_, String p_240077_2_, ModelsUtil p_240077_3_) {
        p_240077_3_.create(ModelsResourceUtil.getModelLocation(p_240077_1_, p_240077_2_), ModelTextures.layer0(ModelTextures.getItemTexture(p_240077_1_, p_240077_2_)), this.output);
    }

    private void generateFlatItem(Item p_240075_1_, Item p_240075_2_, ModelsUtil p_240075_3_) {
        p_240075_3_.create(ModelsResourceUtil.getModelLocation(p_240075_1_), ModelTextures.layer0(p_240075_2_), this.output);
    }

    private final BiConsumer<ResourceLocation, Supplier<JsonElement>> output;
}
