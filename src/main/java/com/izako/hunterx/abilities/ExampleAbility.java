package com.izako.hunterx.abilities;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.izapi.ability.ChargeableAbility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class ExampleAbility extends ChargeableAbility {

	public ExampleAbility() {
		this.setChargingTimer(100);
		this.setCooldown(0);
		this.setInPassive(false);
		this.setPassiveTimer(100);
		ExampleAbility.setType(AbilityType.ONUSE);
	}
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "exampleability";
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Example Ability";
	}
	@Override
	public void renderDesc(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void use(PlayerEntity p) {
		// TODO Auto-generated method stub
		if(!p.world.isRemote()) {
		p.sendMessage(new StringTextComponent("works"));
		} else {
			p.sendMessage(new StringTextComponent("lasagna"));
		}
	}
	@Override
	public int getMaxCooldown() {
		// TODO Auto-generated method stub
		return 100;
	}
	@Override
	public int getMaxCharging() {
		// TODO Auto-generated method stub
		return 200;
	}
	@Override
	public int getMaxPassive() {
		// TODO Auto-generated method stub
		return 100;
	}
	@Override
	public ResourceLocation getTexture() {
		// TODO Auto-generated method stub
		return new ResourceLocation(Main.MODID, "textures/gui/button1.png");
	}
	@Override
	public void onStartCharging(PlayerEntity p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void duringCharging(PlayerEntity p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEndCharging(PlayerEntity p) {
		// TODO Auto-generated method stub
		
	}
	

}
