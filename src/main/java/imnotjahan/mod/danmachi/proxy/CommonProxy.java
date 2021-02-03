package imnotjahan.mod.danmachi.proxy;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusStorage;
import imnotjahan.mod.danmachi.util.handlers.CapabilityHandler;
import imnotjahan.mod.danmachi.util.handlers.EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CommonProxy
{
    public void registerItemRenderer(Item item, int meta) {}

    public void init()
    {
        CapabilityManager.INSTANCE.register(IStatus.class, new StatusStorage(), Status::new);

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().player;
    }
}
