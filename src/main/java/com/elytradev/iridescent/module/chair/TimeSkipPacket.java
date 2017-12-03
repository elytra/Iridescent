package com.elytradev.iridescent.module.chair;

import com.elytradev.concrete.network.Message;
import com.elytradev.concrete.network.NetworkContext;
import com.elytradev.concrete.network.annotation.type.ReceivedOn;
import com.elytradev.iridescent.Iridescent;
import com.elytradev.iridescent.module.chair.client.ModuleChairClient;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;

@ReceivedOn(Side.CLIENT)
public class TimeSkipPacket extends Message {

	private boolean state;
	
	public TimeSkipPacket(NetworkContext ctx) {
		super(ctx);
	}
	
	public TimeSkipPacket(boolean state) {
		super(Iridescent.inst.network);
		this.state = state;
	}
	
	@Override
	protected void handle(EntityPlayer sender) {
		ModuleChairClient.timeskipping = state;
	}

}
