package imnotjahan.mod.danmachi.world;

import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.vecmath.Vector2d;

public class WorldData extends WorldSavedData
{
    private static final String DATA_NAME = Reference.MODID + "_WORLD";
    private static WorldData instance;

    private boolean generatedBabel = false;
    private BlockPos babelCenter = new BlockPos(0, 100, 0);
    private Vector2d[] stairChunks = new Vector2d[100];

    public WorldData()
    {
        super(DATA_NAME);
    }

    public WorldData(String name)
    {
        super(name);
    }

    public static WorldData get(World world)
    {
        MapStorage storage = world.getMapStorage();
        instance = (WorldData)storage.getOrLoadData(WorldData.class, DATA_NAME);

        if (instance == null)
        {
            instance = new WorldData();
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        generatedBabel = nbt.getBoolean("generated_babel");

        babelCenter = new BlockPos(
                nbt.getInteger("babel_x"),
                nbt.getInteger("babel_y"),
                nbt.getInteger("babel_z"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setBoolean("generated_babel", generatedBabel);

        compound.setInteger("babel_x", babelCenter.getX());
        compound.setInteger("babel_y", babelCenter.getY());
        compound.setInteger("babel_z", babelCenter.getZ());
        return compound;
    }

    public void createBabel()
    {
        this.generatedBabel = true;
        markDirty();
    }

    public boolean babelCreated()
    {
        return generatedBabel;
    }

    public BlockPos getBabelCenter()
    {
        return babelCenter;
    }

    public void setBabelCenter(BlockPos center)
    {
        babelCenter = center;
        markDirty();
    }
}
