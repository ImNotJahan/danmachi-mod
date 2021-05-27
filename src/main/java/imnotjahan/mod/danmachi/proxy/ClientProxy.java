package imnotjahan.mod.danmachi.proxy;

import imnotjahan.mod.danmachi.keybinds.KeyInputHandler;
import imnotjahan.mod.danmachi.keybinds.Keybinds;
import imnotjahan.mod.danmachi.util.handlers.RenderHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void preInit()
    {
        Keybinds.RegisterKeybinds();
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        RenderHandler.registerEntityRenderers();
    }
}