package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.gui.GodGUI;
import imnotjahan.mod.danmachi.network.ClientMessageBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityLoki extends EntityCreature
{
    static final String godName = "loki";

    public EntityLoki(World worldIn)
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
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if(player.isSneaking())
        {
            super.processInteract(player, hand);
        } else
        {
            ItemStack itemstack = player.getHeldItem(hand);
            boolean flag = itemstack.getItem() == Items.GLASS_BOTTLE;

            if (hand == EnumHand.MAIN_HAND)
            {
                ClientMessageBase.showGodGUI(godName, flag);
            }
        }

        return true;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }
}
