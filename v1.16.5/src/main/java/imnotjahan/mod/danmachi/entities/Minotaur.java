package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.MonsterBase;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.World;

public class Minotaur extends MonsterBase
{
    public Minotaur(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_, "minotaur");
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        final double[] attributes = STD.SDAStringToDict(Config.COMMON.mobStats.get()).get("minotaur");

        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, attributes[0])
                .add(Attributes.MAX_HEALTH, attributes[1])
                .add(Attributes.MOVEMENT_SPEED, attributes[2])
                .add(Attributes.ARMOR, attributes[3]);
    }
}
