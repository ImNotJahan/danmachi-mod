package imnotjahan.mod.danmachi.objects.items;

import imnotjahan.mod.danmachi.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;

public class Needle extends Item
{
    public Needle()
    {
        super(new Item.Properties().tab(Main.MaterialsGroup));
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context)
    {
        PlayerEntity player = context.getPlayer();

        if(player != null)
        {
            player.hurt(DamageSource.GENERIC, 1);
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }
}
