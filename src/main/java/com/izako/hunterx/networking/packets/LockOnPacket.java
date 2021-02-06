package com.izako.hunterx.networking.packets;

import java.util.function.Supplier;

import com.izako.hunterx.abilities.basic_hatsus.LoyaltyCurseAbility;
import com.izako.hunterx.abilities.hatsus.leorio.LockOnAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.goals.wolfgoals.FollowOwnerGoal;
import com.izako.hunterx.entities.goals.wolfgoals.MeleeAttackGoal;
import com.izako.hunterx.entities.goals.wolfgoals.OwnerHurtByTargetGoal;
import com.izako.hunterx.entities.goals.wolfgoals.OwnerHurtTargetGoal;
import com.izako.hunterx.izapi.ability.Ability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class LockOnPacket {


	public LockOnPacket() {
	}

	int slot;
	int lockon;
	public LockOnPacket(int slot, int lockon) {

		this.slot = slot;
		this.lockon = lockon;
	}

	public void encode(PacketBuffer buf) {

		buf.writeInt(slot);
		buf.writeInt(lockon);
	}

	public static LockOnPacket decode(PacketBuffer buf) {
		LockOnPacket msg = new LockOnPacket();
		msg.slot = buf.readInt();
		msg.lockon = buf.readInt();
		return msg;
	}

	public static void handle(LockOnPacket msg, final Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				
				PlayerEntity p = ctx.get().getSender();
				IAbilityData data = AbilityDataCapability.get(p);
				
				Entity e = p.world.getEntityByID(msg.lockon);
				Ability abl = data.getAbilityInSlot(msg.slot);
				
				if(abl instanceof LockOnAbility) {
					((LockOnAbility)abl).lockOn = (LivingEntity) e;
				}
				if(abl instanceof LoyaltyCurseAbility) {
					((LoyaltyCurseAbility) abl).animal = (AnimalEntity) e;
					((LoyaltyCurseAbility) abl).followGoal = new FollowOwnerGoal(((LoyaltyCurseAbility) abl).animal, 1.0D, 10.0F, 2.0F, false,p);
					((LoyaltyCurseAbility) abl).ownerHurtGoal = new OwnerHurtTargetGoal(((LoyaltyCurseAbility) abl).animal, p);
					((LoyaltyCurseAbility) abl).ownerHurtByGoal = new OwnerHurtByTargetGoal(((LoyaltyCurseAbility) abl).animal, p);
					((LoyaltyCurseAbility) abl).meleeGoal = new MeleeAttackGoal(((LoyaltyCurseAbility) abl).animal, 1.0D, true, p);
					((LoyaltyCurseAbility) abl).animal.goalSelector.addGoal(1, ((LoyaltyCurseAbility) abl).followGoal);
					((LoyaltyCurseAbility) abl).animal.goalSelector.addGoal(0, ((LoyaltyCurseAbility) abl).ownerHurtByGoal);
					((LoyaltyCurseAbility) abl).animal.goalSelector.addGoal(0, ((LoyaltyCurseAbility) abl).ownerHurtGoal);
					((LoyaltyCurseAbility) abl).animal.goalSelector.addGoal(4, ((LoyaltyCurseAbility) abl).meleeGoal);

				}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
