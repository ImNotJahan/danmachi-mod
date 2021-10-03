package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.util.events.EventSubscriber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;

public class ArmorDressContainer extends Container
{
    private IInventory dressInventory;

    public ArmorDressContainer(int id, PlayerInventory playerInventory, PacketBuffer extraData)
    {
        this(id, playerInventory);
    }

    public ArmorDressContainer(int id, PlayerInventory playerInventory)
    {
        super(EventSubscriber.armorDressContainer, id);

        dressInventory = new Inventory(1);

        this.addSlot(new Slot(dressInventory, 0, 15, 27));
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_)
    {
        return true;
    }
}
