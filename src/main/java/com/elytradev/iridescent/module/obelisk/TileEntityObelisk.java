package com.elytradev.iridescent.module.obelisk;

import com.elytradev.iridescent.Iridescent;
import com.elytradev.iridescent.module.obelisk.client.ModuleObeliskClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityObelisk extends TileEntity {

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
	}
	
	public void attune(EntityPlayer p) {
		if (p == null) return;
		p.playSound(ModuleObelisk.ATTUNE, 1f, 1.0f);
		
		if (!p.world.isRemote) {
			p.setSpawnPoint(p.getPosition(), true);
		} else if (Iridescent.isModuleLoaded(ModuleObeliskClient.class)) {
			Iridescent.getModule(ModuleObeliskClient.class).playAttuneEffect(this);
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(5);
	}
	
}
