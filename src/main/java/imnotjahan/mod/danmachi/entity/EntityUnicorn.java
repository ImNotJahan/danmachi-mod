package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityUnicorn extends MonsterBase
{
    public EntityUnicorn(World worldIn)
    {
        super(worldIn, "unicorn");

        this.setSize(1.3964844F, 1.6F);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    @Override
    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_HORSE_STEP;
    }
}
