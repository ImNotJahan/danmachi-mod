package imnotjahan.mod.danmachi.objects.armor;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.init.ItemInit;
import imnotjahan.mod.danmachi.util.interfaces.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ArmorBase extends ItemArmor implements IHasModel
{
    public ArmorBase(String name, ArmorMaterial armorMaterial, EntityEquipmentSlot entityEquipmentSlot)
    {
        super(armorMaterial, 1, entityEquipmentSlot);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.creativeTab);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        switch(getRegistryName().toString())
        {
            case "danmachi:salamander_wool_cloak":
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 1, 1, false, false));
                break;

            case "danmachi:hades_head":
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 2, 1, false, false));
                break;
        }
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        if(itemStack != ItemStack.EMPTY)
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                ModelPlayer model = new ModelPlayer(0.1f, false);

                model.bipedHead.showModel = true;

                model.isChild = _default.isChild;
                model.isRiding = _default.isRiding;
                model.isSneak = _default.isSneak;
                model.rightArmPose = _default.rightArmPose;
                model.leftArmPose = _default.leftArmPose;

                return model;
            }
        }

        return null;
    }
}
