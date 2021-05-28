package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityHellhound extends MonsterBase
{
    public EntityHellhound(World worldIn)
    {
        super(worldIn, "hellhound");
    }

    @Override
    protected boolean shouldBurnInDay()
    {
        return false;
    }
}
