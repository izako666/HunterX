package com.izako.wypi.data.ability;

import java.util.List;
import java.util.stream.Collectors;

import com.izako.wypi.APIConfig;
import com.izako.wypi.APIConfig.AbilityCategory;
import com.izako.wypi.WyHelper;
import com.izako.wypi.abilities.Ability;
import com.izako.wypi.abilities.ChargeableAbility;
import com.izako.wypi.abilities.ContinuousAbility;
import com.izako.wypi.abilities.PassiveAbility;
import com.izako.wypi.debug.WyDebug;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AbilityDataCapability
{
	@CapabilityInject(IAbilityData.class)
	public static final Capability<IAbilityData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
		{
			@Override
			public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				try
				{
					ListNBT unlockedAbilities = new ListNBT();
					for (int i = 0; i < instance.getUnlockedAbilities(AbilityCategory.ALL).size(); i++)
					{
						Ability ability = instance.getUnlockedAbilities(AbilityCategory.ALL).get(i);
						String name = WyHelper.getResourceName(ability.getName());
						CompoundNBT nbtAbility = new CompoundNBT();
						nbtAbility.putString("name", name);
						unlockedAbilities.add(nbtAbility);
					}
					props.put("unlocked_abilities", unlockedAbilities);

					ListNBT equippedAbilities = new ListNBT();
					for (int i = 0; i < instance.getEquippedAbilities().length; i++)
					{
						Ability ability = instance.getEquippedAbilities()[i];
						if(ability != null)
						{
							String name = WyHelper.getResourceName(ability.getName());
							CompoundNBT nbtAbility = new CompoundNBT();
							nbtAbility.putString("name", name);
							nbtAbility.putInt("pos", i);
							nbtAbility.putString("state", ability.getState().toString());
							nbtAbility.putDouble("cooldown", ability.getCooldown());
							nbtAbility.putDouble("maxCooldown", ability.getMaxCooldown());
							if(ability instanceof ContinuousAbility)
								nbtAbility.putDouble("continueTimer", ((ContinuousAbility)ability).getContinueTime());
							if(ability instanceof ChargeableAbility)
								nbtAbility.putDouble("chargeTimer", ((ChargeableAbility)ability).getChargeTime());
							equippedAbilities.add(nbtAbility);
						}
						else
						{
							CompoundNBT nbtAbility = new CompoundNBT();
							nbtAbility.putInt("pos", i);
							equippedAbilities.add(nbtAbility);
						}
					}
					props.put("equipped_abilities", equippedAbilities);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				return props;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				try
				{
					instance.clearEquippedAbilities(AbilityCategory.ALL);
					instance.clearUnlockedAbilities(AbilityCategory.ALL);

					ListNBT unlockedAbilities = props.getList("unlocked_abilities", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < unlockedAbilities.size(); i++)
					{
						CompoundNBT nbtAbility = unlockedAbilities.getCompound(i);
						Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, nbtAbility.getString("name")));
						try
						{
							instance.addUnlockedAbility(ability.create());
						}
						catch(Exception e)
						{
							WyDebug.debug("Unregistered ability: " + nbtAbility.getString("name"));
						}
					}
	
					ListNBT equippedAbilities = props.getList("equipped_abilities", Constants.NBT.TAG_COMPOUND);
					List<Ability> activeAbilitiesUnlocked = instance.getUnlockedAbilities(AbilityCategory.ALL).parallelStream().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
					for (int i = 0; i < equippedAbilities.size(); i++)
					{
						CompoundNBT nbtAbility = equippedAbilities.getCompound(i);
						Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, nbtAbility.getString("name")));
						if (ability != null)
						{
							for(Ability abl : activeAbilitiesUnlocked)
							{
								if(abl.equals(ability))
								{
									Ability.State state = Ability.State.valueOf(nbtAbility.getString("state"));
									int cooldown = (int) (nbtAbility.getDouble("cooldown") / 20);
									int maxCooldown = (int) (nbtAbility.getDouble("maxCooldown") / 20);
									int pos = nbtAbility.getInt("pos");
									if (state == null)
										state = Ability.State.STANDBY;
									abl.setState(state);
									abl.setMaxCooldown(maxCooldown);
									abl.setCooldown(cooldown);
									if(ability instanceof ContinuousAbility)
									{
										int continueTime = (int) (nbtAbility.getDouble("continueTime") / 20);
										((ContinuousAbility)ability).setContinueTime(continueTime);
									}
									if(ability instanceof ChargeableAbility)
									{
										int chargeTime = (int) (nbtAbility.getDouble("chargeTime") / 20);
										((ChargeableAbility)ability).setChargeTime(chargeTime);
									}
									
									instance.setEquippedAbility(pos, abl);
								}
							}
						}
						else if(ability == null)
						{
							int pos = nbtAbility.getInt("pos");
							instance.setEquippedAbility(pos, null);
						}
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}

		}, AbilityDataBase::new);
	}

	public static IAbilityData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
	}

	public static LazyOptional<IAbilityData> getLazy(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null);
	}
}
