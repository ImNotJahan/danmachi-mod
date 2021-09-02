package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntitySwordStag;
import imnotjahan.mod.danmachi.entity.EntityUnicorn;
import imnotjahan.mod.danmachi.entity.model.ModelSwordStag;
import imnotjahan.mod.danmachi.entity.model.ModelUnicorn;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSwordStag extends RenderLiving<EntitySwordStag>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/sword_stag.png");

    public RenderSwordStag(RenderManager manager)
    {
        super(manager, new ModelSwordStag(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySwordStag entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntitySwordStag entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
