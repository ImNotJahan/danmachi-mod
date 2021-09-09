package imnotjahan.mod.danmachi.entities;

import imnotjahan.mod.danmachi.gui.container.ContainerTypes;
import imnotjahan.mod.danmachi.gui.container.GuildContainer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class GuildMember extends CreatureEntity
{
    public GuildMember(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_)
    {
        super(p_i48575_1_, p_i48575_2_);
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        final boolean client = player.getCommandSenderWorld().isClientSide;

        if(!client)
        {
            player.openMenu(new SimpleNamedContainerProvider((id, inventory, type)
                    -> new GuildContainer(id, inventory),
                    new StringTextComponent("Guild Member")));
        }
        return ActionResultType.sidedSuccess(client);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes()
    {
        return LivingEntity.createLivingAttributes().add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_KNOCKBACK).add(Attributes.MAX_HEALTH, 20);
    }
}
