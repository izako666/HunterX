package com.izako.wypi.quests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.izako.wypi.WyHelper;
import com.izako.wypi.data.quest.IQuestData;
import com.izako.wypi.quests.objectives.Objective;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Quest extends ForgeRegistryEntry<Quest>
{
	private String title;
	private String description;
	
	private List<Objective> objectives = new ArrayList<Objective>();
	private List<Quest> requirements = new ArrayList<Quest>();

	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IAfterStarting onStartEvent = (player) -> {};
	protected IAfterCompleting onCompleteEvent = (player) -> {};
	protected IShouldRestart shouldRestartEvent = (player) -> { return false; };

	public Quest(String id, String title)
	{
		this.title = title;
	}
	
	/*
	 *  Methods
	 */
	
	@Nullable
	public Quest create()
	{
		try
		{
			return this.getClass().getConstructor().newInstance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean equals(Object quest)
	{
		if(!(quest instanceof Quest))
			return false;
		
		return this.getId().equalsIgnoreCase(((Quest) quest).getId());
	}
	
	/*
	 *  Setters and Getters
	 */
	
	public boolean checkRestart(PlayerEntity player)
	{
		return this.shouldRestartEvent.shouldRestart(player);
	}
	
	public void triggerCompleteEvent(PlayerEntity player)
	{
		this.onCompleteEvent.onComplete(player);
	}
	
	public void triggerStartEvent(PlayerEntity player)
	{
		this.onStartEvent.onStart(player);
	}
	
	public void addRequirements(Quest... requirements)
	{
		for(Quest req : requirements)
			this.addRequirement(req);

	}
	
	public void addRequirement(Quest req)
	{
		if(!this.requirements.contains(req))
			this.requirements.add(req);
	}
	
	public void addObjectives(Objective... objectives)
	{
		for(Objective obj : objectives)
			this.addObjective(obj);
	}
	
	public void addObjective(Objective objective)
	{
		if(!this.objectives.contains(objective))
			this.objectives.add(objective);
	}
	
	public List<Objective> getObjectives()
	{
		return this.objectives;
	}
	
	public boolean isComplete()
	{
		return this.objectives.stream().allMatch(objective -> objective.isComplete());
	}
	
	public double getProgress()
	{
		int maxProgress = this.objectives.size();
		int completed = this.objectives.stream().filter(objective -> objective.isComplete()).collect(Collectors.toList()).size();

		double progress = completed / (double) maxProgress;
		
		return progress;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getId()
	{
		return WyHelper.getResourceName(this.title);
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public boolean isLocked(IQuestData props)
	{
		if(this.requirements.size() <= 0)
			return false;
		
		boolean isLocked = false;
		for(Quest quest : this.requirements)
		{
			if(!props.hasFinishedQuest(quest))
			{
				isLocked = true;
				break;
			}
		}

		return isLocked;
	}
	
	
	/*
	 *  Save / Load data
	 */
	
	public CompoundNBT save()
	{
		CompoundNBT nbt = new CompoundNBT();
		
		nbt.putString("id", this.getId());
		ListNBT objectivesData = new ListNBT();
		for(Objective obj : this.getObjectives())
		{
			objectivesData.add(obj.save());
		}
		nbt.put("objectives", objectivesData);
		
		return nbt;
	}
	
	public void load(CompoundNBT nbt)
	{
		ListNBT objectivesData = nbt.getList("objectives", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < objectivesData.size(); i++)
		{
			CompoundNBT questData = objectivesData.getCompound(i);
			this.getObjectives().get(i).load(questData);
		}
	}
	
	
	/*
	 *  Interfaces
	 */
	
	public interface IShouldRestart extends Serializable
	{
		boolean shouldRestart(PlayerEntity player);
	}
	
	public interface IAfterStarting extends Serializable
	{
		void onStart(PlayerEntity player);
	}
	
	public interface IAfterCompleting extends Serializable
	{
		void onComplete(PlayerEntity player);
	}
}
