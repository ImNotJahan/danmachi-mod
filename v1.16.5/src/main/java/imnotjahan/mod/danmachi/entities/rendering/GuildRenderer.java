package imnotjahan.mod.danmachi.entities.rendering;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.entities.Goblin;
import imnotjahan.mod.danmachi.entities.GuildMember;
import imnotjahan.mod.danmachi.entities.models.GoblinModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuildRenderer extends MobRenderer<GuildMember, PlayerModel<GuildMember>>
{
    public GuildRenderer(EntityRendererManager context)
    {
        super(context, new PlayerModel<GuildMember>(0, true), 1);
    }

    @Override
    public ResourceLocation getTextureLocation(GuildMember p_110775_1_)
    {
        return new ResourceLocation(Reference.MODID, "textures/entity/guild.png");
    }
}