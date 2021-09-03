package imnotjahan.mod.danmachi.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class StatusProvider implements ICapabilitySerializable<INBT>
{
    @CapabilityInject(IStatus.class)
    public static final Capability<IStatus> STATUS_CAP = null;

    private IStatus instance = STATUS_CAP.getDefaultInstance();

    public boolean hasCapability(Capability<?> capability, Direction facing)
    {
        return capability == STATUS_CAP && facing == Status.capSide;
    }

    @Override
    public <IStatus> LazyOptional<IStatus> getCapability(Capability<IStatus> capability, Direction facing)
    {
        return hasCapability(capability, facing) ? (LazyOptional<IStatus>)this.instance : null;
    }

    @Override
    public INBT serializeNBT()
    {
        return STATUS_CAP.getStorage().writeNBT(STATUS_CAP, this.instance, Status.capSide);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        STATUS_CAP.getStorage().readNBT(STATUS_CAP, this.instance, Status.capSide, nbt);
    }
}