package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class DungeonCommand
{
    public DungeonCommand(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("dungeon").executes((command) ->
        { return CommandStuff(command.getSource()); }));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {

        return 0;
    }
}
