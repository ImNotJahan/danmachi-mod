package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.MonsterBase;
import net.minecraft.world.World;

public class EntityKillerAnt extends MonsterBase
{
    public EntityKillerAnt(World worldIn)
    {
        super(worldIn, "killer_ant");

        this.setSize(2.3F, 0.8F);
    }
}
