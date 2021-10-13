package imnotjahan.mod.danmachi.util.events;

import com.mojang.serialization.Codec;
import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.commands.ChangeStatus;
import imnotjahan.mod.danmachi.commands.DungeonCommand;
import imnotjahan.mod.danmachi.commands.RefreshStatus;
import imnotjahan.mod.danmachi.networking.PacketHandler;
import imnotjahan.mod.danmachi.util.STD;
import imnotjahan.mod.danmachi.util.config.Config;
import imnotjahan.mod.danmachi.util.exceptions.MissingStatus;
import imnotjahan.mod.danmachi.world.dimension.chunkgenerators.DungeonChunkGenerator;
import imnotjahan.mod.danmachi.world.structures.StructureGeneration;
import imnotjahan.mod.danmachi.world.structures.Structures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.server.command.ConfigCommand;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
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

            if(!status.getFalna()) return;

            int level = status.get(7);

            double attack = Math.pow((status.get(1) + (1000 * (level - 1))) * statMultipliers.get("attack"), 1.4D);
            double vitality = Math.pow((status.get(2) + (1000 * (level - 1))) * statMultipliers.get("vitality"), 1.1D);
            double movement = (status.get(4) + (1000 * (level - 1))) * statMultipliers.get("movement");
            double atkSpeed = (status.get(4) + (1000 * (level - 1))) * statMultipliers.get("atkspeed");

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
        new ChangeStatus(event.getDispatcher());
        new DungeonCommand(event.getDispatcher());
        new RefreshStatus(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(new ResourceLocation(Main.MODID, "status"),
                new StatusProvider());
    }

    // Make monsters only spawn on certain floors of the dungeon
    @SubscribeEvent
    public static void entitySpawn(LivingSpawnEvent event)
    {
        if(!event.getEntity().level.dimension().location().toString().equals("danmachi:dungeon_dimension")) return;

        final String registryName = event.getEntity().getType().getRegistryName().toString();
        final Map<String, Integer[]> spawnableFloors = STD.SIAStringToDict(Config.COMMON.spawnableFloors.get());
        final int oppositeY = DungeonChunkGenerator.DUNGEON_HEIGHT - (int)event.getY();
        final int floor = (int)Math.floor(oppositeY != 0 ? oppositeY / DungeonChunkGenerator.FLOOR_HEIGHT + 1 : 1);

        if(spawnableFloors.containsKey(registryName))
        {
            System.out.println("contains");
            event.setCanceled(!Arrays.asList(spawnableFloors.get(registryName)).contains(floor));
        }
    }

    // Network refreshing
    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PacketHandler.refreshClient(player);
    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event)
    {
        if(!(event.getEntity() instanceof PlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
        PacketHandler.refreshClient(player);
    }

    @SubscribeEvent
    public static void changedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        PacketHandler.refreshClient((ServerPlayerEntity) event.getPlayer());
    }

    @SubscribeEvent
    public static void playerRespawned(PlayerEvent.PlayerRespawnEvent event)
    {
        PacketHandler.refreshClient((ServerPlayerEntity) event.getPlayer());
    }

    // For keeping status on death
    @SubscribeEvent
    public static void changeBackPlayersStatus(PlayerEvent.Clone event)
    {
        IStatus status = event.getPlayer().getCapability(StatusProvider.STATUS_CAP).orElseThrow(MissingStatus::new);
        IStatus oldStatus = event.getOriginal().getCapability(StatusProvider.STATUS_CAP).orElseThrow(MissingStatus::new);

        status.setArray(oldStatus.getArray());
        status.setFamilia(oldStatus.getFamilia());
    }

    // Structures
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {
        StructureGeneration.generateStructures(event); // Put this line first
    }

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event)
    {
        if(event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            try {
                Method GETCODEC_METHOD =
                        ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey(
                        (Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));

                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception e) {
                LogManager.getLogger().error("Was unable to check if " + serverWorld.dimension().location()
                        + " is using Terraforged's ChunkGenerator.");
            }

            // Prevent spawning our structure in Vanilla's superflat world
            if (serverWorld.getChunkSource().generator instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }

            // Adding our Structure to the Map
            Map<Structure<?>, StructureSeparationSettings> tempMap =
                    new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(Structures.HOUSE.get(),
                    DimensionStructuresSettings.DEFAULTS.get(Structures.HOUSE.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
