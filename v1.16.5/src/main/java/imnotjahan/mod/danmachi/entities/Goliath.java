package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.IMobStatus;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Goliath extends MonsterEntity implements IAnimatable, IMobStatus
{
    private final AnimationFactory factory = new AnimationFactory(this);

    public Goliath(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))
        {
            setAnimation(event, "animation.goliath.idle");
        } else if (attackAnim != 0)
        {
            setAnimation(event, "animation.goliath.slam");
        } else
        {
            setAnimation(event, "animation.goliath.idle");
        }

        return PlayState.CONTINUE;
    }

    private static void setAnimation(AnimationEvent event, String animation)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(animation, true));
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 5,
                this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return factory;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.MOVEMENT_SPEED, .24F)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.FOLLOW_RANGE, 100);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new SlamAttackGoal());
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void die(DamageSource source)
    {
        this.die(source, "goliath");
        super.die(source);
    }

    class SlamAttackGoal extends MeleeAttackGoal
    {
        private final Goliath goliath;
        private int attackTicks;

        public SlamAttackGoal()
        {
            super(Goliath.this, 1, true);
            goliath = Goliath.this;
        }

        @Override
        public void start()
        {
            super.start();
            this.attackTicks = 0;
        }

        @Override
        public void stop()
        {
            super.stop();
            this.goliath.setAggressive(false);
        }

        @Override
        public void tick()
        {
            super.tick();
            attackTicks++;

            if (this.attackTicks >= 120*5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2)
            {
                this.goliath.setAggressive(true);
            } else
            {
                this.goliath.setAggressive(false);
            }
        }
    }
}
