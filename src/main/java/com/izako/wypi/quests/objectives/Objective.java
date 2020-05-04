package com.izako.wypi.quests.objectives;

import java.util.ArrayList;
import java.util.List;

import com.izako.wypi.WyHelper;

import net.minecraft.nbt.CompoundNBT;

public abstract class Objective
{
	private String title;
	private String description;
	private boolean isHidden;
	private boolean isOptional;
	private double maxProgress = 1;
	private double progress;
	
	private List<Objective> requirements = new ArrayList<Objective>();

	public Objective(String title)
	{
		this.title = title;
	}
	
	
	
	/*
	 * 	Setters and Getters
	 */
	
	public void setProgress(double progress)
	{
		if(progress <= this.getMaxProgress())
			this.progress = progress;
		else
			this.progress = this.getMaxProgress();
	}
	
	public void alterProgress(double progress)
	{
		if(this.progress + progress <= this.getMaxProgress())
			this.progress += progress;
		else
			this.progress = this.getMaxProgress();
	}
	
	public double getProgress()
	{
		return this.progress;
	}
	
	public void setMaxProgress(double progress)
	{
		this.maxProgress = progress;
	}
	
	public double getMaxProgress()
	{
		return this.maxProgress;
	}
	
	public Objective addRequirements(Objective... objectives)
	{
		for(Objective obj : objectives)
			this.addRequirement(obj);
		
		return this;
	}
	
	public Objective addRequirement(Objective objective)
	{
		if(!this.requirements.contains(objective))
			this.requirements.add(objective);
		
		return this;
	}
	
	public Objective setDescription(String desc)
	{
		this.description = desc;
		return this;
	}

	public Objective markHidden()
	{
		this.isHidden = true;
		return this;
	}
	
	public String getId()
	{
		return WyHelper.getResourceName(this.title);
	}
	
	public boolean isHidden()
	{
		return this.isHidden && this.isLocked();
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public boolean isComplete()
	{
		return this.progress >= this.maxProgress;
	}
	
	public boolean isLocked()
	{
		if(this.requirements.size() <= 0)
			return false;
		
		if(this.requirements.stream().allMatch(objective -> objective.isComplete()))
			return false;
		
		return true;
	}
	
	
	
	/*
	 *  Save / Load data
	 */
	
	public CompoundNBT save()
	{
		CompoundNBT nbt = new CompoundNBT();

		nbt.putString("id", this.getId());
		nbt.putBoolean("isHidden", this.isHidden);
		nbt.putDouble("progress", this.progress);

		return nbt;
	}
	
	public void load(CompoundNBT nbt)
	{	
		this.isHidden = nbt.getBoolean("isHidden");
		this.progress = nbt.getDouble("progress");
	}
}
