package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MonsterBase extends ZombieEntity
{
    String name = "";

    public MonsterBase(EntityType<? extends ZombieEntity> entityType, World world, String name)
    {
        super(entityType, world);
        this.name = name;
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
                for (int k = 0; k < 5; k++)
                {
                    status.increase(1, k + 1);
                }

                status.increase(1, 7);
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
