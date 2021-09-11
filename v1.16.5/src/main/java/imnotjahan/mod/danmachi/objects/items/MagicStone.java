package imnotjahan.mod.danmachi.objects.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class MagicStone extends Item
{
    public MagicStone(Properties properties)
    {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        CompoundNBT nbtTagCompound = stack.getTag();
        if(nbtTagCompound != null && nbtTagCompound.contains("droppedFrom"))
        {
            tooltip.add(new StringTextComponent("Dropped from " + nbtTagCompound.getString("droppedFrom")));
            //tooltip.add(new StringTextComponent(String.valueOf(nbtTagCompound.getInt("value"))));
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }
}
