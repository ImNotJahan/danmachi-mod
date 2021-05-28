package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.network.ClientMessageBase;
import imnotjahan.mod.danmachi.network.MessageStatus;
import imnotjahan.mod.danmachi.network.NetworkHandler;
import imnotjahan.mod.danmachi.util.handlers.LootTableHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class EntityHellhound extends EntityZombie
{
    public EntityHellhound(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected boolean shouldBurnInDay()
    {
        return false;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.HELLHOUND;
    }

    @Override
    public boolean isArmsRaised()
    {
        return false;
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if(cause.getTrueSource() instanceof EntityPlayer)
        {
            EntityPlayerMP player = (EntityPlayerMP)cause.getTrueSource();
            IStatus status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);
            if(!status.getFalna()) return;

            for(int k = 0; k < 5; k++)
            {
                status.increase(ModConfig.statusIncreases.get("hellhound")[k], k + 1);
            }

            status.increase(ModConfig.statusIncreases.get("hellhound")[5], 7);

            NetworkHandler.refreshThing(new MessageStatus(status, player), player);
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return super.getCanSpawnHere() && world.provider.getDimension() == 0 ? Math.random() < ModConfig.overworldMonsterSpawnChance : true;
    }

    @Override
    public boolean isChild()
    {
        return false;
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return true;
    }

    @Override
    protected float getSoundPitch()
    {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F;
    }

    @Override
    public boolean isRiding()
    {
        return false;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
    {
        super.dropLoot(wasRecentlyHit, lootingModifier, source);

        if(ModConfig.dropMagicCrystals)
        {
            ResourceLocation resource = LootTableHandler.HELLHOUND_STONE;
            LootTable loottable = this.world.getLootTableManager().getLootTableFromLocation(resource);

            LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer)this.world)).withLootedEntity(this).withDamageSource(source);

            if (wasRecentlyHit && this.attackingPlayer != null)
            {
                lootcontext$builder = lootcontext$builder.withPlayer(this.attackingPlayer).withLuck(this.attackingPlayer.getLuck());
            }

            ItemStack stack = loottable.generateLootForPools(this.rand, lootcontext$builder.build()).get(0);
            entityDropItem(stack, 0);
        }
    }
}
