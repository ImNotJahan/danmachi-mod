package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.StringTextComponent;

import java.util.Arrays;

public class ChangeStatus
{
    private static final String[] actionSuggestions = new String[]{"get", "set", "increase", "decrease"};
    private static final String[] statSuggestions = new String[]{"falna", "strength", "endurance", "dexterity", "agility",
            "magic", "excelia", "level", "mind", "pstrength", "pendurance", "pdexterity", "pagility", "pmagic", "pexcelia"};

    public static final SuggestionProvider<CommandSource> ActionSuggestions = (a, b) ->
            ISuggestionProvider.suggest(actionSuggestions, b);
    public static final SuggestionProvider<CommandSource> StatSuggestions = (a, b) ->
            ISuggestionProvider.suggest(statSuggestions, b);

    public ChangeStatus(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("status")
                .then(Commands.argument("action", StringArgumentType.word()).suggests(ActionSuggestions)
                .then(Commands.argument("stat", StringArgumentType.word()).suggests(StatSuggestions)
                .then(Commands.argument("value", IntegerArgumentType.integer(0))
                .executes((command) -> CommandStuff(command.getSource(), command.getArgument("action", String.class),
                       command.getArgument("stat", String.class), command.getArgument("value", Integer.class)))))));
    }

    private int CommandStuff(CommandSource source, String action, String stat, int value) throws CommandSyntaxException
    {
        IStatus status = source.getPlayerOrException().getCapability(StatusProvider.STATUS_CAP)
                .orElseThrow(MissingStatus::new);
        final int statID = Arrays.asList(statSuggestions).indexOf(stat);

        switch(action)
        {
            case "get":
                source.sendSuccess(new StringTextComponent("Your " + stat + " is " +
                        status.get(statID)), false);
                return 0;

            case "set":
                status.set(statID, value);
                source.sendSuccess(new StringTextComponent("Set " + stat + " to " + value), false);
                return 0;

            case "increase":
                status.increase(statID, value);
                break;

            case "decrease":
                status.increase(statID, -value);
                break;

            default:
                source.sendFailure(new StringTextComponent("Incorrect action"));
                return 0;
        }

        source.sendSuccess(new StringTextComponent(action + "d " + stat + " by " + value), false);

        return 0;
    }
}
