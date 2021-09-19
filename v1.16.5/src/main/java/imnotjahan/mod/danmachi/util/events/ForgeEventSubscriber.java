package imnotjahan.mod.danmachi.util.events;

import imnotjahan.mod.danmachi.Reference;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.commands.DanmachiLog;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import imnotjahan.mod.danmachi.networking.packets.MessageStatus;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ForgeEventSubscriber
{
    // Making status do stuff
    public static Map<String, Double> statMultipliers = new HashMap<String, Double>()
    {{
        put("attack", .005D);
        put("vitality", .01D);
        put("movement", .0001D);
        put("atkspeed", .0001D);
    }};

    static UUID attackUUID = UUID.randomUUID();
    static UUID vitalityUUID = UUID.randomUUID();
    static UUID movementUUID = UUID.randomUUID();
    static UUID atkSpeedUUID = UUID.randomUUID(); // atk = attack

    static AttributeModifier attackModifier = new AttributeModifier(attackUUID, "Attack Stat", 0,
            AttributeModifier.Operation.ADDITION);
    static AttributeModifier vitalityModifier = new AttributeModifier(vitalityUUID, "Vitality Stat", 0,
            AttributeModifier.Operation.ADDITION);
    static AttributeModifier movementModifier = new AttributeModifier(movementUUID, "Movement Stat", 0,
            AttributeModifier.Operation.ADDITION);
    static AttributeModifier atkSpeedModifier = new AttributeModifier(atkSpeedUUID, "Attack Speed Stat", 0,
            AttributeModifier.Operation.ADDITION);

    @SubscribeEvent
    public static void applyPlayerStats(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            IStatus status = player.getCapability(StatusProvider.STATUS_CAP).orElseThrow(MissingStatus::new);

            int level = status.get(7);

            double attack = status.get(1) * 1000 * level * statMultipliers.get("attack");
            double vitality = status.get(2) * 1000 * level * statMultipliers.get("vitality");
            double movement = status.get(4) * 1000 * level * statMultipliers.get("movement");
            double atkSpeed = status.get(4) * 1000 * level * statMultipliers.get("atkspeed");

            if(attack != attackModifier.getAmount())
            {
                ModifiableAttributeInstance atkDamage = player.getAttribute(Attributes.ATTACK_DAMAGE);
                if(atkDamage.hasModifier(attackModifier))
                {
                    atkDamage.removeModifier(attackUUID);
                }

                attackModifier = new AttributeModifier(attackUUID, "Attack Stat", attack,
                        AttributeModifier.Operation.ADDITION);

                player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(attackModifier);
            }

            if(vitality != vitalityModifier.getAmount())
            {
                ModifiableAttributeInstance maxHp = player.getAttribute(Attributes.MAX_HEALTH);
                if(maxHp.hasModifier(vitalityModifier))
                {
                    maxHp.removeModifier(vitalityUUID);
                }

                vitalityModifier = new AttributeModifier(vitalityUUID, "Vitality Stat", vitality,
                        AttributeModifier.Operation.ADDITION);

                player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(vitalityModifier);
            }

            if(movement != movementModifier.getAmount())
            {
                ModifiableAttributeInstance speed = player.getAttribute(Attributes.MOVEMENT_SPEED);
                if(speed.hasModifier(movementModifier))
                {
                    speed.removeModifier(movementUUID);
                }

                movementModifier = new AttributeModifier(movementUUID, "Movement Stat", movement,
                        AttributeModifier.Operation.ADDITION);

                player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(movementModifier);
            }

            if(atkSpeed != atkSpeedModifier.getAmount())
            {
                ModifiableAttributeInstance atkSpeedAttribute = player.getAttribute(Attributes.ATTACK_SPEED);
                if(atkSpeedAttribute.hasModifier(atkSpeedModifier))
                {
                    atkSpeedAttribute.removeModifier(atkSpeedUUID);
                }

                atkSpeedModifier = new AttributeModifier(atkSpeedUUID, "Attack Speed Stat", atkSpeed,
                        AttributeModifier.Operation.ADDITION);

                player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(atkSpeedModifier);
            }
        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event)
    {
        new DanmachiLog(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(new ResourceLocation(Reference.MODID, "status"),
                new StatusProvider());
    }

    // Network refreshing
    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageStatus(
                player.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElseThrow(ArithmeticException::new)));
    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event)
    {
        if(!(event.getEntity() instanceof PlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageStatus(
                player.getCapability(StatusProvider.STATUS_CAP, Status.capSide).orElseThrow(ArithmeticException::new)));
    }
}
