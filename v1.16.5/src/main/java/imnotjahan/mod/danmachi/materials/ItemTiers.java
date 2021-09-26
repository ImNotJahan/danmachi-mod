package imnotjahan.mod.danmachi.materials;

import imnotjahan.mod.danmachi.init.Items;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum ItemTiers implements IItemTier
{
    HESTIA_KNIFE(3, 1000, 2.3F, 10.0F, 30, () -> {
        return Ingredient.of(Items.MYTHRIL_INGOT);
    }),
    DESPERATE(3, 1000, 1.9F, 25.0F, 10, () -> {
        return Ingredient.of(Items.ORICHALCUM_INGOT);
    }),
    HAKUGEN(3, 180, 2.0F, 25.0F, 15, () -> {
        return Ingredient.of(Items.UNICORN_HORN);
    }),
    DAGGER(2, 100, 1.7F, 4.0F, 1, () -> {
        return Ingredient.of(net.minecraft.item.Items.IRON_INGOT);
    }),
    SWORD_AIR(3, 400, 1.6F, 8.0F, 10, () -> {
        return Ingredient.of(Items.DAMASCUS_STEEL);
    }),
    MAGIC_SWORD(2, 3, .8F, 10.0F, 30, () -> {
        return Ingredient.of(Items.ADAMANTITE_INGOT);
    }),
    GREATSWORD(2, 180, .8F, 9.0F, 10, () -> {
        return Ingredient.of(net.minecraft.item.Items.IRON_INGOT);
    }),
    KOTETSU(2, 140, 1.8F, 7.0F, 20, () -> {
        return Ingredient.of(Items.LYGERFANG_FANG);
    }),
    URGA(2, 500, .7F, 40.0F, 5, () -> {
        return Ingredient.of(Items.URGA_BLADE);
    }),
    KODACHI_FUTABA(2, 300, 1.7F, 16.0F, 5, () -> {
        return Ingredient.of(net.minecraft.item.Items.GOLD_INGOT);
    }),
    FORTIA_SPEAR(2, 400, 2F, 30.0F, 5, () -> {
        return Ingredient.of(Items.ADAMANTITE_INGOT);
    }),
    KINGS_BLADE(2, 600, .8F, 40.0F, 5, () -> {
        return Ingredient.of(Items.ADAMANTITE_INGOT);
    }),
    SUPREME_BLACK_SWORD(2, 750, 1F, 50.0F, 5, () -> {
        return Ingredient.of(Items.ADAMANTITE_INGOT);
    }),
    ALFS_LUMINA(2, 400, 1.9F, 20.0F, 5, () -> {
        return Ingredient.of(net.minecraft.item.Items.SPRUCE_WOOD);
    }),
    GREAT_PODAO_ZAGA(2, 500, .9F, 28.0F, 5, () -> {
        return Ingredient.of(Items.ADAMANTITE_INGOT);
    }),
    PROTAGONISTA(2, 200, 1.6F, 20.0F, 5, () -> {
        return Ingredient.of(net.minecraft.item.Items.GOLD_INGOT);
    });

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    ItemTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = -1 * (speed - 1.2F);
        this.damage = damage - 5;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}