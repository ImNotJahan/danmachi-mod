package imnotjahan.mod.danmachi.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import org.jline.utils.Log;

public class StatusStorage implements Capability.IStorage<IStatus>
{
    @Override
    public NBTBase writeNBT(Capability<IStatus> capability, IStatus instance, EnumFacing side)
    {
        if(Status.capSide == side)
        {
            NBTTagCompound status = new NBTTagCompound();
            status.setInteger("hasFalna", instance.get(0));
            status.setInteger("strength", instance.get(1));
            status.setInteger("endurance", instance.get(2));
            status.setInteger("dexterity", instance.get(3));
            status.setInteger("agility", instance.get(4));
            status.setInteger("magic", instance.get(5));
            status.setInteger("level", instance.get(6));
            status.setInteger("excelia", instance.get(7));

            status.setInteger("strengthP", instance.getP(0));
            status.setInteger("enduranceP", instance.getP(1));
            status.setInteger("dexterityP", instance.getP(2));
            status.setInteger("agilityP", instance.getP(3));
            status.setInteger("magicP", instance.getP(4));
            status.setInteger("exceliaP", instance.getP(5));

            status.setString("familia", instance.getFamilia());

            Status.Skill[] instanceSkills = instance.getSkills();
            int[] skills = new int[instanceSkills.length];

            for(int k = 0; k < skills.length; k++)
            {
                skills[k] = instanceSkills[k].toInt();
            }

            status.setIntArray("skills", skills);

            Status.Ability[] instanceAbilities = instance.getAbilities();
            int[] abilities = new int[instanceAbilities.length];
            int[] abilityStats = new int[instanceAbilities.length];

            for(int k = 0; k < abilities.length; k++)
            {
                abilities[k] = instanceAbilities[k].toInt();
                abilityStats[k] = instanceAbilities[k].getStat();
            }

            status.setIntArray("abilities", abilities);
            status.setIntArray("abilityStats", abilityStats);

            return status;
        }

        return new NBTTagCompound();
    }

    @Override
    public void readNBT(Capability<IStatus> capability, IStatus instance, EnumFacing side, NBTBase nbt)
    {
        if(Status.capSide == side)
        {
            if(nbt instanceof NBTTagCompound)
            {
                NBTTagCompound tag = (NBTTagCompound)nbt;

                instance.set(0, tag.getInteger("hasFalna"));
                instance.set(1, tag.getInteger("strength"));
                instance.set(2, tag.getInteger("endurance"));
                instance.set(3, tag.getInteger("dexterity"));
                instance.set(4, tag.getInteger("agility"));
                instance.set(5, tag.getInteger("magic"));
                instance.set(6, tag.getInteger("level"));
                instance.set(7, tag.getInteger("excelia"));

                instance.setP(0, tag.getInteger("strengthP"));
                instance.setP(1, tag.getInteger("enduranceP"));
                instance.setP(2, tag.getInteger("dexterityP"));
                instance.setP(3, tag.getInteger("agilityP"));
                instance.setP(4, tag.getInteger("magicP"));
                instance.setP(5, tag.getInteger("exceliaP"));

                instance.setFamilia(tag.getString("familia"));

                int[] oldSkills = tag.getIntArray("skills");
                Status.Skill[] skills = new Status.Skill[oldSkills.length];

                for(int k = 0; k < oldSkills.length; k++)
                {
                    skills[k] = Status.Skill.values()[oldSkills[k]];
                }

                instance.setSkills(skills);

                int[] oldAbilities = tag.getIntArray("abilities");
                int[] oldAbilityStats = tag.getIntArray("abilityStats");
                Status.Ability[] abilities = new Status.Ability[oldAbilities.length];

                for(int k = 0; k < oldAbilities.length; k++)
                {
                    abilities[k] = Status.Ability.values()[oldAbilities[k]];
                    abilities[k].setStat(oldAbilityStats[k]);
                }

                instance.setAbilities(abilities);
            }
        }
    }
}