package imnotjahan.mod.danmachi.commands;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.util.Reference;
import imnotjahan.mod.danmachi.world.WorldData;
import imnotjahan.mod.danmachi.world.dimension.CustomTeleporter;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandDungeon extends CommandBase
{
    @Override
    public String getName()
    {
        return "dungeon";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/dungeon";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        int dimensionId = sender.getEntityWorld().provider.getDimension();

        if(sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)sender;

            if(dimensionId == Reference.DUNGEON_ID)
            {
                WorldData data = WorldData.get(server.getEntityWorld());
                CustomTeleporter.teleportToDimension(player, 0, data.getBabelCenter().getX(), data.getBabelCenter().getY(), data.getBabelCenter().getZ());
            } else
            {
                CustomTeleporter.teleportToDimension(player, Reference.DUNGEON_ID, 0, Math.min(ModConfig.dungeonHeight, 256) - 3, 0);
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
}
