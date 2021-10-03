package imnotjahan.mod.danmachi.entities.models;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.Goliath;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoliathModel extends AnimatedGeoModel<Goliath>
{
    @Override
    public ResourceLocation getModelLocation(Goliath object)
    {
        return new ResourceLocation(Main.MODID, "geo/goliath.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Goliath object)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/goliath.png");
    }

    /**
     * This resource location needs to point to a json file of your animation file,
     * i.e. "geckolib:animations/frog_animation.json"
     *
     * @param animatable
     * @return the animation file location
     */
    @Override
    public ResourceLocation getAnimationFileLocation(Goliath animatable)
    {
        return new ResourceLocation(Main.MODID, "animations/goliath.animation.json");
    }
}
