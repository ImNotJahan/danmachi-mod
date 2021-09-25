package imnotjahan.mod.danmachi.gui.container;

import imnotjahan.mod.danmachi.gui.container.slots.GuildOutputSlot;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.events.EventSubscriber;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.MerchantInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.MerchantResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.config.ModConfig;

import javax.annotation.Nullable;
import java.util.Map;

public class GuildContainer extends Container
{
    private IInventory guildInventory;
    private IInventory guildOutput;

    public GuildContainer(int id, PlayerInventory playerInventory, PacketBuffer extraData)
    {
        this(id, playerInventory);
    }

    public GuildContainer(int id, PlayerInventory playerInventory)
    {
        super(EventSubscriber.guildContainer, id);

        guildInventory = new Inventory(5);
        guildOutput = new Inventory(3);

        for (int k = 0; k < 5; ++k)
        {
            this.addSlot(new Slot(guildInventory, k, 8 + k * 18, 20));
        }

        this.addSlot(new GuildOutputSlot(guildOutput, guildInventory, 0, 152, 20));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 109));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_)
    {
        return true;
    }

    @Override
    public void broadcastChanges()
    {
        super.broadcastChanges();

        int value = 0;

        final Map<String, Integer> guildTrades = STD.SIStringToDict(Config.COMMON.guildTrades.get());
        final Map<String, Integer> stoneTrades = STD.SIStringToDict(Config.COMMON.stoneTrades.get());
        
        for(int k = 0; k < guildInventory.getContainerSize(); k++)
        {
            ItemStack stack = guildInventory.getItem(k);
            for(int j = 0; j < guildTrades.size(); j++)
            {
                if (guildTrades.containsKey(stack.getItem().getRegistryName().toString()))
                {
                    value += guildTrades.get(stack.getItem().getRegistryName().toString())
                            * stack.getCount();
                    break;
                } else if(stack.getItem().getRegistryName().equals("danmachi:magic_stone"))
                {
                    String droppedFrom = stack.getTag().getCompound("display").get("Lore").toString();
                    droppedFrom = droppedFrom.replace("]", "");
                    droppedFrom = droppedFrom.replace("\"", "");
                    droppedFrom = droppedFrom.split(" ")[2];

                    if (stoneTrades.containsKey(droppedFrom))
                    {
                        value += stoneTrades.get(droppedFrom)
                                * stack.getCount();
                        break;
                    }
                }
            }
        }

        guildOutput.setItem(0, new ItemStack(Items.EMERALD, value));
    }
}
