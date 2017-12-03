package com.elytradev.iridescent.module.obelisk;

import java.util.List;
import java.util.Set;

import com.elytradev.iridescent.Cardinal;
import com.elytradev.iridescent.Goal;
import com.elytradev.iridescent.module.Module;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules.ValueType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModuleObelisk extends Module {

	@Override
	public String getName() {
		return "Obelisk";
	}
	
	@Override
	public String getDescription() {
		return "Disables beds, and adds periodically generating Obelisks to the world which can be used to set your spawn.";
	}
	
	@Override
	public Set<Goal> getGoals() {
		return ImmutableSet.of(Goal.ENCOURAGE_INFRASTRUCTURE, Goal.BE_UNIQUE);
	}
	
	public static BlockObelisk OBELISK_BLOCK;
	public static SoundEvent PULSATING;
	public static SoundEvent ATTUNE;
	public static SoundEvent HOLLOWHIT;
	
	@Override
	public void onPreInit(FMLPreInitializationEvent e) {
		GameRegistry.registerTileEntity(TileEntityObelisk.class, "iridescent:obelisk");
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> e) {
		e.getRegistry().register(OBELISK_BLOCK = (BlockObelisk)(new BlockObelisk(Material.ROCK, MapColor.BLACK)
				.setLightLevel(0.15f)
				.setBlockUnbreakable()
				.setResistance(4000000)
				.setUnlocalizedName("iridescent.obelisk_block")
				.setRegistryName("obelisk_block")));
	}
	
	@SubscribeEvent
	public void onRegisterSounds(RegistryEvent.Register<SoundEvent> e) {
		e.getRegistry().register(PULSATING = new SoundEvent(new ResourceLocation("iridescent", "pulsating")).setRegistryName("pulsating"));
		e.getRegistry().register(ATTUNE = new SoundEvent(new ResourceLocation("iridescent", "attune")).setRegistryName("attune"));
		e.getRegistry().register(HOLLOWHIT = new SoundEvent(new ResourceLocation("iridescent", "hollowhit")).setRegistryName("hollowhit"));
		
		OBELISK_BLOCK.setSoundType(new SoundType(1, 1, HOLLOWHIT, HOLLOWHIT, HOLLOWHIT, HOLLOWHIT, HOLLOWHIT));
	}
	
	@SubscribeEvent
	public void onBreak(BlockEvent.BreakEvent e) {
		if (e.getState().getBlock() == OBELISK_BLOCK) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onPlace(BlockEvent.PlaceEvent e) {
		if (e.getPlacedBlock().getBlock() == Blocks.BED) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onBedCheck(PlayerSleepInBedEvent e) {
		e.setResult(SleepResult.OTHER_PROBLEM);
	}
	
	@SubscribeEvent
	public void onPopulate(ChunkGeneratorEvent.ReplaceBiomeBlocks e) {
		if (e.getWorld().provider.isSurfaceWorld()) {
			// This is DEFINITELY not a hack. Nope. Not at all.
			GenerateObelisk.generate(e.getX(), e.getZ(), e.getWorld().getSeed(), e.getPrimer());
		}
	}
	
	@SubscribeEvent
	public void onPopulate(PopulateChunkEvent.Pre e) {
		if (e.getWorld().provider.isSurfaceWorld()) {
			GenerateObelisk.populate(e.getChunkX(), e.getChunkZ(), e.getWorld().getSeed(), e.getWorld());
		}
	}
	
	@SubscribeEvent
	public void onWake(PlayerWakeUpEvent e) {
		
	}
	
	@SubscribeEvent
	public void onSpawnPoint(WorldEvent.CreateSpawnPosition e) {
		int x = 0;
		int z = 0;
		Cardinal dir = Cardinal.WEST;
		int legLength = 0;
		int i = 0;
		int j = 0;
		BiomeProvider biomeprovider = e.getWorld().getBiomeProvider();
        List<Biome> allowedBiomes = biomeprovider.getBiomesToSpawnIn();
		// scan in a counterclockwise outward spiral from 0, 0
		// i.e. find the closest point to 0, 0 that contains an obelisk and has a valid spawn biome
		while (true) {
			if (GenerateObelisk.isObeliskChunk(e.getWorld().getSeed(), x, z)) {
				boolean anySuitable = false;
				for (byte biome : e.getWorld().getChunkFromChunkCoords(x, z).getBiomeArray()) {
					Biome b = Biome.getBiome(biome);
					if (allowedBiomes.contains(b)) {
						anySuitable = true;
						break;
					}
				}
				if (anySuitable) {
					break;
				}
			}
			if (i >= legLength) {
				dir = dir.ccw();
				i = 0;
				j++;
				if (j % 2 == 0) {
					legLength++;
				}
			}
			x += dir.xOfs();
			z += dir.yOfs();
			i++;
		}
		
		int spawnX = (x*16)+8;
		int spawnZ = (z*16)+10;
		int spawnY = e.getWorld().getChunkFromChunkCoords(x, z).getHeightValue(6, 8);
		
		e.getWorld().getWorldInfo().setSpawn(new BlockPos(spawnX, spawnY, spawnZ));
		e.getWorld().getGameRules().addGameRule("spawnRadius", "0", ValueType.NUMERICAL_VALUE);
		e.setCanceled(true);
	}
	
}
