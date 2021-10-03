package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.gods.Hermes;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HermesRenderer extends MobRenderer<Hermes, PlayerModel<Hermes>>
{
    public HermesRenderer(EntityRendererManager context)
    {
        super(context, new PlayerModel<Hermes>(0, true), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(Hermes p_110775_1_)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/hermes.png");
    }
}