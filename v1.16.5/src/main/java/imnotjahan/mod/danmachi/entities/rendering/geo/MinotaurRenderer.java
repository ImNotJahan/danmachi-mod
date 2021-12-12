package imnotjahan.mod.danmachi.entities.rendering.geo;

import imnotjahan.mod.danmachi.entities.Minotaur;
import imnotjahan.mod.danmachi.entities.models.MinotaurModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MinotaurRenderer extends GeoEntityRenderer<Minotaur>
{
    public MinotaurRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new MinotaurModel());
        shadowStrength = 3;
    }
}
