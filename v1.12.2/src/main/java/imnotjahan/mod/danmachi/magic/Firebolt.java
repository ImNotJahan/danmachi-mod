package imnotjahan.mod.danmachi.magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.math.Vec3d;

public class Firebolt
{
    public static void fire(EntityPlayer player)
    {
        Vec3d vec3d = player.getLook(1.0F);

        EntityLargeFireball fireball = new EntityLargeFireball(player.world, player.posX, player.posY, player.posZ,
                vec3d.x, vec3d.y, vec3d.z);
        fireball.explosionPower = 10;
        player.world.spawnEntity(fireball);
        //WorldServer
    }
}
