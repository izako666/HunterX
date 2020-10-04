package com.izako.hunterx.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.abilities.basics.EnAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.EnUpdatePacket;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnEntity extends Entity implements IEntityAdditionalSpawnData {



public	LivingEntity owner;
	public double renderScale  = 10;
	public List<LivingEntity> entities = new ArrayList<>();
	@SuppressWarnings("unchecked")
	public static EntityType<EnEntity> TYPE = (EntityType<EnEntity>) EntityType.Builder
	.<EnEntity>create(EnEntity::new, EntityClassification.MISC).setTrackingRange(128)
	.setShouldReceiveVelocityUpdates(true).size(1, 1).setUpdateInterval(1)
	.setCustomClientFactory(EnEntity::new).build("en_sphere").setRegistryName(Main.MODID, "en_sphere");
	public EnEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public EnEntity(EntityType<?> entityTypeIn, World worldIn, LivingEntity owner) {
		super(entityTypeIn, worldIn);
		this.owner = owner;
		
	}
	public EnEntity(FMLPlayMessages.SpawnEntity packet, World worldIn) {
		super(TYPE, worldIn);
	}
	@Override
	protected void registerData() {
		
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer) {
	     buffer.writeDouble(renderScale);
	     if(owner != null) {
	     buffer.writeInt(owner.getEntityId());
	     }
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		
		ClientHandler.handle(additionalData, this);
	}


	@Override
	public AxisAlignedBB getBoundingBox() {
		return super.getBoundingBox().grow(renderScale);
	}

	
	

	 @Override
		public void tick() {
		 if(this.owner == null) {
			 this.remove();
			 return;
		 }
		 if(!this.world.isRemote()) {
			 IAbilityData data = AbilityDataCapability.get(owner);
			 EnAbility abl = (EnAbility) data.getAbility(ModAbilities.EN_ABILITY);
			 if(owner instanceof PlayerEntity ) {
				  abl = (EnAbility) data.getSlotAbility(ModAbilities.EN_ABILITY);
			 }
			 
			 if(abl.isActive() && !abl.entity.isEntityEqual(this)) {
				 this.remove();
				 return;
			 } else if(!abl.isActive()) {
				 this.remove();
				 return;
			 }
		 }
		 
		 if(this.ticksExisted % 40 == 0 && this.owner != null && !this.world.isRemote()) {
		 BlockPos startPos = this.getPosition().add(-(renderScale / 2.0), -(renderScale / 2.0), -(renderScale / 2.0));
		 BlockPos endPos = this.getPosition().add((renderScale / 2.0), (renderScale / 2.0), (renderScale / 2.0));

		 List<LivingEntity> list =  this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(startPos,endPos)); 
	   	 list.removeIf((entity) -> {return !(entity instanceof MonsterEntity || entity instanceof PlayerEntity);});
		 list.remove(owner);
	   	 List<LivingEntity> extras = new ArrayList<>(list);
		 Iterator<LivingEntity> iterator = extras.iterator();
		 extras.removeIf((extra) -> {return this.entities.contains(extra);});
		
		 if(this.ticksExisted % 80 == 0) {
			 if(owner instanceof ServerPlayerEntity) {
			 ServerPlayerEntity p = (ServerPlayerEntity) owner;
			 PacketHandler.INSTANCE.sendTo(new EnUpdatePacket(list, owner,this), p.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		 }
		 }
		 for(int i = 0; i < extras.size(); i++) {
			 LivingEntity entity = extras.get(i);
			 if(!this.world.isRemote()) {
			 this.owner.sendMessage(new StringTextComponent(entity.getDisplayName().getString() + " has entered your en. he is at" +  entity.getPosition().toString()));
			 }
			 }
		 
		 for(int i = 0; i < list.size(); i++) {
			 LivingEntity entity = list.get(i);
			 
			 if(entity.getDistanceSq(this.owner) < 20) {
				 LivingEntity entity2 = null;
				 try {
				  entity2 = this.entities.get(this.entities.indexOf(entity));
				 } catch(Exception e) {
					 
				 }
				 if(entity2 != null && entity2.getDistanceSq(this.owner) >= 20) {
					 if(!this.world.isRemote()) {
						 this.owner.sendMessage(new StringTextComponent(entity.getDisplayName().getString() + " has gotten in 20 blocks range of you beware. he is at" +  entity.getPosition().toString()));
						 }

				 } else if(entity2 == null) {
					 if(!this.world.isRemote()) {
						 this.owner.sendMessage(new StringTextComponent(entity.getDisplayName().getString() + " has gotten in 20 blocks range of you beware. he is at" +  entity.getPosition().toString()));
						 }

				 }
			 }
		 }
		 this.entities = list;
		 } 
		// super.tick();
		}



	public static class ClientHandler {
		@OnlyIn(Dist.CLIENT)
		public static void handle(PacketBuffer buf,EnEntity e) {
			e.renderScale = buf.readDouble();
			try {
			e.owner = (LivingEntity) Minecraft.getInstance().world.getEntityByID(buf.readInt());
			} catch(Exception exception) {
				
			}
			}
	}
	
}
