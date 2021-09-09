package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

import java.util.Map;
import java.util.Random;

public class MonsterBase extends ZombieEntity
{
    String name = "";

    public MonsterBase(EntityType<? extends ZombieEntity> entityType, World world, String name)
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
    protected boolean isSunSensitive()
    {
        return false;
    }

    @Override
    protected ResourceLocation getDefaultLootTable()
    {
        return super.getDefaultLootTable();
    }

    @Override
    protected void addBehaviourGoals()
    {
        super.addBehaviourGoals();
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
                Integer[] statusIncreases = STD.SIAStringToDict(
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
    protected void handleAttributes(float p_207304_1_)
    {
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * (double)0.05F, AttributeModifier.Operation.ADDITION));
        double d0 = this.random.nextDouble() * 1.5D * (double)p_207304_1_;
        if (d0 > 1.0D)
        {
            this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random zombie-spawn bonus", d0, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        if (this.random.nextFloat() < p_207304_1_ * 0.05F)
        {
            this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0D + 1.0D, AttributeModifier.Operation.MULTIPLY_TOTAL));
            this.setCanBreakDoors(this.supportsBreakDoorGoal());
        }
    }

    /*@Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
    {
        super.dropLoot(wasRecentlyHit, lootingModifier, source);

        if(ModConfig.dropMagicCrystals)
        {
            ResourceLocation resource = LootTableHandler.magicStones.get(name);
            LootTable loottable = this.world.getLootTableManager().getLootTableFromLocation(resource);

            LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer)this.world)).withLootedEntity(this).withDamageSource(source);

            if (wasRecentlyHit && this.attackingPlayer != null)
            {
                lootcontext$builder = lootcontext$builder.withPlayer(this.attackingPlayer).withLuck(this.attackingPlayer.getLuck());
            }

            ItemStack stack = loottable.generateLootForPools(this.rand, lootcontext$builder.build()).get(0);
            entityDropItem(stack, 0);
        }
    }*/
}
