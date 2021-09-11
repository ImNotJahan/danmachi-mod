package imnotjahan.mod.danmachi.objects.blocks.tileentities;

import imnotjahan.mod.danmachi.gui.container.SmithingContainer;
import imnotjahan.mod.danmachi.util.events.EventSubscriber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SmithingTile extends TileEntity implements INamedContainerProvider
{
    public SmithingTile()
    {
        super(EventSubscriber.smithingTile);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new StringTextComponent("smithing_anvil");
    }

    @Override
    public Container createMenu(int windowID, PlayerInventory inv, PlayerEntity player)
    {
        return new SmithingContainer(windowID, inv);
    }
}
