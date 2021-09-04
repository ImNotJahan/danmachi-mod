package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.MonsterBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.World;

public class Goblin extends MonsterBase
{
    public Goblin(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_, "goblin");
    }
}
