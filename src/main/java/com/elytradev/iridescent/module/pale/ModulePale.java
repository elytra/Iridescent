package com.elytradev.iridescent.module.pale;

import java.util.Set;

import com.elytradev.iridescent.Goal;
import com.elytradev.iridescent.module.Module;
import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModulePale extends Module {

	@Override
	public String getName() {
		return "Pale";
	}
	
	@Override
	public String getDescription() {
		return "Adds a new potion effect, Pale, and replaces Rotten Flesh's Hunger effect with it. Pale gives you a vulnerability to sunlight, weakness to arthropods, and resistance to undead.";
	}
	
	@Override
	public Set<Goal> getGoals() {
		return ImmutableSet.of(Goal.IMPROVE_VANILLA, Goal.BE_UNIQUE);
	}
	
	public static Potion PALE;
	public static SoundEvent SIZZLE;
	
	@Override
	public void onPreInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(new ItemPaleRottenFlesh()
			.setUnlocalizedName("rottenFlesh")
			.setRegistryName("minecraft:rotten_flesh"));
	}
	
	@SubscribeEvent
	public void onRegisterSounds(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().register(SIZZLE = new SoundEvent(new ResourceLocation("iridescent", "sizzle")).setRegistryName("sizzle"));
	}
	
	@SubscribeEvent
	public void onRegisterPotions(RegistryEvent.Register<Potion> e) {
		e.getRegistry().register(PALE = new PotionPale()
				.setRegistryName("pale")
				.setPotionName("effect.iridescent.pale"));
	}
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteract e) {
		if (e.getTarget() instanceof EntityWolf &&
				e.getItemStack().getItem() instanceof ItemPaleRottenFlesh) {
			// poor doggo :(
			EntityWolf ew = (EntityWolf)e.getTarget();
			int dur = 3600;
			int lvl = 0;
			PotionEffect active = ew.getActivePotionEffect(PALE);
			if (active != null) {
				dur = active.getDuration()+200;
				lvl = active.getAmplifier()+1;
				if (lvl > 3) {
					lvl = 3;
				}
			}
			ew.addPotionEffect(new PotionEffect(PALE, dur, lvl));
			ew.heal(1);
			e.getItemStack().shrink(1);
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent e) {
		PotionEffect pale = e.getEntityLiving().getActivePotionEffect(PALE);
		if (pale != null) {
			if (e.getSource() instanceof EntityDamageSource) {
				Entity attacker = ((EntityDamageSource)e.getSource()).getTrueSource();
				if (attacker instanceof EntityLivingBase) {
					EntityLivingBase ec = (EntityLivingBase)attacker;
					float mult = 1;
					if (ec.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
						mult += ((pale.getAmplifier()+1)/5f);
					} else if (ec.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
						mult -= ((pale.getAmplifier()+1)/10f);
					}
					e.setAmount(e.getAmount()*mult);
				}
			}
		}
	}
	
}
