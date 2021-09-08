package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.STDTS;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RabbitBase extends RabbitEntity
{
    private final String name;

    public RabbitBase(EntityType<? extends RabbitEntity> rabbit, World world, String name)
    {
        super(rabbit, world);

        this.name = name;
    }

    @Override
    public boolean canBeLeashed(PlayerEntity p_184652_1_)
    {
        return true;
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        int i = 99;
        if (p_213386_4_ instanceof RabbitEntity.RabbitData) {
            i = ((RabbitEntity.RabbitData)p_213386_4_).rabbitType;
        } else {
            p_213386_4_ = new RabbitEntity.RabbitData(i);
        }

        this.setRabbitType(i);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Override
    public void setCustomName(@Nullable ITextComponent text){}

    @Override
    public void die(DamageSource cause)
    {
        super.die(cause);

        if(cause.getEntity() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getEntity();
            IStatus status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                    .orElse(new Status());

            if (status.getFalna())
            {
                Integer[] statusIncreases = STDTS.SIAStringToDict(
                        Config.COMMON.statusIncreases.get()).get(name);

                for (int k = 0; k < 5; k++)
                {
                    status.increase(statusIncreases[k], k + 1);
                }

                status.increase(statusIncreases[5], 7);
            } else
            {
                return;
            }

        }
    }
}
