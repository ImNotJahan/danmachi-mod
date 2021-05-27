package imnotjahan.mod.danmachi.network;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;

public class MessageStatus extends MessageBase<MessageStatus>
{
    private IStatus status = new Status();
    private EntityPlayer player;

    @Override
    public void handleClientSide(MessageStatus message, EntityPlayer player)
    {
        if(Thread.currentThread().getThreadGroup() == SidedThreadGroups.CLIENT)
        {
            EntityPlayer playerMP = player;
            IStatus statuss = message.status;

            if (playerMP != null)
            {
                IStatus old = playerMP.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

                for (int k = 0; k < 8; k++)
                {
                    old.set(k, statuss.get(k));
                }

                for (int k = 0; k < 6; k++)
                {
                    old.setP(k, statuss.getP(k));
                }

                old.setFamilia(statuss.getFamilia());
                old.setSkills(message.status.getSkills());
                old.setAbilities(message.status.getAbilities());
            }
        }
    }

    @Override
    public void handleServerSide(MessageStatus message, EntityPlayer player)
    {
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        IStatus statuss = message.status;

        IStatus old = playerMP.getCapability(StatusProvider.STATUS_CAP, Status.capSide);
        for(int k = 0; k < 8; k++)
        {
            old.set(k, statuss.get(k));
        }

        for(int k = 0; k < 6; k++)
        {
            old.setP(k, statuss.getP(k));
        }

        old.setFamilia(statuss.getFamilia());
        old.setSkills(message.status.getSkills());
        old.setAbilities(message.status.getAbilities());
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        if(buf.isReadable())
        {
            for(int k = 0; k < 8; k++)
            {
                status.set(k, buf.readInt());
            }

            for(int k = 0; k < 6; k++)
            {
                status.setP(k, buf.readInt());
            }

            status.setFamilia(ByteBufUtils.readUTF8String(buf));

            int skillsLength = buf.readInt();
            byte[] oldSkills = new byte[skillsLength];
            buf.duplicate().readBytes(oldSkills);

            Status.Skill[] skills = new Status.Skill[oldSkills.length];

            for(int k = 0; k < oldSkills.length; k++)
            {
                skills[k] = Status.Skill.values()[(oldSkills[k] & 0xFF)];
            }

            status.setSkills(skills);

            /*int abilitiesLength = buf.readInt();
            byte[] oldAbilities = new byte[abilitiesLength];
            buf.duplicate().readBytes(oldAbilities);

            Status.Ability[] abilities = new Status.Ability[oldAbilities.length];

            for(int k = 0; k < oldAbilities.length; k++)
            {
                abilities[k] = Status.Ability.values()[(oldAbilities[k] & 0xFF)];
                abilities[k].setStat(buf.readByte());
            }

            status.setAbilities(abilities);*/
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        for(int k = 0; k < 8; k++)
        {
            buf.writeInt(status.get(k));
        }

        for(int k = 0; k < 6; k++)
        {
            buf.writeInt(status.getP(k));
        }

        ByteBufUtils.writeUTF8String(buf, status.getFamilia());

        Status.Skill[] skills = status.getSkills();
        byte[] skillBytes = new byte[skills.length];

        buf.writeInt(skills.length);

        for(int k = 0; k < skills.length; k++)
        {
            skillBytes[k] = (byte)skills[k].toInt();
        }

        buf.writeBytes(skillBytes);

        Status.Ability[] abilities = status.getAbilities();
        byte[] abilityBytes = new byte[abilities.length];

        buf.writeInt(abilities.length);

        for(int k = 0; k < abilities.length; k++)
        {
            abilityBytes[k] = (byte)abilities[k].toInt();
        }

        buf.writeBytes(abilityBytes);

        byte[] abilityStats = new byte[abilities.length];

        for(int k = 0; k < abilities.length; k++)
        {
            abilityStats[k] = (byte)abilities[k].getStat();
        }

        buf.writeBytes(abilityStats);
    }

    public MessageStatus(IStatus status, EntityPlayer player)
    {
        this.status = status;
        this.player = player;
    }

    public MessageStatus()
    {

    }
}