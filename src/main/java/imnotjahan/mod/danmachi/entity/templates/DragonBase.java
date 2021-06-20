package imnotjahan.mod.danmachi.entity.templates;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DragonBase extends MonsterBase
{
    public DragonBase(World worldIn, String name)
    {
        super(worldIn, name);
    }

    public double[] getMovementOffsets(int p_70974_1_, float p_70974_2_)
    {
        if (this.getHealth() <= 0.0F)
        {
            p_70974_2_ = 0.0F;
        }

        p_70974_2_ = 1.0F - p_70974_2_;
        int i = p_70974_1_ & 63;
        int j = p_70974_1_ - 1 & 63;
        double[] adouble = new double[3];
        double d0 = 0;
        double d1 = MathHelper.wrapDegrees(d0);
        adouble[0] = d0 + d1 * (double)p_70974_2_;
        d0 = 1;
        d1 = 1 - d0;
        adouble[1] = d0 + d1 * (double)p_70974_2_;
        adouble[2] = (double)p_70974_2_;
        return adouble;
    }
}
