package imnotjahan.mod.danmachi.entities.models;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.Goliath;
import imnotjahan.mod.danmachi.entities.Minotaur;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MinotaurModel extends AnimatedGeoModel<Minotaur>
{
    @Override
    public ResourceLocation getModelLocation(Minotaur object)
    {
        return new ResourceLocation(Main.MODID, "geo/minotaur.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Minotaur object)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/minotaur.png");
    }

    /**
     * This resource location needs to point to a json file of your animation file,
     * i.e. "geckolib:animations/frog_animation.json"
     *
     * @param animatable
     * @return the animation file location
     */
    @Override
    public ResourceLocation getAnimationFileLocation(Minotaur animatable)
    {
        return new ResourceLocation(Main.MODID, "animations/goliath.animation.json");
    }
}
