package com.elytradev.iridescent.module;

import java.util.Set;

import com.elytradev.iridescent.Goal;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class Module {

	public void onPreInit(FMLPreInitializationEvent e) {}
	public void onInit(FMLInitializationEvent e) {}
	public void onPostInit(FMLPostInitializationEvent e) {}
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract Set<Goal> getGoals();
	
	// Like dependencies, but for lazy people
	public int getWeight() { return 0; }
	
}
