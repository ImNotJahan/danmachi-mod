package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class Bugbear extends PolarBearEntity
{

    public Bugbear(EntityType<? extends PolarBearEntity> p_i50249_1_, World p_i50249_2_)
    {
        super(p_i50249_1_, p_i50249_2_);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        final double[] attributes = STD.SDAStringToDict(Config.COMMON.mobStats.get()).get("bugbear");

        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, attributes[0])
                .add(Attributes.MAX_HEALTH, attributes[1])
                .add(Attributes.MOVEMENT_SPEED, attributes[2])
                .add(Attributes.ARMOR, attributes[3]);
    }

    @Override
    public void die(DamageSource cause)
    {
        super.die(cause);

        if(cause.getEntity() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getEntity();
            IStatus status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                    .orElseThrow(MissingStatus::new);

            if (status.getFalna())
            {
                Integer[] statusIncreases = STD.SIAStringToDict(
                        Config.COMMON.statusIncreases.get()).get("bugbear");

                for (int k = 0; k < 5; k++)
                {
                    status.increase(statusIncreases[k], Status.POTENTIAL_START + k);
                }

                status.increase(statusIncreases[5], 7);
            } else
            {
                return;
            }

        }
    }

    @Override
    public boolean isBaby()
    {
        return false;
    }

    @Override
    public boolean canBeLeashed(PlayerEntity player)
    {
        return true;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new Bugbear.MeleeAttackGoal());
        this.goalSelector.addGoal(1, new Bugbear.PanicGoal());
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new Bugbear.HurtByTargetGoal());
        this.targetSelector.addGoal(2, new Bugbear.AttackPlayerGoal());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (Predicate<LivingEntity>) null));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }


    //Goals

    class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity>
    {
        public AttackPlayerGoal()
        {
            super(Bugbear.this, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
        }

        public boolean canUse()
        {
            if (super.canUse())
            {
                for(PolarBearEntity polarbearentity : Bugbear.this.level.getEntitiesOfClass(PolarBearEntity.class, Bugbear.this.getBoundingBox().inflate(8.0D, 4.0D, 8.0D)))
                {
                    if (polarbearentity.isBaby())
                    {
                        return true;
                    }
                }
            }

            return false;
        }

        protected double getFollowDistance() {
            return super.getFollowDistance() * 0.5D;
        }
    }

    class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal
    {
        public HurtByTargetGoal() {
            super(Bugbear.this);
        }

        public void start() {
            super.start();
        }

        protected void alertOther(MobEntity p_220793_1_, LivingEntity p_220793_2_) {
            if (p_220793_1_ instanceof PolarBearEntity) {
                super.alertOther(p_220793_1_, p_220793_2_);
            }

        }
    }

    class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
        public MeleeAttackGoal() {
            super(Bugbear.this, 1.25D, true);
        }

        protected void checkAndPerformAttack(LivingEntity p_190102_1_, double p_190102_2_) {
            double d0 = this.getAttackReachSqr(p_190102_1_);
            if (p_190102_2_ <= d0 && this.isTimeToAttack()) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(p_190102_1_);
                Bugbear.this.setStanding(false);
            } else if (p_190102_2_ <= d0 * 2.0D) {
                if (this.isTimeToAttack()) {
                    Bugbear.this.setStanding(false);
                    this.resetAttackCooldown();
                }

                if (this.getTicksUntilNextAttack() <= 10) {
                    Bugbear.this.setStanding(true);
                    Bugbear.this.playWarningSound();
                }
            } else {
                this.resetAttackCooldown();
                Bugbear.this.setStanding(false);
            }

        }

        public void stop() {
            Bugbear.this.setStanding(false);
            super.stop();
        }

        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return 4.0F + p_179512_1_.getBbWidth();
        }
    }

    class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        public PanicGoal() {
            super(Bugbear.this, 2.0D);
        }

        public boolean canUse() {
            return Bugbear.this.isOnFire() && super.canUse();
        }
    }
}
