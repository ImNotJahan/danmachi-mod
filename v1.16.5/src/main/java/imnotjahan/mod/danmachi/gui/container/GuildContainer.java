package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.util.events.EventSubscriber;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.MerchantInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.MerchantResultSlot;
import net.minecraft.inventory.container.Slot;

import javax.annotation.Nullable;

public class GuildContainer extends Container
{
    public GuildContainer(@Nullable ContainerType<?> type, int id)
    {
        super(type, id);
    }

    public GuildContainer(int id, PlayerInventory playerInventory)
    {
        super(EventSubscriber.smithingContainer, id);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 108 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 108 + k * 18, 142));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_)
    {
        return false;
    }
}
