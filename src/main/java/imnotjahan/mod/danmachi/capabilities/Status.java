package imnotjahan.mod.danmachi.capabilities;

import imnotjahan.mod.danmachi.config.ModConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.*;

public class Status implements IStatus
{
    public static final EnumFacing capSide = EnumFacing.UP;

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

            return I18n.format("skills." + name);
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

            return I18n.format("skills." + name);
        }
    }

    private int hasFalna = 0;
    private int strength = 0;
    private int endurance = 0;
    private int dexterity = 0;
    private int agility = 0;
    private int magic = 0;
    private int level = 1;
    private int excelia = 0;
    private int mind = 100;

    private String familia = "";
    private List<Skill> skills = new ArrayList<>();
    private List<Ability> abilities = new ArrayList<>();

    private int strengthP = 0;
    private int enduranceP = 0;
    private int dexterityP = 0;
    private int agilityP = 0;
    private int magicP = 0;
    private int exceliaP = 0;

    public boolean canLevelUp = false;

    @Override
    public void increase(int points, int id)
    {
        if(id != 7)
        {
            for(int k = 0; k < level - 1; k++)
            {
                points /= 2;
            }
        }

        points *= ModConfig.statMultiplier;

        switch(id)
        {
            case 0:
                this.hasFalna += points;
                break;

            case 1:
                this.strengthP += points;
                break;

            case 2:
                this.enduranceP += points;
                break;

            case 3:
                this.dexterityP += points;
                break;

            case 4:
                this.agilityP += points;
                break;

            case 5:
                this.magicP += points;
                break;

            case 6:
                this.level += points;
                break;

            case 7:
                this.exceliaP += points;
                break;
        }
    }

    @Override
    public void set(int id, int stat)
    {
        switch(id)
        {
            case 0:
                this.hasFalna = stat;
                break;

            case 1:
                this.strength = stat;
                break;

            case 2:
                this.endurance = stat;
                break;

            case 3:
                this.dexterity = stat;
                break;

            case 4:
                this.agility = stat;
                break;

            case 5:
                this.magic = stat;
                break;

            case 6:
                this.level = stat;
                break;

            case 7:
                this.excelia = stat;
                break;
        }
    }

    @Override
    public void setP(int id, int stat)
    {
        switch(id)
        {
            case 0:
                this.strengthP = stat;
                break;

            case 1:
                this.enduranceP = stat;
                break;

            case 2:
                this.dexterityP = stat;
                break;

            case 3:
                this.agilityP = stat;
                break;

            case 4:
                this.magicP = stat;
                break;

            case 5:
                this.exceliaP = stat;
                break;
        }
    }

    @Override
    public int get(int id)
    {
        switch(id)
        {
            case 0:
                return this.hasFalna;

            case 1:
                return this.strength;

            case 2:
                return this.endurance;

            case 3:
                return this.dexterity;

            case 4:
                return this.agility;

            case 5:
                return this.magic;

            case 6:
                return this.level;

            case 7:
                return this.excelia;
        }

        return 0;
    }

    @Override
    public int getP(int id)
    {
        switch (id)
        {
            case 0:
                return this.strengthP;

            case 1:
                return this.enduranceP;

            case 2:
                return this.dexterityP;

            case 3:
                return this.agilityP;

            case 4:
                return this.magicP;

            case 5:
                return this.exceliaP;
        }

        return 0;
    }

    @Override
    public void giveFalna()
    {
        hasFalna = 1;
    }

    @Override
    public boolean getFalna()
    {
        return (hasFalna == 1);
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
        int level = this.level;
        if(excelia / (300 * level) >= 1 && strength > 400 ||
                endurance > 400 ||
                dexterity > 400 ||
                agility > 400 ||
                magicP > 400)
        {
            canLevelUp = true;
            level++;
        }

        return level;
    }

    @Override
    public Set<Ability> levelUp()
    {
        level += 1;
        excelia = 0;

        strength = 0;
        endurance = 0;
        dexterity = 0;
        agility = 0;
        magic = 0;

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
        strength += strengthP;
        endurance += enduranceP;
        dexterity += dexterityP;
        agility += agilityP;
        magic += magicP;
        excelia += exceliaP;
        level = getLevel();

        int increase = exceliaP;

        strengthP = 0;
        enduranceP = 0;
        dexterityP = 0;
        agilityP = 0;
        magicP = 0;
        exceliaP = 0;

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