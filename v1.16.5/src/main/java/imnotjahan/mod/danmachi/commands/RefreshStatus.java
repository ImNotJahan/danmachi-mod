package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RefreshStatus
{
    public RefreshStatus(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("refreshstatus").executes((command) ->
        { return CommandStuff(command.getSource()); }));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        ServerPlayerEntity player = source.getPlayerOrException();
        PacketHandler.refreshClient(player);
        return 0;
    }
}
