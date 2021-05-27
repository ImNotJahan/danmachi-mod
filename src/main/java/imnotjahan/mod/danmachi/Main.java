package imnotjahan.mod.danmachi;

import imnotjahan.mod.danmachi.gui.Professions;
import imnotjahan.mod.danmachi.proxy.CommonProxy;
import imnotjahan.mod.danmachi.tabs.CustomTab;
import imnotjahan.mod.danmachi.util.Reference;
import imnotjahan.mod.danmachi.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/*
1. Work on attacking and defensive abilities and find a way
2. Special system for ore such as special forge and anvil
3. Summoner class
4. More monsters tons of new monsters
5. Better dungeon gen
*/

/*
* Weapons to add:
* Thunder sword
* Fire sword
* Frosvirt
* Crimson Whip
* Kaminari Ikazuchimaru
* Supreme Black Sword
* Udaeus' Black Sword
* Victim Abyss
* Dizaria
* Violetta
 * */
@Mod(modid = Reference.MODID,
        name = Reference.NAME,
        version = Reference.VERSION,
        useMetadata = true,
        guiFactory = Reference.GUI_FACTORY)
public class Main
{
    public static final CreativeTabs creativeTab = new CustomTab("danmachitab");

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries();
        RegistryHandler.otherRegistries();
        proxy.preInit();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        Professions.associateCareersAndTrades();
        proxy.init();
        RegistryHandler.initRegistries();
    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
        RegistryHandler.serverInitRegistries(event);
    }
}
