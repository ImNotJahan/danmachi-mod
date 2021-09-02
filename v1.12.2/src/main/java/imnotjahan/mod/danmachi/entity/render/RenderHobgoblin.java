package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityHobgoblin;
import imnotjahan.mod.danmachi.entity.model.ModelHobgoblin;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHobgoblin extends RenderLiving<EntityHobgoblin>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/hobgoblin.png");

    public RenderHobgoblin(RenderManager manager)
    {
        super(manager, new ModelHobgoblin(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHobgoblin entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityHobgoblin entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
