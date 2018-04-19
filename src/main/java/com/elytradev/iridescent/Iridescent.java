package com.elytradev.iridescent;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elytradev.concrete.network.NetworkContext;
import com.elytradev.iridescent.module.Module;
import com.elytradev.iridescent.module.ModuleClient;
import com.elytradev.iridescent.module.betterguiscale.ModuleBetterGuiScale;
import com.elytradev.iridescent.module.chair.ModuleChair;
import com.elytradev.iridescent.module.chair.client.ModuleChairClient;
import com.elytradev.iridescent.module.clearwater.ModuleClearWater;
import com.elytradev.iridescent.module.instantpickup.ModuleInstantPickup;
import com.elytradev.iridescent.module.obelisk.ModuleObelisk;
import com.elytradev.iridescent.module.obelisk.client.ModuleObeliskClient;
import com.elytradev.iridescent.module.pale.ModulePale;
import com.elytradev.iridescent.module.resizehelper.ModuleResizeHelper;
import com.elytradev.iridescent.module.spiritgraves.ModuleSpiritGraves;
import com.elytradev.iridescent.module.spiritgraves.client.ModuleSpiritGravesClient;
import com.elytradev.iridescent.module.stoned.ModuleStoned;
import com.elytradev.iridescent.module.stoned.ModuleStonedClient;
import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ProgressManager.ProgressBar;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Iridescent.MODID, name=Iridescent.NAME, version=Iridescent.VERSION)
public class Iridescent {
	
	public static final String MODID = "iridescent";
	public static final String NAME = "Iridescent";
	public static final String VERSION = "@VERSION@";

	public static final Logger log = LogManager.getLogger("Iridescent");
	
	@Instance
	public static Iridescent inst;
	public static int nextEntityId = 0;
	
	public final List<Module> modules = Lists.newArrayList();
	public NetworkContext network;
	
	public static boolean isModuleLoaded(Class<? extends Module> clazz) {
		for (Module m : inst.modules) {
			if (m.getClass().isAssignableFrom(clazz)) return true;
		}
		return false;
	}
	
	public static <T extends Module> T getModule(Class<T> clazz) {
		for (Module m : inst.modules) {
			if (m.getClass().isAssignableFrom(clazz)) {
				return (T)m;
			}
		}
		return null;
	}
	
	@EventHandler
	public void onConstructing(FMLConstructionEvent e) throws Exception {
		boolean client = e.getSide().isClient();
		Configuration c = new Configuration(new File("config/iridescent.cfg"));
		if (c.getBoolean("chair", "modules", true, "")) {
			modules.add(new ModuleChair());
			if (client) modules.add(new ModuleChairClient());
		}
		if (c.getBoolean("clearwater", "modules", true, "")) {
			modules.add(new ModuleClearWater());
		}
		if (c.getBoolean("instantpickup", "modules", true, "")) {
			modules.add(new ModuleInstantPickup());
		}
		if (c.getBoolean("obelisk", "modules", true, "")) {
			modules.add(new ModuleObelisk());
			if (client) modules.add(new ModuleObeliskClient());
		}
		if (c.getBoolean("pale", "modules", true, "")) {
			modules.add(new ModulePale());
		}
		if (c.getBoolean("resizehelper", "modules", true, "")) {
			if (client) modules.add(new ModuleResizeHelper());
		}
		if (c.getBoolean("spiritgraves", "modules", true, "")) {
			modules.add(new ModuleSpiritGraves());
			if (client) modules.add(new ModuleSpiritGravesClient());
		}
		if (c.getBoolean("stoned", "modules", true, "")) {
			modules.add(new ModuleStoned());
			if (client) modules.add(new ModuleStonedClient());
		}
		if (c.getBoolean("betterguiscale", "modules", true, "")) {
			if (client) modules.add(new ModuleBetterGuiScale());
		}
		c.save();
		
		
		Collections.sort(modules, (a, b) -> ComparisonChain.start()
				.compare(a.getWeight(), b.getWeight())
				.compare(a.getName(), b.getName())
				.result());
		for (Module m : modules) {
			log.info("Enabling {}module {}", m instanceof ModuleClient ? "client " : "", m.getName());
		}
		MetadataCollection mc = new MetadataCollection() {
			@Override
			public ModMetadata getMetadataForId(String modId, Map<String, Object> extraData) {
				if (modId.equals(MODID)) {
					ModMetadata mm = new ModMetadata();
					mm.name = NAME;
					mm.modId = MODID;
					mm.version = VERSION;
					mm.authorList = Lists.newArrayList("unascribed", "Falkreon");
					StringBuilder sb = new StringBuilder("Iridescent tweaks mod. Loaded modules:\n");
					for (Module m : modules) {
						sb.append("\u00A7m--\u00A7e ");
						sb.append(m.getName());
						sb.append("\u00A7r \u00A7m--\n");
						sb.append(m.getDescription());
						if (!m.getGoals().isEmpty()) {
							sb.append("\n");
							for (Goal g : m.getGoals()) {
								sb.append(" - ");
								sb.append(g.description);
								sb.append("\n");
							}
						}
						sb.append("\n\n");
					}
					mm.description = sb.toString();
					return mm;
				}
				return super.getMetadataForId(modId, extraData);
			}
		};
		Loader.instance().activeModContainer().bindMetadata(mc);
	}
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		network = NetworkContext.forChannel("iridescent");
		ProgressBar bar = ProgressManager.push("Pre initializing modules", modules.size());
		for (Module m : modules) {
			bar.step(m.getName());
			m.onPreInit(e);
		}
		ProgressManager.pop(bar);
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent e) {
		ProgressBar bar = ProgressManager.push("Initializing modules", modules.size());
		for (Module m : modules) {
			bar.step(m.getName());
			m.onInit(e);
		}
		ProgressManager.pop(bar);
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent e) {
		ProgressBar bar = ProgressManager.push("Initializing modules", modules.size());
		for (Module m : modules) {
			bar.step(m.getName());
			m.onPostInit(e);
		}
		ProgressManager.pop(bar);
	}
	
	public static void sendUpdatePacket(TileEntity te) {
		sendUpdatePacket(te, te.getUpdateTag());
	}
	
	public static void sendUpdatePacket(TileEntity te, NBTTagCompound nbt) {
		WorldServer ws = (WorldServer)te.getWorld();
		Chunk c = te.getWorld().getChunkFromBlockCoords(te.getPos());
		SPacketUpdateTileEntity packet = new SPacketUpdateTileEntity(te.getPos(), te.getBlockMetadata(), nbt);
		for (EntityPlayerMP player : te.getWorld().getPlayers(EntityPlayerMP.class, Predicates.alwaysTrue())) {
			if (ws.getPlayerChunkMap().isPlayerWatchingChunk(player, c.x, c.z)) {
				player.connection.sendPacket(packet);
			}
		}
	}
	
}
