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
        if(Status.capSide == side)
        {
            CompoundNBT status = new CompoundNBT();
            status.putInt("hasFalna", instance.get(0));
            status.putInt("strength", instance.get(1));
            status.putInt("endurance", instance.get(2));
            status.putInt("dexterity", instance.get(3));
            status.putInt("agility", instance.get(4));
            status.putInt("magic", instance.get(5));
            status.putInt("level", instance.get(6));
            status.putInt("excelia", instance.get(7));

            status.putInt("strengthP", instance.getP(0));
            status.putInt("enduranceP", instance.getP(1));
            status.putInt("dexterityP", instance.getP(2));
            status.putInt("agilityP", instance.getP(3));
            status.putInt("magicP", instance.getP(4));
            status.putInt("exceliaP", instance.getP(5));

            status.putString("familia", instance.getFamilia());

            Status.Skill[] instanceSkills = instance.getSkills();
            int[] skills = new int[instanceSkills.length];

            for(int k = 0; k < skills.length; k++)
            {
                skills[k] = instanceSkills[k].toInt();
            }

            status.putIntArray("skills", skills);

            Status.Ability[] instanceAbilities = instance.getAbilities();
            int[] abilities = new int[instanceAbilities.length];
            int[] abilityStats = new int[instanceAbilities.length];

            for(int k = 0; k < abilities.length; k++)
            {
                abilities[k] = instanceAbilities[k].toInt();
                abilityStats[k] = instanceAbilities[k].getStat();
            }

            status.putIntArray("abilities", abilities);
            status.putIntArray("abilityStats", abilityStats);

            return status;
        }

        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<IStatus> capability, IStatus instance, Direction side, INBT nbt)
    {
        if(Status.capSide == side)
        {
            if(nbt instanceof CompoundNBT)
            {
                CompoundNBT tag = (CompoundNBT)nbt;

                instance.set(0, tag.getInt("hasFalna"));
                instance.set(1, tag.getInt("strength"));
                instance.set(2, tag.getInt("endurance"));
                instance.set(3, tag.getInt("dexterity"));
                instance.set(4, tag.getInt("agility"));
                instance.set(5, tag.getInt("magic"));
                instance.set(6, tag.getInt("level"));
                instance.set(7, tag.getInt("excelia"));

                instance.setP(0, tag.getInt("strengthP"));
                instance.setP(1, tag.getInt("enduranceP"));
                instance.setP(2, tag.getInt("dexterityP"));
                instance.setP(3, tag.getInt("agilityP"));
                instance.setP(4, tag.getInt("magicP"));
                instance.setP(5, tag.getInt("exceliaP"));

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