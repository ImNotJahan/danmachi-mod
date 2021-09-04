package imnotjahan.mod.danmachi.networking.packets;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageStatus
{
    private IStatus status = new Status();
    private PlayerEntity player;

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        
    }

    public void handleServerSide(MessageStatus message, PlayerEntity player)
    {
        ServerPlayerEntity playerMP = (ServerPlayerEntity)player;
        IStatus statuss = message.status;
        IStatus old = playerMP.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElse(new Status());

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
        old.setSpells(message.status.getSpells());
    }

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

            int skillsLength = buf.readInt();
            byte[] oldSkills = new byte[skillsLength];
            buf.duplicate().readBytes(oldSkills);

            Status.Skill[] skills = new Status.Skill[oldSkills.length];

            for(int k = 0; k < oldSkills.length; k++)
            {
                skills[k] = Status.Skill.values()[(oldSkills[k] & 0xFF)];
            }

            status.setSkills(skills);

            int spellsLength = buf.readInt();
            byte[] oldSpells = new byte[spellsLength];
            buf.duplicate().readBytes(oldSkills);

            Status.Magic[] spells = new Status.Magic[oldSpells.length];

            for(int k = 0; k < oldSpells.length; k++)
            {
                spells[k] = Status.Magic.values()[(oldSpells[k] & 0xFF)];
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

    public void encode(PacketBuffer buf)
    {
        status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElse(new Status());

        for(int k = 0; k < 8; k++)
        {
            buf.writeInt(status.get(k));
        }

        for(int k = 0; k < 6; k++)
        {
            buf.writeInt(status.getP(k));
        }

        Status.Skill[] skills = status.getSkills();
        byte[] skillBytes = new byte[skills.length];

        buf.writeInt(skills.length);

        for(int k = 0; k < skills.length; k++)
        {
            skillBytes[k] = (byte)skills[k].toInt();
        }

        buf.writeBytes(skillBytes);

        Status.Magic[] spells = status.getSpells();
        byte[] spellBytes = new byte[spells.length];

        buf.writeInt(spells.length);

        for(int k = 0; k < spells.length; k++)
        {
            spellBytes[k] = (byte)spells[k].toInt();
        }

        buf.writeBytes(spellBytes);

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

    public MessageStatus(IStatus status, PlayerEntity player)
    {
        this.status = status;
        this.player = player;
    }

    public MessageStatus()
    {

    }
}
