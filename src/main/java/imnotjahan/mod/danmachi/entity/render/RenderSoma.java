package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityLoki;
import imnotjahan.mod.danmachi.entity.EntitySoma;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSoma extends RenderLiving<EntitySoma>
{
    public RenderSoma(RenderManager manager)
    {
        super(manager, new ModelPlayer(0, true), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySoma entity)
    {
        return new ResourceLocation(Reference.MODID + ":textures/entity/soma.png");

    }

    @Override
    protected void applyRotations(EntitySoma entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
