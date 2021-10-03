package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.gods.Hestia;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HestiaRenderer extends MobRenderer<Hestia, PlayerModel<Hestia>>
{
    public HestiaRenderer(EntityRendererManager context)
    {
        super(context, new PlayerModel<Hestia>(0, false), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(Hestia p_110775_1_)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/hestia.png");
    }
}