package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.IXenos;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Goliath extends MonsterEntity implements IAnimatable
{
    private AnimationFactory factory = new AnimationFactory(this);

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
        data.addAnimationController(new AnimationController(this, "controller", 0,
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
                .add(Attributes.ATTACK_SPEED, 0.1D)
                .add(Attributes.FOLLOW_RANGE, 10);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new SlamAttackGoal());
    }

    class SlamAttackGoal extends MeleeAttackGoal
    {
        public SlamAttackGoal()
        {
            super(Goliath.this, 1, true);
        }
    }
}
