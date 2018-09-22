package com.elytradev.iridescent.module.stoned;

import java.util.Set;

import com.elytradev.iridescent.Goal;
import com.elytradev.iridescent.module.ModuleClient;
import com.google.common.collect.ImmutableSet;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleStonedClient extends ModuleClient {

	@Override
	public String getName() {
		return "Stoned (Client)";
	}

	@Override
	public String getDescription() {
		return "Registers item models for the Stoned module.";
	}

	@Override
	public Set<Goal> getGoals() {
		return ImmutableSet.of();
	}
	
	@Override
	public void onPreInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent e) {
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.COBBLESTONE_SWORD, 0, new ModelResourceLocation("iridescent:cobblestone_sword#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.COBBLESTONE_HOE, 0, new ModelResourceLocation("iridescent:cobblestone_hoe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.COBBLESTONE_PICKAXE, 0, new ModelResourceLocation("iridescent:cobblestone_pickaxe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.COBBLESTONE_AXE, 0, new ModelResourceLocation("iridescent:cobblestone_axe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.COBBLESTONE_SHOVEL, 0, new ModelResourceLocation("iridescent:cobblestone_shovel#inventory"));
		
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.GRANITE_SWORD, 0, new ModelResourceLocation("iridescent:granite_sword#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.GRANITE_HOE, 0, new ModelResourceLocation("iridescent:granite_hoe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.GRANITE_PICKAXE, 0, new ModelResourceLocation("iridescent:granite_pickaxe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.GRANITE_AXE, 0, new ModelResourceLocation("iridescent:granite_axe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.GRANITE_SHOVEL, 0, new ModelResourceLocation("iridescent:granite_shovel#inventory"));
		
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.ANDESITE_SWORD, 0, new ModelResourceLocation("iridescent:andesite_sword#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.ANDESITE_HOE, 0, new ModelResourceLocation("iridescent:andesite_hoe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.ANDESITE_PICKAXE, 0, new ModelResourceLocation("iridescent:andesite_pickaxe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.ANDESITE_AXE, 0, new ModelResourceLocation("iridescent:andesite_axe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.ANDESITE_SHOVEL, 0, new ModelResourceLocation("iridescent:andesite_shovel#inventory"));
		
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.DIORITE_SWORD, 0, new ModelResourceLocation("iridescent:diorite_sword#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.DIORITE_HOE, 0, new ModelResourceLocation("iridescent:diorite_hoe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.DIORITE_PICKAXE, 0, new ModelResourceLocation("iridescent:diorite_pickaxe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.DIORITE_AXE, 0, new ModelResourceLocation("iridescent:diorite_axe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.DIORITE_SHOVEL, 0, new ModelResourceLocation("iridescent:diorite_shovel#inventory"));
		
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.FLINT_SWORD, 0, new ModelResourceLocation("iridescent:flint_sword#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.FLINT_HOE, 0, new ModelResourceLocation("iridescent:flint_hoe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.FLINT_PICKAXE, 0, new ModelResourceLocation("iridescent:flint_pickaxe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.FLINT_AXE, 0, new ModelResourceLocation("iridescent:flint_axe#inventory"));
		ModelLoader.setCustomModelResourceLocation(ModuleStoned.FLINT_SHOVEL, 0, new ModelResourceLocation("iridescent:flint_shovel#inventory"));
	}

}
