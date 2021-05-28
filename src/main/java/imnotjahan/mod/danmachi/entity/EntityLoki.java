package imnotjahan.mod.danmachi.entity;

import imnotjahan.mod.danmachi.entity.templates.GodBase;
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

public class EntityLoki extends GodBase
{
    public EntityLoki(World worldIn)
    {
        super(worldIn, "loki");
    }
}
