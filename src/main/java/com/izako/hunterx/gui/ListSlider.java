package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.Main;
import com.izako.hunterx.gui.AbilitiesListSlider.onActivateEntry;
import com.izako.hunterx.gui.AbilitiesListSlider.onInitialClickEntry;
import com.izako.hunterx.gui.ListSlider.Entry;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.quest.Quest;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class ListSlider extends VerticalSlider {

	public static final ResourceLocation LIST_SLIDER_ICONS = new ResourceLocation(Main.MODID, "textures/gui/list_slider.png");

	private onActivateEntry onActivate = (e,s) -> {};
	private onInitialClickEntry onInitClick = (e,s) -> {};

	List<Entry> entries = new ArrayList<>();
	public Entry selectedEntry = Entry.EMPTY;
	public ListSlider(int x, int y, int width, int height, double min, double max, List<Entry> entries) {
		super(x, y, width, height, min, max);
		this.entries = entries;
		if(this.entries.size() > 0) {
		this.inSliderLength = this.height / this.entries.size();
		} 
		for(int i = 0; i < this.entries.size(); i++) {
			Entry entry = this.entries.get(i);
			entry.posX = this.x + 15;
			entry.posY = this.y + (30 * i);
		}
	}


	public void  setOnInitClickEntry(onInitialClickEntry click) {
		this.onInitClick = click;
	}


	public void  setOnActivateEntry(onActivateEntry activate) {
		this.onActivate = activate;
	}


	 public static class Entry {
		
		public String id;
		public String name = "";
		public String desc = "";
		public int posX = 0;
		public int posY = 0;
		public int posZ = 1;
		public static final Entry EMPTY = new Entry("","","");
		public ResourceLocation extraIcon = null;
		public Entry(String id,String name, String description) {
			this(id,name, description, null);
		}

		public Entry(String id,String name, String description,ResourceLocation extraIcon) {
			this.id = id;
			this.name = name;
			this.desc = description;
			this.extraIcon = extraIcon;
		}
		
		public Entry(Quest q) {
			this(q.getId(), q.getName(),q.getDescription());
		}
		public Entry(Ability abl) {
			this(abl.getId(), abl.getName(), abl.getDesc());
		}
		
		public static List<Entry> fromQuests(List<Quest> quests) {
			List<Entry> entries = new ArrayList<>();
			for(int i = 0; i< quests.size(); i++) {
				Quest q= quests.get(i);
				Entry entry = new Entry(q.getId(),q.getName(),q.getDescription());
				entries.add(entry);
			}
			
			return entries;
		}
		
		
		public static List<Entry> fromAbilities(List<Ability> abilities) {
			List<Entry> entries = new ArrayList<>();
			for(int i = 0; i< abilities.size(); i++) {
				Ability a= abilities.get(i);
				Entry entry = new Entry(a.getId(),a.getName(),a.getDesc());
				entries.add(entry);
			}
			
			return entries;
		}

	}
	 
	
	@Override
	public void renderButton(int mX, int mY, float idfk) {
		super.renderButton(mX, mY, idfk);
		
		for(int i = 0; i < this.entries.size(); i++) {
			RenderSystem.enableBlend();
			Entry entry = this.entries.get(i);
			if(entry.posY >= this.y && entry.posY <= this.y + this.height) {
				if(entry == this.selectedEntry) {
					Helper.drawIMG(LIST_SLIDER_ICONS, entry.posX + 4, entry.posY, 0, 14, 54, 13, 2, 54, 13);
				} else {
				Helper.drawIMG(LIST_SLIDER_ICONS, entry.posX, entry.posY, 0, 0, 51, 13, 2, 51, 13);
				}
			} else if(entry.posY < this.y && entry.posY > this.y - 13) {
				int remainingPixels = this.y - entry.posY;
				if(entry == this.selectedEntry) {
					Helper.drawIMG(LIST_SLIDER_ICONS, entry.posX + 4, entry.posY + remainingPixels, 0, 14 + remainingPixels, 51,13 - remainingPixels, 2, 51,13 -  remainingPixels);

				} else {
				Helper.drawIMG(LIST_SLIDER_ICONS, entry.posX, entry.posY + remainingPixels, 0, remainingPixels, 51,13 - remainingPixels, 2, 51,13 -  remainingPixels);
				}
			} else if(entry.posY > this.y + this.height && entry.posY < this.y + this.height + 13) {
				int remainingPixels = 13 -( entry.posY - (this.y + this.height));
				if(entry == this.selectedEntry) {
					Helper.drawIMG(LIST_SLIDER_ICONS, entry.posX + 4, entry.posY, 0, 14, 51, remainingPixels, 2, 51,remainingPixels);

				} else {
				Helper.drawIMG(LIST_SLIDER_ICONS, entry.posX, entry.posY, 0, 0, 51, remainingPixels, 2, 51,remainingPixels);

				}
			}
			
			RenderSystem.disableBlend();
			
			if(this.isInRange(this.y - 2, this.y + this.height + 4, entry.posY)) {
			RenderSystem.pushMatrix();
			if(entry == this.selectedEntry) {
				   RenderSystem.translated(entry.posX + 8, entry.posY + 4, 3);

			} else {
			   RenderSystem.translated(entry.posX + 2, entry.posY + 4, 3);
			}
			   RenderSystem.scaled(0.4d, 0.4d, 1d);
				this.drawString(Minecraft.getInstance().fontRenderer, entry.name, 0, 0, Color.red.getRGB());
				   RenderSystem.scaled(1d, 1d, 1d);
 
				   RenderSystem.popMatrix();

			}
		}
		
	}

	@Override
	public boolean mouseClicked(double mX, double mY, int p_mouseClicked_5_) {
		
		for(int i = 0; i < this.entries.size(); i++) {
			Entry entry = this.entries.get(i);
			
			if(entry.posY >= this.y && entry.posY <= this.y + this.height&& this.isInRange(entry.posX, entry.posX + 51, mX) && this.isInRange(entry.posY, entry.posY + 13, mY)) {
			
				if(entry == selectedEntry) {
					this.onActivateEntry(entry);
				}else {
					
				this.selectedEntry = entry;
				this.onClickEntry(entry);
				}
			
			} else if(entry.posY < this.y && entry.posY > this.y - 13 && this.isInRange(entry.posX, entry.posX + 51, mX)) {
				int remainingPixels = 13 - (this.y - entry.posY);
				if(this.isInRange(this.y, this.y + remainingPixels, mY)) {
					if(entry == selectedEntry) {
						this.onActivateEntry(entry);
					}else {
						
					this.selectedEntry = entry;
					this.onClickEntry(entry);

					}
				}
			} else if(entry.posY > this.y + this.height && entry.posY < this.y + this.height + 13 && this.isInRange(entry.posX, entry.posX + 51, mX)) {
				int remainingPixels =  entry.posY - (this.y + this.height);
				int endPos = this.y + this.height;
				if(this.isInRange(endPos + remainingPixels, endPos + 13, mY)) {
					if(entry == selectedEntry) {
						this.onActivateEntry(entry);
					}else {
						
					this.selectedEntry = entry;
					this.onClickEntry(entry);

					}
				}

			}
		}
		return super.mouseClicked(mX, mY, p_mouseClicked_5_);
		
		
	}

	protected void onActivateEntry(Entry entry) {

		this.onActivate.onActivate(entry, this);
	}

	@Override
	protected void onDrag(double mX, double mY, double p_onDrag_5_, double p_onDrag_7_) {
		super.onDrag(mX, mY, p_onDrag_5_, p_onDrag_7_);
		for(int i = 0; i < this.entries.size(); i++) {
			Entry entry = this.entries.get(i);
			double originIndex = Helper.fromRangeToRange(this.minValue, this.maxValue, 0, this.entries.size() - 1, (int)this.value);
		
		
			double difference = i - originIndex;

			entry.posY = (int) (this.y + (30 * difference));
		}
	}

	public boolean isInRange(double min,double max,double val) {
		if(val <= max && val >= min) {
			return true;
		}
		return false;
	}
	
	public void onClickEntry(Entry entry) {

		this.selectedEntry = entry;
		this.onInitClick.onInitClick(entry, this);
	}


	interface onActivateEntry {
		
		void onActivate(Entry entry,ListSlider slider);
	}

	interface onInitialClickEntry {
		
		void onInitClick(Entry entry,ListSlider slider);
	}
}
