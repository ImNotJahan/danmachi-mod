package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import imnotjahan.mod.danmachi.util.handlers.SoundHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMinotaur extends MonsterBase
{
    private static final String name = "minotaur";

    public EntityMinotaur(World worldIn)
    {
        super(worldIn, name);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ModConfig.mobStats.get(name)[0]);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.mobStats.get(name)[1]);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ModConfig.mobStats.get(name)[2]);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ModConfig.mobStats.get(name)[3]);
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
