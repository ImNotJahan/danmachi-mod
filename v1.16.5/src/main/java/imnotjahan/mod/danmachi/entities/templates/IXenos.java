package imnotjahan.mod.danmachi.entities.templates;

public interface IXenos
{
    int CHANCE = 10;

    default void rollForXenos()
    {
        setXenos(Math.round(Math.random() * CHANCE) == 0);
    }

    void setXenos(boolean isXenos);
    boolean getXenos();
}
