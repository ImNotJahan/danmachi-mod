package imnotjahan.mod.danmachi.world.biome;

import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.entity.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.biome.Biome;

public class BiomeDungeon extends Biome
{
    public BiomeDungeon()
    {
        super(new BiomeProperties("Dungeon")
                .setBaseHeight(2)
                .setHeightVariation(0)
                .setRainDisabled()
                .setWaterColor(4710833));

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityGoblin.class, ModConfig.spawnRates.get("goblin"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityKobold.class, ModConfig.spawnRates.get("kobold"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityKillerAnt.class, ModConfig.spawnRates.get("killer_ant"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityAlmiraj.class, ModConfig.spawnRates.get("almiraj"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityLygerfang.class, ModConfig.spawnRates.get("lygerfang"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMinotaur.class, ModConfig.spawnRates.get("minotaur"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityNeedleRabbit.class, ModConfig.spawnRates.get("needle_rabbit"), 1, 20));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityUnicorn.class, ModConfig.spawnRates.get("unicorn"), 1, 200));
    }
}
