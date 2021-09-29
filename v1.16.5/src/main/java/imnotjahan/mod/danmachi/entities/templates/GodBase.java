package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.ClientThings;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.UUID;

public class GodBase extends CreatureEntity
{
    private final String godName;

    /**
    @param entity The entity type of this mob
    @param world The world that it's being spawned in
    @param godName The name of the god in snake case
     */
    protected GodBase(EntityType<? extends CreatureEntity> entity, World world, String godName)
    {
        super(entity, world);
        this.godName = godName;
    }

    @Override
    public void die(DamageSource source)
    {
        super.die(source);

        if(!(source.getEntity() instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) source.getEntity();

        String familia = player.getCapability(StatusProvider.STATUS_CAP).orElseThrow(MissingStatus::new).getFamilia();
        String killer = familia.isEmpty() ? player.getScoreboardName() : familia;

        getServer().sendMessage(new StringTextComponent(source.getEntity().getDisplayName().getString() +
                " was killed by " + killer), UUID.randomUUID());
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean wantingBlood = itemstack.getItem() == Items.GLASS_BOTTLE;

        if(hand == Hand.MAIN_HAND && player.getCommandSenderWorld().isClientSide)
        {
            ClientThings.showGodGui(godName, wantingBlood);
        }

        return ActionResultType.PASS;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_KNOCKBACK).add(Attributes.MAX_HEALTH, 30);
    }
}
