package imnotjahan.mod.danmachi.gui.container.slots;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.util.Constants;

import java.util.Map;
import java.util.stream.IntStream;

public class SmithingSlot extends Slot
{
    IInventory outputInventory;
    IInventory inputInventory;
    public SmithingSlot(IInventory outputInventory, IInventory inputInventory, int index, int xPosition, int yPosition)
    {
        super(outputInventory, index, xPosition, yPosition);
        this.outputInventory = outputInventory;
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return false;
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack)
    {
        for(int k = 0; k < 3; k++)
        {
            inputInventory.setItem(k, ItemStack.EMPTY);
        }
        return stack;
    }

    final Map<String, Integer> requiredSmithingDex = STD.SIStringToDict(Config.COMMON.requiredSmithingDex.get());
    
    @Override
    public boolean mayPickup(PlayerEntity playerIn)
    {
        ItemStack output = outputInventory.getItem(0);

        ListNBT tag = new ListNBT();

        if(output.hasTag() && output.getTag().hasUUID("displayd") && output.getTag().getCompound("displayd").hasUUID("a"))
        {
            tag = output.getTag().getCompound("display")
                    .getList("Lore", Constants.NBT.TAG_STRING);
        }

        if(requiredSmithingDex.containsKey(output.getItem()
                .getRegistryName().toString()))
        {
            int requiredDex = requiredSmithingDex.get(output.getItem()
                    .getRegistryName().toString());

            IStatus status = playerIn.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElse(new Status());
            if (status.get(3) + (status.getLevel() - 1) * 1000 >= requiredDex)
            {
                resetTags(tag);
                return true;
            } else
            {
                resetTags(tag);
                tag.add(StringNBT.valueOf("Not enough dexterity"));
            }
        }

        return false;
    }

    private static void resetTags(ListNBT tags)
    {
        IntStream.range(0, tags.size()).forEach(tags::remove);
    }
}
