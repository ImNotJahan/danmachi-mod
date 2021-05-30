package imnotjahan.mod.danmachi.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityDealer extends EntityMob
{
    public EntityDealer(World worldIn)
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
    protected void updateLeashedState()
    {
        super.updateLeashedState();

        if (this.getLeashed() && this.getLeashHolder() != null && this.getLeashHolder().world == this.world)
        {

        }
    }

    long startTime = System.currentTimeMillis();
    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        long timeNow = System.currentTimeMillis();
        if(timeNow - startTime > 1000)
        {
            for (EntityLiving entityliving : player.world.getEntitiesWithinAABB(EntityLiving.class,
                    new AxisAlignedBB(player.posX - 7.0D, player.posY - 7.0D, player.posZ - 7.0D,
                            player.posX + 7.0D, player.posY + 7.0D, player.posZ + 7.0D)))
            {
                if (entityliving.getLeashed() && entityliving.getLeashHolder() == player)
                {
                    switch(entityliving.getName())
                    {
                        case "Unicorn":
                            player.sendMessage(new TextComponentString(
                                    "Finally something good, here's some stuff for your troubles"));
                            player.inventory.addItemStackToInventory(new ItemStack(Items.EMERALD, 3));
                            entityliving.isDead = true;
                            break;

                        default:
                            player.sendMessage(new TextComponentString(
                                    "Come back with something exotic and I'll buy it"));
                            break;
                    }
                } else
                {
                    player.sendMessage(new TextComponentString(
                            "Come back with something exotic and I'll buy it"));
                }
            }
            startTime = timeNow;

            return true;
        }

        return false;
    }

    private void giveDrop(int rarity, EntityPlayer player)
    {

    }
}
