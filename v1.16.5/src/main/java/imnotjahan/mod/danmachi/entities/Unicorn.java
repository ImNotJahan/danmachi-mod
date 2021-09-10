package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.HorseBase;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class Unicorn extends HorseBase
{
    public Unicorn(EntityType<? extends AbstractHorseEntity> p_i48549_1_, World p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_, "unicorn");
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        final double[] attributes = STD.SDAStringToDict(Config.COMMON.mobStats.get()).get("unicorn");

        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, attributes[0])
                .add(Attributes.MAX_HEALTH, attributes[1])
                .add(Attributes.MOVEMENT_SPEED, attributes[2])
                .add(Attributes.ARMOR, attributes[3])
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.HORSE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        return SoundEvents.HORSE_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.HORSE_AMBIENT;
    }
}
