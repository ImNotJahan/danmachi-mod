package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.danmachi.Main;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DungeonCommand
{
    public DungeonCommand(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("dungeon").executes((command) ->
        { return CommandStuff(command.getSource()); }));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        ServerPlayerEntity player = source.getPlayerOrException();
        if(player.level.dimension().location().toString().equals("danmachi:dungeon_dimension"))
        {
            source.getPlayerOrException().teleportTo(source.getLevel().getServer().getLevel(World.OVERWORLD),
                    0, 100, 0, 0, 0);
        } else
        {
            RegistryKey<World> dungeonKey = RegistryKey.create(Registry.DIMENSION_REGISTRY,
                    new ResourceLocation(Main.MODID,
                    "dungeon_dimension"));
            ServerWorld dungeon = source.getLevel().getServer().getLevel(dungeonKey);

            source.getPlayerOrException().teleportTo(dungeon, 0, 250, 0, 0,
                    0);
        }
        return 0;
    }
}
