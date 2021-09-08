package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.RabbitBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;

public class Almiraj extends RabbitBase
{
    public Almiraj(EntityType<? extends RabbitEntity> rabbit, World world)
    {
        super(rabbit, world, "almiraj");
    }
}
