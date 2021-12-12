package imnotjahan.mod.danmachi.entities.rendering.geo;

import imnotjahan.mod.danmachi.entities.Hellhound;
import imnotjahan.mod.danmachi.entities.Minotaur;
import imnotjahan.mod.danmachi.entities.models.HellhoundModel;
import imnotjahan.mod.danmachi.entities.models.MinotaurModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HellhoundRenderer extends GeoEntityRenderer<Hellhound>
{
    public HellhoundRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HellhoundModel());
        shadowStrength = 3;
    }
}
