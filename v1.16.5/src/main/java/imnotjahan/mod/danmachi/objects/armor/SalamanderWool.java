package imnotjahan.mod.danmachi.objects.armor;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.materials.ArmorMaterials;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class SalamanderWool extends SizeableArmor
{
    private final SalamanderType type;
    public SalamanderWool(IArmorMaterial material, SalamanderType type)
    {
        super(material, EquipmentSlotType.CHEST, (new Item.Properties()).tab(Main.ToolGroup));

        this.type = type;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {
        switch(type.id)
        {
            case 0:
                player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2, 1, false,
                        false, false));
                break;

            case 1:
                player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 1, 1, false,
                        false, false));
                break;

            case 2:
                player.removeEffect(Effects.POISON);
                player.removeEffect(Effects.WITHER);
                break;
        }

        super.onArmorTick(stack, world, player);
    }
}