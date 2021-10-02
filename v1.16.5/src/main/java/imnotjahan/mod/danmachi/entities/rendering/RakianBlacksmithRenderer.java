package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.RakianBlacksmith;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RakianBlacksmithRenderer extends MobRenderer<RakianBlacksmith, PlayerModel<RakianBlacksmith>>
{
    public RakianBlacksmithRenderer(EntityRendererManager context)
    {
        super(context, new PlayerModel<>(0, true),
                1);
    }

    @Override
    public ResourceLocation getTextureLocation(RakianBlacksmith soldier)
    {
        int type = soldier.getSoldier();
        return new ResourceLocation(Reference.MODID, "textures/entity/rakia/blacksmith" + type + ".png");
    }
}