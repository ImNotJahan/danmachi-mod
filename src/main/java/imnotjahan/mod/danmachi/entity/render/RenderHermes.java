package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityHermes;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHermes extends RenderLiving<EntityHermes>
{
    public RenderHermes(RenderManager manager)
    {
        super(manager, new ModelPlayer(0, false), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHermes entity)
    {
        return new ResourceLocation(Reference.MODID + ":textures/entity/hermes.png");

    }

    @Override
    protected void applyRotations(EntityHermes entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
