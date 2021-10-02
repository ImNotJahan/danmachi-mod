package imnotjahan.mod.danmachi.capabilities;

import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class StatusProvider implements ICapabilitySerializable<INBT>
{
    @CapabilityInject(IStatus.class)
    public static final Capability<IStatus> STATUS_CAP = null;
    private final LazyOptional<IStatus> lazyStatus = LazyOptional.of(Status::new);

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return capability == STATUS_CAP ? lazyStatus.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT()
    {
        return STATUS_CAP.getStorage().writeNBT(STATUS_CAP, this.lazyStatus.orElseThrow(MissingStatus::new), Status.capSide);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        STATUS_CAP.getStorage().readNBT(STATUS_CAP, this.lazyStatus.orElseThrow(MissingStatus::new), Status.capSide, nbt);
    }
}