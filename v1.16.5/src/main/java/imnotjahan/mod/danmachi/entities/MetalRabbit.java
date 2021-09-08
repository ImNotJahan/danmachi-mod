package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.RabbitBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;

public class MetalRabbit extends RabbitBase
{
    public MetalRabbit(EntityType<? extends RabbitEntity> rabbit, World world)
    {
        super(rabbit, world, "metal_rabbit");
    }
}
