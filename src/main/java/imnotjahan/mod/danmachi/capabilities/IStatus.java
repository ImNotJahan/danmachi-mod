package imnotjahan.mod.danmachi.capabilities;

import java.util.Set;

public interface IStatus
{
    void giveFalna();
    boolean getFalna();

    String getFamilia();

    void setFamilia(String familiaName);

    /**
     * @param points how much the stat should increase by,
     *               is cut in half n - 1 times, n being level.
     *               doesn't cut in half if your adding excelia
     * @param id the id of the stat your increasing
     */
    void increase(int points, int id);

    /**
     * @param id the id of the stat your changing
     * @param amount the amount your setting the stat to
     */
    void set(int id, int amount);

    /**
     * @param id the id of the stat your changing
     * @param amount the amount your setting the stat to
     */
    void setP(int id, int amount);

    /**
     * @param id the id of the stat your grabbing
     */
    int get(int id);

    /**
     * @param id the id of the stat your grabbing
     */
    int getP(int id);

    int updateStatus();

    int getLevel();

    void grantAbility(Status.Ability ability);
    boolean grantSkill();
    boolean grantMagic();

    void setAbilities(Status.Ability[] abilities);
    void setSkills(Status.Skill[] skills);
    void setSpells(Status.Magic[] spells);

    Set<Status.Ability> levelUp();

    boolean canLevelUp();
    void setCanLevelUp(boolean canLevelUp);

    Status.Ability[] getAbilities();
    Status.Skill[] getSkills();
    Status.Magic[] getSpells();
}