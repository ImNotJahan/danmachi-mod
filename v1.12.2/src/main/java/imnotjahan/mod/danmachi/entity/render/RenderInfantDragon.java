package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityInfantDragon;
import imnotjahan.mod.danmachi.entity.model.ModelInfantDragon;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInfantDragon extends RenderLiving<EntityInfantDragon>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/infant_dragon.png");

    public RenderInfantDragon(RenderManager manager)
    {
        super(manager, new ModelInfantDragon(0), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityInfantDragon entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityInfantDragon entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
