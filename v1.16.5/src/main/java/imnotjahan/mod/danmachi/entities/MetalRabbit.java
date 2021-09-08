package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.RabbitBase;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;

public class MetalRabbit extends RabbitBase
{
    public MetalRabbit(EntityType<? extends RabbitEntity> rabbit, World world)
    {
        super(rabbit, world, "metal_rabbit");
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        final double[] attributes = STD.SDAStringToDict(Config.COMMON.mobStats.get()).get("metal_rabbit");

        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, attributes[0])
                .add(Attributes.MAX_HEALTH, attributes[1])
                .add(Attributes.MOVEMENT_SPEED, attributes[2])
                .add(Attributes.ARMOR, attributes[3]);
    }
}
