package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
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
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goliath.slam", true));
        return PlayState.CONTINUE;
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
                .add(Attributes.MOVEMENT_SPEED, 1)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.FOLLOW_RANGE, 10);
    }
}
