package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DragonBase extends EnderDragonEntity implements IMobStatus
{
    private final String name;
    public DragonBase(EntityType<? extends EnderDragonEntity> entity, World world, String name)
    {
        super(entity, world);
        this.name = name;
    }

    @Override
    public void die(DamageSource source)
    {
        super.die(source);
        this.die(source, name);
    }
}
