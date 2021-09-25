package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class HorseBase extends AbstractHorseEntity
{
    String name = "";

    public HorseBase(EntityType<? extends AbstractHorseEntity> entityType, World world, String name)
    {
        super(entityType, world);
        this.name = name;
    }

    @Override
    public boolean checkSpawnRules(IWorld p_213380_1_, SpawnReason p_213380_2_)
    {
        return super.checkSpawnRules(p_213380_1_, p_213380_2_);
    }

    public static boolean checkMonsterSpawnRules(EntityType<? extends MonsterEntity> p_223325_0_, IServerWorld p_223325_1_, SpawnReason p_223325_2_, BlockPos p_223325_3_, Random p_223325_4_)
    {
        return checkMobSpawnRules(p_223325_0_, p_223325_1_, p_223325_2_, p_223325_3_, p_223325_4_);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, AbstractHorseEntity.class));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.addBehaviourGoals();
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
                        Config.COMMON.statusIncreases.get()).get(name);

                for (int k = 0; k < 5; k++)
                {
                    status.increase(statusIncreases[k], Status.POTENTIAL_START + k);
                }

                status.increase(statusIncreases[5], 6);
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
