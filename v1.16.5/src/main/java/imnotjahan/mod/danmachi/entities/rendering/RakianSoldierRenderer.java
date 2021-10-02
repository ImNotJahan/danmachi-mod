package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.Goblin;
import imnotjahan.mod.danmachi.entities.RakianSoldier;
import imnotjahan.mod.danmachi.entities.models.GoblinModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RakianSoldierRenderer extends MobRenderer<RakianSoldier, PlayerModel<RakianSoldier>>
{
    public RakianSoldierRenderer(EntityRendererManager context)
    {
        super(context, new PlayerModel<>(0, true),
                1);
    }

    @Override
    public ResourceLocation getTextureLocation(RakianSoldier soldier)
    {
        int type = soldier.getSoldier();
        return new ResourceLocation(Reference.MODID, "textures/entity/rakia/soldier" + type + ".png");
    }
}