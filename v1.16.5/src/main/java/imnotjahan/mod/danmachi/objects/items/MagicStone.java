package imnotjahan.mod.danmachi.objects.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
            tooltip.add(new StringTextComponent("Dropped from " + nbtTagCompound.getString("droppedFrom"))
                    .withStyle(TextFormatting.GRAY));
            tooltip.add(new StringTextComponent("Energy: " + getStoredEnergy(stack) + "/" + getCapacity(stack))
                    .withStyle(TextFormatting.RED));
        }
        super.appendHoverText(stack, world, tooltip, flag);
    }

    public static int getStoredEnergy(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("Energy");
    }

    public static void setStoredEnergy(ItemStack stack, int energy)
    {
        stack.getOrCreateTag().putInt("Energy", energy);
    }

    public static int getCapacity(ItemStack stack)
    {
        return 200;
    }

    public static int getMaxTransfer(ItemStack stack)
    {
        return 5;
    }

    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int p_41407_, boolean p_41408_)
    {
        if(level.isClientSide()) return;
        if(entity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) entity;
            List<ItemStack> energyItems = player.inventory.items
                    .stream()
                    .filter(itemStack -> !ItemStack.tagMatches(itemStack, stack))
                    .filter(itemStack -> itemStack.getCapability(CapabilityEnergy.ENERGY).isPresent())
                    .collect(Collectors.toList());
            if(energyItems.size() > 0)
            {
                int storedEnergy = getStoredEnergy(stack);
                int maxTransferableEnergy = Math.min(storedEnergy, getMaxTransfer(stack));
                int actualTransferredEnergy = 0;

                actualTransferredEnergy = this.transferEnergy(maxTransferableEnergy, energyItems);

                if(actualTransferredEnergy > 0)
                {
                    MagicStone.setStoredEnergy(stack, storedEnergy - actualTransferredEnergy);
                }
            }
        }
    }

    private int transferEnergy(int energy, List<ItemStack> storages)
    {
        AtomicInteger energyTransferred = new AtomicInteger(0);
        int index = 0;

        while(energyTransferred.get() == 0 && index < storages.size())
        {
            ItemStack energyReceiverStack = storages.get(index++);
            energyReceiverStack.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyReceiverStorage ->
                    energyTransferred.set(energyReceiverStorage.receiveEnergy(energy, false)));
        }
        return energyTransferred.get();
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return new StackEnergyStorage(stack);
    }

    public static class StackEnergyStorage implements IEnergyStorage, ICapabilityProvider
    {
        private final LazyOptional<IEnergyStorage> holder = LazyOptional.of(() -> this);
        private final ItemStack stack;

        public StackEnergyStorage(ItemStack stack)
        {
            this.stack = stack;
        }

        public void setEnergyStored(int energy)
        {
            MagicStone.setStoredEnergy(this.stack, energy);
        }

        @Override
        public int getEnergyStored()
        {
            return MagicStone.getStoredEnergy(this.stack);
        }

        @Override
        public int getMaxEnergyStored()
        {
            return MagicStone.getCapacity(this.stack);
        }

        @Override
        public boolean canExtract()
        {
            return this.getEnergyStored() > 0;
        }

        @Override
        public boolean canReceive()
        {
            return this.getEnergyStored() < this.getMaxEnergyStored();
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate)
        {
            if (!canReceive())
            {
                return 0;
            }

            int stored = this.getEnergyStored();
            int energyReceived = Math.min(this.getMaxEnergyStored() - stored, Math.min(MagicStone.getMaxTransfer(this.stack), maxReceive));
            if (!simulate)
            {
                this.setEnergyStored(stored + energyReceived);
            }
            return energyReceived;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate)
        {
            if (!canExtract())
            {
                return 0;
            }

            int stored = this.getEnergyStored();
            int energyExtracted = Math.min(stored, Math.min(MagicStone.getMaxTransfer(this.stack), maxExtract));
            if (!simulate)
            {
                this.setEnergyStored(stored - energyExtracted);
            }
            return energyExtracted;
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
        {
            return CapabilityEnergy.ENERGY.orEmpty(cap, holder);
        }
    }
}
