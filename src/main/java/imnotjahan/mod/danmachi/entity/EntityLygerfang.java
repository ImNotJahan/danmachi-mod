package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import imnotjahan.mod.danmachi.util.handlers.SoundHandler;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityLygerfang extends MonsterBase
{
    public EntityLygerfang(World worldIn)
    {
        super(worldIn, "lygerfang");

        this.setSize(0.9F, 1F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundHandler.ENTITY_LYGERFANG_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundHandler.ENTITY_LYGERFANG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundHandler.ENTITY_LYGERFANG_DEATH;
    }
}
