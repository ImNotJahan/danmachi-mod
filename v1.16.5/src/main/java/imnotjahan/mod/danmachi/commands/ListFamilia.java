package imnotjahan.mod.danmachi.commands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.danmachi.Reference;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
