package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.entities.templates.MonsterBase;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Goblin extends MonsterBase
{
    public Goblin(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_, "goblin");
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        final double[] attributes = STD.SDAStringToDict(Config.COMMON.mobStats.get()).get("goblin");

        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, attributes[0])
                .add(Attributes.MAX_HEALTH, attributes[1])
                .add(Attributes.MOVEMENT_SPEED, attributes[2])
                .add(Attributes.ARMOR, attributes[3]);
    }

    @Override
    public boolean canPickUpLoot()
    {
        return true;
    }

    @Override
    protected ResourceLocation getDefaultLootTable()
    {
        return new ResourceLocation("danmachi", "goblin");
    }
}
