package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.gui.Professions;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class EntityGuildMember extends EntityVillager
{
    public EntityGuildMember(World worldIn)
    {
        super(worldIn, VillagerRegistry.getId(Professions.guild));
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
}
