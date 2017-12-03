package com.elytradev.iridescent.module.spiritgraves;

import com.elytradev.concrete.network.Message;
import com.elytradev.concrete.network.NetworkContext;
import com.elytradev.concrete.network.annotation.field.MarshalledAs;
import com.elytradev.concrete.network.annotation.type.ReceivedOn;
import com.elytradev.iridescent.Iridescent;
import com.elytradev.iridescent.module.spiritgraves.client.ParticleSmokeSeekEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ReceivedOn(Side.CLIENT)
public class GraveDispelMessage extends Message {

	@MarshalledAs("i32")
	private int responsibleEntityId;
	@MarshalledAs("i32")
	private int graveEntityId;
	
	public GraveDispelMessage(NetworkContext ctx) {
		super(ctx);
	}
	
	public GraveDispelMessage(Entity responsible, Entity grave) {
		super(Iridescent.inst.network);
		this.responsibleEntityId = responsible.getEntityId();
		this.graveEntityId = grave.getEntityId();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected void handle(EntityPlayer sender) {
		Entity responsible = Minecraft.getMinecraft().world.getEntityByID(responsibleEntityId);
		Entity grave = Minecraft.getMinecraft().world.getEntityByID(graveEntityId);
		if (responsible == null || grave == null) return;
		float r = 0.1f;
		for (int i = 0; i < 480; i++) {
			float x = (float)(grave.world.rand.nextGaussian());
			float y = (float)(grave.world.rand.nextGaussian());
			float z = (float)(grave.world.rand.nextGaussian());

			ParticleSmokeSeekEntity p = new ParticleSmokeSeekEntity(responsible, grave.world, grave.posX+(r*x), grave.posY+(r*y), grave.posZ+(r*z), 0, 0, 0, 1f);
			p.setRBGColorF(0, 0, 0);
			p.setVelocity(x/8, y/8, z/8);
			Minecraft.getMinecraft().effectRenderer.addEffect(p);
		}
	}

}
