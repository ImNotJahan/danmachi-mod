package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBugbear extends MonsterBase
{
    private static final String name = "bugbear";

    public EntityBugbear(World worldIn)
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

    private float clientSideStandAnimation0 = 0;
    private float clientSideStandAnimation;
    @SideOnly(Side.CLIENT)
    public float getStandingAnimationScale(float p_189795_1_)
    {
        this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
        return (this.clientSideStandAnimation0 + (this.clientSideStandAnimation - this.clientSideStandAnimation0) * p_189795_1_) / 6.0F;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_POLAR_BEAR_BABY_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_POLAR_BEAR_HURT;
    }
}
