package imnotjahan.mod.danmachi.commands;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.network.MessageStatus;
import imnotjahan.mod.danmachi.network.NetworkHandler;
import imnotjahan.mod.danmachi.util.Reference;
import imnotjahan.mod.danmachi.world.WorldData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class CommandDanmachiDebug extends CommandBase
{


    @Override
    public String getName()
    {
        return "danmachilog";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/danmachilog <type> [additional args]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
    {
        if(args.length > 0)
        {
            switch(args[0].toLowerCase())
            {
                case "floor":
                    if(sender.getEntityWorld().provider.getDimension() == Reference.DUNGEON_ID)
                    {
                        int height = (ModConfig.dungeonHeight - sender.getPosition().getY());
                        int floor = (int)Math.floor(height != 0 ? height / ModConfig.dungeonFloorHeight + 1 : 1);
                        String ordinal;
                        String floorAsString = Integer.toString(floor);

                        switch(floorAsString.charAt(floorAsString.length() - 1))
                        {
                            case 1:
                                ordinal = "st";
                                break;

                            case 2:
                                ordinal = "nd";
                                break;

                            case 3:
                                ordinal = "rd";
                                break;

                            default:
                                ordinal = "th";
                                break;
                        }

                        sender.sendMessage(new TextComponentString("You're on the " + floor + ordinal + " floor"));
                    } else
                    {
                        sender.sendMessage(new TextComponentString("You need to be in the dungeon to use this command"));
                    }
                    break;

                case "hasgeneratedbabel":
                {
                    WorldData data = WorldData.get(sender.getEntityWorld());
                    if (data != null)
                    {
                        sender.sendMessage(new TextComponentString(Boolean.toString(data.babelCreated())));
                    } else
                    {
                        sender.sendMessage(new TextComponentString("Failed to grab world data"));
                    }
                }
                    break;

                case "babelpos":
                {
                    WorldData data = WorldData.get(sender.getEntityWorld());
                    if (data != null)
                    {
                        sender.sendMessage(new TextComponentString(data.getBabelCenter().toString()));
                    } else
                    {
                        sender.sendMessage(new TextComponentString("Failed to grab world data"));
                    }
                }
                    break;

                case "refresh":
                {
                    EntityPlayerMP player = (EntityPlayerMP)sender.getCommandSenderEntity();
                    IStatus status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);
                    NetworkHandler.refreshThing(new MessageStatus(status, player), player);
                }
                    break;

                case "status":
                {
                    IStatus status = sender.getCommandSenderEntity().getCapability(StatusProvider.STATUS_CAP, Status.capSide);
                    switch (args[1])
                    {
                        case "set":
                            switch (args[2])
                            {
                                case "falna":
                                    status.set(0, Integer.parseInt(args[3]));
                                    break;

                                case "strength":
                                    status.set(1, Integer.parseInt(args[3]));
                                    break;

                                case "endurance":
                                    status.set(2, Integer.parseInt(args[3]));
                                    break;

                                case "dexterity":
                                    status.set(3, Integer.parseInt(args[3]));
                                    break;

                                case "agility":
                                    status.set(4, Integer.parseInt(args[3]));
                                    break;

                                case "magic":
                                    status.set(5, Integer.parseInt(args[3]));
                                    break;
                            }
                            break;

                        case "get":
                            switch (args[2])
                            {
                                case "falna":
                                    sender.sendMessage(new TextComponentString(args[2] + ": " + status.get(0)));
                                    break;

                                case "strength":
                                    sender.sendMessage(new TextComponentString(args[2] + ": " + status.get(1)));
                                    break;

                                case "endurance":
                                    sender.sendMessage(new TextComponentString(args[2] + ": " + status.get(2)));
                                    break;

                                case "dexterity":
                                    sender.sendMessage(new TextComponentString(args[2] + ": " + status.get(3)));
                                    break;

                                case "agility":
                                    sender.sendMessage(new TextComponentString(args[2] + ": " + status.get(4)));
                                    break;

                                case "magic":
                                    sender.sendMessage(new TextComponentString(args[2] + ": " + status.get(5)));
                                    break;
                            }
                            break;
                    }
                }
                    break;

                default:
                    sender.sendMessage(new TextComponentString("Incorrect type"));
                    break;
            }
        } else
        {
            sender.sendMessage(new TextComponentString("Missing <type>"));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        List<String> completions = new ArrayList<>();

        switch(args.length)
        {
            case 1:
                completions = new ArrayList<String>()
                {{
                    add("floor");
                    add("hasGeneratedBabel");
                    add("babelPos");
                    add("refresh");
                    add("status");
                }};
                break;

            case 2:
                if(args[1] == "status")
                {
                    completions = new ArrayList<String>()
                    {{
                        add("set");
                        add("get");
                    }};
                }
                break;

            case 3:
                if(args[1] == "status")
                {
                    completions = new ArrayList<String>()
                    {{
                        add("falna");
                        add("strength");
                        add("endurance");
                        add("dexterity");
                        add("agility");
                        add("magic");
                    }};
                }
                break;
        }

        return completions;
    }
}
