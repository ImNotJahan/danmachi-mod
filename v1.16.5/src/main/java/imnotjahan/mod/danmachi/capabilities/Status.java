package imnotjahan.mod.danmachi.capabilities;
import net.minecraft.util.Direction;

import java.util.*;

public class Status implements IStatus
{
    public static final Direction capSide = Direction.UP;

    public enum Ability
    {
        AbnormalResistance(0),
        Blacksmith(1),
        Mage(2),
        Mystery(3),
        Hunter(4),
        SpiritHealing(5),
        Luck(6);

        private int stat;
        private final int id;
        Ability(int id)
        {
            this.stat = 0;
            this.id = id;
        }

        public int getStat()
        {
            return stat;
        }

        public void increaseStat(int amount)
        {
            stat += amount;
        }

        public void setStat(int amount)
        {
            stat = amount;
        }

        public int toInt()
        {
            return id;
        }

        @Override
        public String toString()
        {
            String name = super.toString();

            name = name.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1_$2").toLowerCase();

            return name;
        }

        private static final List<Ability> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final Random RANDOM = new Random();
        private static final int SIZE = VALUES.size();

        public static Ability randomAbility()
        {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public enum Skill
    {
        AeroMana(0, 10),
        AilMacMidna(1, 10),
        Avenger(2, 5),
        MindLoad(3, 6);

        private final int index;
        private final int rarity;
        Skill(int index, int rarity)
        {
            this.index = index;
            this.rarity = rarity;
        }

        public int toInt()
        {
            return index;
        }

        private static final List<Skill> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Skill randomSkill()
        {
            Skill skill = Skill.AeroMana;
            Boolean gotOne = false;
            while(!gotOne)
            {
                skill = VALUES.get(RANDOM.nextInt(SIZE));
                if(Math.random() * 10 < skill.rarity)
                {
                    gotOne = true;
                }
            }

            return skill;
        }

        @Override
        public String toString()
        {
            String name = super.toString();

            name = name.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1_$2").toLowerCase();

            return name;
        }
    }

    public enum Magic
    {
        Firebolt(0);

        private final int index;
        Magic(int index)
        {
            this.index = index;
        }

        public int toInt()
        {
            return index;
        }

        private static final List<Magic> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Magic randomSkill()
        {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

        @Override
        public String toString()
        {
            String name = super.toString();

            name = name.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1_$2").toLowerCase();

            return name;
        }
    }

    private String familia = "";
    private List<Skill> skills = new ArrayList<>();
    private List<Ability> abilities = new ArrayList<>();
    private List<Magic> spells = new ArrayList<>();

    public boolean canLevelUp = false;

    /** 0 falna, 1 strength, 2 endurance, 3 dexterity, 4 agility, 5 magic, 6 excelia, 7 level, 8 mind
     * 9 p strength, 10 p endurance, 11 p dexterity, 12 p agility, 13 p magic, 14 p excelia.
     *  <br><br>
     * P stands for potential, as it isn't applied to the players actual status yet*/
    private int[] stats = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0 };

    /** The index at which potential stats start */
    public static final int POTENTIAL_START = 9;
    private static final int NORMAL_STAT_AMOUNT = 6;

    @Override
    public void increase(int points, int id)
    {
        if(id != 7)
        {
            for(int k = 0; k < stats[7] - 1; k++)
            {
                points /= 2;
            }
        }

        if(id < stats.length) stats[id] += points;
    }

    @Override
    public void set(int id, int stat)
    {
        if(id < stats.length) stats[id] = stat;
    }

    @Override
    public int get(int id)
    {
        if(id < stats.length) return stats[id];
        return -1;
    }

    @Override
    public void giveFalna()
    {
        stats[0] = 1;
        stats[7]++;
    }

    @Override
    public boolean getFalna()
    {
        return (stats[0] == 1);
    }

    @Override
    public String getFamilia()
    {
        return familia;
    }

    @Override
    public void setFamilia(String familia)
    {
        this.familia = familia;
    }

    @Override
    public int getLevel()
    {
        int level = stats[7];
        if(stats[6] / (300 * level) >= 1 &&
                stats[1] > 400 ||
                stats[2] > 400 ||
                stats[3] > 400 ||
                stats[4] > 400 ||
                stats[5] > 400)
        {
            canLevelUp = true;
            level++;
        }

        return level;
    }

    @Override
    public Set<Ability> levelUp()
    {
        stats[7] += 1;

        for(int k = 0; k < NORMAL_STAT_AMOUNT; k++) stats[k + 1] = 0;

        canLevelUp = false;

        Set<Ability> abilities = new HashSet<>();
        for(int k = 0; k < 3; k++)
        {
            abilities.add(Ability.randomAbility());
        }

        return abilities;
    }

    @Override
    public int updateStatus()
    {
        for(int k = 0; k < NORMAL_STAT_AMOUNT; k++) stats[k + 1] += stats[POTENTIAL_START + k];
        stats[7] = getLevel();

        int increase = stats[POTENTIAL_START] + 5; // Excelia

        for(int k = 0; k < NORMAL_STAT_AMOUNT; k++) stats[POTENTIAL_START + k] = 0;

        return increase;
    }

    @Override
    public void grantAbility(Ability ability)
    {
        abilities.add(ability);
    }

    @Override
    public boolean grantSkill()
    {
        Skill chosenSkill = Skill.randomSkill();
        if(!skills.contains(chosenSkill))
        {
            skills.add(chosenSkill);
            return true;
        }

        return false;
    }

    @Override
    public boolean grantMagic()
    {
        Magic chosenMagic = Magic.randomSkill();
        if(!spells.contains(chosenMagic))
        {
            spells.add(chosenMagic);
            return true;
        }

        return false;
    }

    @Override
    public void setAbilities(Ability[] abilities)
    {
        for(int k = 0; k < abilities.length; k++)
        {
            this.abilities.add(k, abilities[k]);
        }
    }

    @Override
    public void setSkills(Skill[] skills)
    {
        for(int k = 0; k < skills.length; k++)
        {
            this.skills.add(k, skills[k]);
        }
    }

    @Override
    public void setSpells(Magic[] spells)
    {
        for(int k = 0; k < spells.length; k++)
        {
            this.spells.add(k, spells[k]);
        }
    }

    @Override
    public Ability[] getAbilities()
    {
        Ability[] abilityArray = new Ability[abilities.toArray().length];

        for(int k = 0; k < abilityArray.length; k++)
        {
            abilityArray[k] = abilities.get(k);
        }

        return abilityArray;
    }

    @Override
    public Skill[] getSkills()
    {
        Skill[] skillArray = new Skill[skills.toArray().length];

        for(int k = 0; k < skillArray.length; k++)
        {
            skillArray[k] = skills.get(k);
        }

        return skillArray;
    }

    @Override
    public Magic[] getSpells()
    {
        Magic[] spellArray = new Magic[spells.toArray().length];

        for(int k = 0; k < spellArray.length; k++)
        {
            spellArray[k] = spells.get(k);
        }

        return spellArray;
    }

    @Override
    public int[] getArray()
    {
        return stats;
    }

    @Override
    public void setArray(int[] array)
    {
        stats = array;
    }

    @Override
    public boolean canLevelUp()
    {
        return canLevelUp;
    }

    @Override
    public void setCanLevelUp(boolean canLevelUp)
    {
        this.canLevelUp = canLevelUp;
    }
}