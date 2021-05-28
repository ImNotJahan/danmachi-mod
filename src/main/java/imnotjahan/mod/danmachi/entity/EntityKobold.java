package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityKobold extends MonsterBase
{
    public EntityKobold(World worldIn)
    {
        super(worldIn, "kobold");
    }

    @Override
    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_WOLF_STEP;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_WOLF_GROWL;
    }

    @Override
    public boolean isArmsRaised()
    {
        return true;
    }
}
