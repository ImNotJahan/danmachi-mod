package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityDealer;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDealer extends RenderLiving<EntityDealer>
{
    public RenderDealer(RenderManager manager)
    {
        super(manager, new ModelPlayer(0, false), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDealer entity)
    {
        return new ResourceLocation(Reference.MODID + ":textures/entity/dealer.png");

    }

    @Override
    protected void applyRotations(EntityDealer entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
