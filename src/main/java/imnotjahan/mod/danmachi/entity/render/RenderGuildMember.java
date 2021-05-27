package imnotjahan.mod.danmachi.entity.render;

import imnotjahan.mod.danmachi.entity.EntityGuildMember;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

public class RenderGuildMember extends RenderLiving<EntityGuildMember>
{
    public RenderGuildMember(RenderManager manager)
    {
        super(manager, new ModelPlayer(0, true), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGuildMember entity)
    {
        return new ResourceLocation(Reference.MODID + ":textures/entity/guild.png");

    }

    @Override
    protected void applyRotations(EntityGuildMember entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
