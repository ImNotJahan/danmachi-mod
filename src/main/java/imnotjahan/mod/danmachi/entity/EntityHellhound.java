package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHellhound extends MonsterBase
{
    private static final String name = "hellhound";

    public EntityHellhound(World worldIn)
    {
        super(worldIn, name);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ModConfig.mobStats.get(name)[0] / 2);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.mobStats.get(name)[1]);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ModConfig.mobStats.get(name)[2]);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ModConfig.mobStats.get(name)[3]);
    }

    @Override
    protected boolean shouldBurnInDay()
    {
        return false;
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
}
