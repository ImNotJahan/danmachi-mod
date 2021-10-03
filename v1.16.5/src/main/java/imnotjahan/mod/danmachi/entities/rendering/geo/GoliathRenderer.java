package imnotjahan.mod.danmachi.entities.rendering.geo;

import imnotjahan.mod.danmachi.entities.Goliath;
import imnotjahan.mod.danmachi.entities.models.GoliathModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GoliathRenderer extends GeoEntityRenderer<Goliath>
{
    public GoliathRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new GoliathModel());
        shadowStrength = 3;
    }
}
