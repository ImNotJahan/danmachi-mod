package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.entities.templates.MonsterBase;
import imnotjahan.mod.danmachi.util.STDTS;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Bugbear extends PolarBearEntity
{

    public Bugbear(EntityType<? extends PolarBearEntity> p_i50249_1_, World p_i50249_2_)
    {
        super(p_i50249_1_, p_i50249_2_);
    }

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
                        Config.COMMON.statusIncreases.get()).get("bugbear");

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
}
