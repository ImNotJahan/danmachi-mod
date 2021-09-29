package imnotjahan.mod.danmachi.entities.templates;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.init.Items;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.network.PacketDistributor;

public interface IMobStatus
{
    default void die(DamageSource cause, String name)
    {
        if(cause.getEntity() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getEntity();
            IStatus status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                    .orElseThrow(MissingStatus::new);

            ItemStack stone = new ItemStack(Items.MAGIC_STONE);
            CompoundNBT nbt = stone.getOrCreateTag();

            nbt.putString("droppedFrom", name);
            nbt.putInt("value", 100);

            player.addItem(stone);

            if (status.getFalna())
            {
                Integer[] statusIncreases = STD.SIAStringToDict(
                        Config.COMMON.statusIncreases.get()).get(name);

                for (int k = 0; k < 5; k++)
                {
                    status.increase(statusIncreases[k], Status.POTENTIAL_START + k);
                }

                status.increase(statusIncreases[5], 6);

                PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageStatus(
                        player.getCapability(StatusProvider.STATUS_CAP, Status.capSide)
                                .orElseThrow(ArithmeticException::new)));
            }
        }
    }
}
