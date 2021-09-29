package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;

public class ListFamilia
{
    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        source.getLevel().players().forEach(player ->
        {

        });
        return 0;
    }
}
