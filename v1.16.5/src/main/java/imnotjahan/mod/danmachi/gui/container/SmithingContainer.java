package imnotjahan.mod.danmachi.gui.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;

public class SmithingContainer extends Container
{
    public SmithingContainer(int id, PlayerInventory playerInventory, PacketBuffer extraData) {

        this(id, playerInventory, new Inventory(6 * 9));

    }

    public SmithingContainer(int id, PlayerInventory playerInventory, IInventory inventory) {

        super(ContainerTypes.SMITHING, id);
        int i = (6 - 4) * 18;

        for (int j = 0; j < 6; ++j) {

            for (int k = 0; k < 9; ++k) {

                this.addSlot(new Slot(inventory, k + j * 9, 8 + k * 18, (j * 18) + 18));

            }

        }

        for (int l = 0; l < 3; ++l) {

            for (int j1 = 0; j1 < 9; ++j1) {

                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));

            }

        }

        for (int i1 = 0; i1 < 9; ++i1) {

            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));

        }
    }

    public SmithingContainer(int id, PlayerInventory playerInventory)
    {
        super(ContainerTypes.SMITHING, id);
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_)
    {
        return true;
    }
}
