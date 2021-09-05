package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class DanmachiLog
{
    public DanmachiLog(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("danmachilog").executes((command) ->
        { return CommandStuff(command.getSource()); }));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        return 0;
    }
}
