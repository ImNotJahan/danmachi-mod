package imnotjahan.mod.danmachi.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class StatusStorage implements Capability.IStorage<IStatus>
{
    @Override
    public INBT writeNBT(Capability<IStatus> capability, IStatus instance, Direction side)
    {
        CompoundNBT status = new CompoundNBT();

        status.putIntArray("numberStats", instance.getArray());
        status.putString("familia", instance.getFamilia());

        return status;
    }

    @Override
    public void readNBT(Capability<IStatus> capability, IStatus instance, Direction side, INBT nbt)
    {
        if(nbt instanceof CompoundNBT)
        {
            CompoundNBT tag = (CompoundNBT)nbt;

            instance.setArray(tag.getIntArray("numberStats"));
            instance.setFamilia(tag.getString("familia"));
        }
    }
}