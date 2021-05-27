package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityHellhound;
import imnotjahan.mod.danmachi.entity.model.ModelHellhound;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHellhound extends RenderLiving<EntityHellhound>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/hellhound.png");

    public RenderHellhound(RenderManager manager)
    {
        super(manager, new ModelHellhound(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHellhound entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityHellhound entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
