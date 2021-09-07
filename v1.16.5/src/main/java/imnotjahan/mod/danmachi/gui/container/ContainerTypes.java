package imnotjahan.mod.danmachi.gui.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ContainerTypes<T extends Container> extends net.minecraftforge.registries.ForgeRegistryEntry<ContainerType<?>> implements net.minecraftforge.common.extensions.IForgeContainerType<T>
{
    public static final ContainerType<SmithingContainer> SMITHING = register("smithing", SmithingContainer::new);
    private final ContainerType.IFactory<T> constructor;

    public ContainerTypes(ContainerType.IFactory<T> p_i50072_1_) {
        this.constructor = p_i50072_1_;
    }

    private static <T extends Container> ContainerType<T> register(String p_221505_0_, ContainerType.IFactory<T> p_221505_1_) {
        return Registry.register(Registry.MENU, p_221505_0_, new ContainerType<>(p_221505_1_));
    }

    @OnlyIn(Dist.CLIENT)
    public T create(int p_221506_1_, PlayerInventory p_221506_2_) {
        return this.constructor.create(p_221506_1_, p_221506_2_);
    }

    @Override
    public T create(int windowId, PlayerInventory playerInv, net.minecraft.network.PacketBuffer extraData) {
        if (this.constructor instanceof net.minecraftforge.fml.network.IContainerFactory) {
            return ((net.minecraftforge.fml.network.IContainerFactory<T>) this.constructor).create(windowId, playerInv, extraData);
        }
        return create(windowId, playerInv);
    }
}
