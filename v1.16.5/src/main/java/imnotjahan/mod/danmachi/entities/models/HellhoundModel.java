package imnotjahan.mod.danmachi.entities.models;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.entities.Hellhound;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HellhoundModel extends AnimatedGeoModel<Hellhound>
{
    @Override
    public ResourceLocation getModelLocation(Hellhound object)
    {
        return new ResourceLocation(Main.MODID, "geo/hellhound.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Hellhound object)
    {
        return new ResourceLocation(Main.MODID, "textures/entity/hellhound.png");
    }

    /**
     * This resource location needs to point to a json file of your animation file,
     * i.e. "geckolib:animations/frog_animation.json"
     *
     * @param animatable
     * @return the animation file location
     */
    @Override
    public ResourceLocation getAnimationFileLocation(Hellhound animatable)
    {
        return new ResourceLocation(Main.MODID, "animations/goliath.animation.json");
    }
}
