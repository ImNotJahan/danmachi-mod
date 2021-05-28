package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import imnotjahan.mod.danmachi.util.handlers.SoundHandler;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMinotaur extends MonsterBase
{
    public EntityMinotaur(World worldIn)
    {
        super(worldIn, "minotaur");
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundHandler.ENTITY_MINOTAUR_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundHandler.ENTITY_MINOTAUR_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundHandler.ENTITY_MINOTAUR_HURT;
    }
}
