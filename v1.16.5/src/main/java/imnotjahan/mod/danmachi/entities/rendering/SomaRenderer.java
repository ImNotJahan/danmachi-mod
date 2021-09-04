package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.gods.Loki;
import imnotjahan.mod.danmachi.entities.gods.Soma;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SomaRenderer extends MobRenderer<Soma, PlayerModel<Soma>>
{
    public SomaRenderer(EntityRendererManager context)
    {
        super(context, new PlayerModel<Soma>(0, false), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(Soma p_110775_1_)
    {
        return new ResourceLocation(Reference.MODID, "textures/entity/soma.png");
    }
}