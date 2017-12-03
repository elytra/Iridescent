package com.elytradev.iridescent.module.spiritgraves;

import java.util.Set;

import com.elytradev.iridescent.Iridescent;
import com.elytradev.iridescent.Goal;
import com.elytradev.iridescent.module.Module;
import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModuleSpiritGraves extends Module {

	@Override
	public String getName() {
		return "Spirit Graves";
	}

	@Override
	public String getDescription() {
		return "Spawns balls of smoke when you die which don't despawn, and can be broken to get your items back (in the same slots they were in originally!)";
	}

	@Override
	public Set<Goal> getGoals() {
		return ImmutableSet.of(Goal.IMPROVE_VANILLA, Goal.REDUCE_FRICTION, Goal.BE_UNIQUE);
	}
	
	public static SoundEvent SPIRIT;
	public static SoundEvent DISPEL;
	
	@Override
	public void onPreInit(FMLPreInitializationEvent e) {
		EntityRegistry.registerModEntity(new ResourceLocation("iridescent", "spirit_grave"), EntityGrave.class, "spirit_grave", Iridescent.nextEntityId++, Iridescent.inst, 64, 2, true);
		MinecraftForge.EVENT_BUS.register(this);
		Iridescent.inst.network.register(GraveDispelMessage.class);
	}
	
	@SubscribeEvent
	public void onRegisterSounds(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().register(SPIRIT = new SoundEvent(new ResourceLocation("iridescent", "spirit")).setRegistryName("spirit"));
		e.getRegistry().register(DISPEL = new SoundEvent(new ResourceLocation("iridescent", "dispel")).setRegistryName("dispel"));
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onDeath(LivingDeathEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)e.getEntity();
			EntityGrave grave = new EntityGrave(e.getEntity().world);
			grave.setPosition(player.posX, player.posY-0.5, player.posZ);
			grave.motionY = 0.85;
			grave.populateFrom(player);
			if (!grave.isEmpty()) {
				grave.clear(player);
				player.world.spawnEntity(grave);
			} else {
				player.sendMessage(new TextComponentTranslation("iridescent.graveEmpty"));
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onDrops(PlayerDropsEvent e) {
		EntityGrave eg = (EntityGrave)e.getEntityPlayer().world.findNearestEntityWithinAABB(EntityGrave.class, e.getEntityPlayer().getEntityBoundingBox().expand(0.5, 0.5, 0.5), e.getEntityPlayer());
		if (eg != null) {
			for (EntityItem ei : e.getDrops()) {
				eg.addExtra(ei.getItem());
				ei.setDead();
			}
			e.getDrops().clear();
		}
	}

}
