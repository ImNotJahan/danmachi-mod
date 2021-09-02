package imnotjahan.mod.danmachi;

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
Danmachi Mod - Literally just a mod about danmachi
Created by Jahan Rashidi


This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see http://www.gnu.org/licenses.
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
        proxy.init();
        RegistryHandler.initRegistries();
    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
        RegistryHandler.serverInitRegistries(event);
    }
}
