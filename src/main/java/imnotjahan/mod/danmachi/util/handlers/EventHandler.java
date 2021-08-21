package imnotjahan.mod.danmachi.util.handlers;

import imnotjahan.mod.danmachi.capabilities.IStatus;
import imnotjahan.mod.danmachi.capabilities.Status;
import imnotjahan.mod.danmachi.capabilities.StatusProvider;
import imnotjahan.mod.danmachi.config.ModConfig;
import imnotjahan.mod.danmachi.gui.MindGUI;
import imnotjahan.mod.danmachi.network.MessageStatus;
import imnotjahan.mod.danmachi.network.NetworkHandler;
import imnotjahan.mod.danmachi.util.ClientThings;
import imnotjahan.mod.danmachi.util.Reference;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import java.util.Arrays;

public class EventHandler
{
    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.getEntityLiving();
            IStatus cap = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

            if(cap.getFalna())
            {
                player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20 + Math.floor((cap.get(2) + (cap.getLevel() - 1) * 1000)) / 100);
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1F + (cap.get(3) + (cap.getLevel() - 1) * 1000) / 10000);
                int dmg = (cap.get(1) + (cap.getLevel() - 1) * 1000) / 300 + 2;

                Status.Skill[] skills = cap.getSkills();
                for (Status.Skill skill : skills)
                {
                    switch (skill.toInt())
                    {
                        case 0:
                            if (player.isSprinting())
                            {
                                dmg += 2 + (cap.get(3) + (cap.getLevel() - 1) * 1000) / 1000;
                            }
                            break;

                        case 1:
                            player.removePotionEffect(MobEffects.NAUSEA);
                            player.removePotionEffect(MobEffects.MINING_FATIGUE);
                            break;
                    }
                }

                Status.Ability[] abilities = cap.getAbilities();
                for (Status.Ability ability : abilities)
                {
                    switch (ability.toInt())
                    {
                        case 0:
                            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1));
                            player.removePotionEffect(Potion.getPotionById(19));
                            break;

                        case 6:
                            player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 1,
                                    ability.getStat() + 1));
                            break;
                    }
                }

                player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(dmg);
            }

            if(player.world.provider.getDimension() == Reference.DUNGEON_ID)
            {
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 200, 1, false, false));
            }
        }
    }

    @SubscribeEvent
    public void pleaseRefresh(EntityJoinWorldEvent event)
    {
        if(event.getEntity() instanceof EntityPlayerMP)
        {
            NetworkHandler.refreshThing(
                    new MessageStatus(
                            event.getEntity().getCapability(StatusProvider.STATUS_CAP,
                                    Status.capSide),
                            (EntityPlayer)event.getEntity()),
                    (EntityPlayerMP) event.getEntity());
        }
    }

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {
        if(ClientThings.getPlayer().getCapability(StatusProvider.STATUS_CAP, Status.capSide).getSpells().length > 0)
        {
            new MindGUI();
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        IStatus oldCap = event.getOriginal().getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        IStatus cap = event.getEntityPlayer().getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        for(int k = 0; k <= 7; k++)
        {
            cap.set(k, oldCap.get(k));
        }

        for(int k = 0; k <= 5; k++)
        {
            cap.setP(k, oldCap.getP(k));
        }

        cap.setFamilia(oldCap.getFamilia());
        cap.setSkills(oldCap.getSkills());
        cap.setAbilities(oldCap.getAbilities());
        cap.setAbilities(oldCap.getAbilities());
    }

    @SubscribeEvent
    public void onPlayerLogsIn(PlayerLoggedInEvent event)
    {
        EntityPlayer player = event.player;
        IStatus status = player.getCapability(StatusProvider.STATUS_CAP, Status.capSide);

        NetworkHandler.refreshThing(new MessageStatus(status, player), (EntityPlayerMP)player);
    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event)
    {
        if(Arrays.asList(event.getEntityPlayer().getCapability(StatusProvider.STATUS_CAP, Status.capSide).getSkills()).contains(Status.Skill.Avenger))
        {
            if(event.getTarget().isCreatureType(EnumCreatureType.MONSTER, true))
            {
               event.getTarget().attackEntityFrom(DamageSource.causePlayerDamage(event.getEntityPlayer()), 2);
            }
        }
    }

    @SubscribeEvent
    public void dimensionChange(PlayerChangedDimensionEvent event)
    {
        if(event.fromDim == Reference.DUNGEON_ID)
        {
            event.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }

        NetworkHandler.refreshThing(
                new MessageStatus(
                        event.player.getCapability(StatusProvider.STATUS_CAP,
                                Status.capSide),
                        event.player),
                (EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent event)
    {
        if(!(event.getEntity() instanceof EntityPlayer) && event.getWorld().provider.getDimension() == Reference.DUNGEON_ID)
        {
            int height = (int)(ModConfig.dungeonHeight - event.getY());
            int floor = (int)Math.floor(height != 0 ? height / ModConfig.dungeonFloorHeight + 1 : 1);

            switch(event.getEntity().getName())
            {
                case "Goblin":
                case "Kobold":
                    if(!((floor >= 1 && floor <= 4) || (floor >= 8 && floor <= 9)))
                    {
                        event.setResult(Event.Result.DENY);
                    }
                    break;

                case "Killer Ant":
                case "Needle Rabbit":
                    if(floor != 7)
                    {
                        event.setResult(Event.Result.DENY);
                    }
                    break;

                case "Almiraj":
                    if(!(floor == 13 || floor == 14))
                    {
                        event.setResult(Event.Result.DENY);
                    }
                    break;

                case "Hellhound":
                    if(!((floor >= 13 && floor <= 17)))
                    {
                        event.setResult(Event.Result.DENY);
                    }
                    break;

                case "Minotaur":
                case "Lygerfang":
                    if(!((floor >= 15 && floor <= 17)))
                    {
                        event.setResult(Event.Result.DENY);
                    }
                    break;

                case "Unicorn":
                    if(!((floor >= 19 && floor <= 24)))
                    {
                        event.setResult(Event.Result.DENY);
                    }
                    break;
            }
        }
    }
}