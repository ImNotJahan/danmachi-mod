package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityBugbear;
import imnotjahan.mod.danmachi.entity.model.ModelBugbear;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPolarBear;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBugbear extends RenderLiving<EntityBugbear>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bugbear.png");

    public RenderBugbear(RenderManager manager)
    {
        super(manager, new ModelBugbear(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBugbear entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityBugbear entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
