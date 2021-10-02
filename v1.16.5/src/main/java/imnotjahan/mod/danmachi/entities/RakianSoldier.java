package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.MonsterBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RakianSoldier extends MonsterBase
{
    private static final int SOLDIER_TYPES = 2;
    private static final DataParameter<Integer> ID = EntityDataManager.defineId(RakianSoldier.class, DataSerializers.INT);

    public RakianSoldier(EntityType<? extends ZombieEntity> entityType, World world)
    {
        super(entityType, world, "rakian_soldier");
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(ID, 0);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_)
    {
        this.entityData.set(ID, Math.round((float) Math.random() * (SOLDIER_TYPES - 1)));
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt)
    {
        this.entityData.set(ID, nbt.getInt("SoldierType"));
        super.readAdditionalSaveData(nbt);
    }

    public int getSoldier()
    {
        return this.entityData.get(ID);
    }
}
