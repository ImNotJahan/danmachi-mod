package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.Main;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityGuildMember extends EntityCreature
{
    public EntityGuildMember(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
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
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if(!player.world.isRemote)
        {
            player.openGui(Main.instance, 0, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
        return true;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIMoveIndoors(this));
        this.tasks.addTask(2, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.6D));
    }
}
