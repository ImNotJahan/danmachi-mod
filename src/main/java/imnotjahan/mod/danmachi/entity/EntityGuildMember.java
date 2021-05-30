package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import net.minecraft.block.BlockAnvil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
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
}
