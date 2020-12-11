package com.izako.hunterx.gui;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.gui.ListSlider.Entry;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.systems.RenderSystem;

public class AbilitiesListSlider extends VerticalSlider {


	private onActivateEntry onActivate = (e,s) -> {};
	private onInitialClickEntry onInitClick = (e,s) -> {};
	List<Entry> entries = new ArrayList<>();
	public Entry selectedEntry = Entry.EMPTY;
	public AbilitiesListSlider(int x, int y, int width, int height, double min, double max, List<Entry> entries) {
		super(x, y, width, height, min, max);
		this.entries = entries;
		this.inSliderLength = this.height / this.entries.size();
		for(int i = 0; i < this.entries.size(); i++) {
			Entry entry = this.entries.get(i);
			entry.posX = this.x + 15;
			entry.posY = this.y + (45 * i);
		}
	}
	
	public void  setOnActivateEntry(onActivateEntry activate) {
		this.onActivate = activate;
	}

	public void  setOnInitClickEntry(onInitialClickEntry click) {
		this.onInitClick = click;
	}
	@Override
	public void renderButton(int mX, int mY, float idfk) {
		
		super.renderButton(mX, mY, idfk);
		for(int i = 0; i < this.entries.size(); i++) {
			RenderSystem.enableBlend();
			Entry entry = this.entries.get(i);
			Ability abl = getAbility(entry);
			if(entry.posY >= this.y && entry.posY <= this.y + this.height) {
				if(entry == this.selectedEntry) {
					WyHelper.drawIcon(abl.props.tex, entry.posX + 4, entry.posY, 32, 32);
				} else {
					WyHelper.drawIcon(abl.props.tex, entry.posX, entry.posY, 32, 32);
				}
			} else if(entry.posY < this.y && entry.posY > this.y - 32) {
				int remainingPixels = this.y - entry.posY;
				if(entry == this.selectedEntry) {
					Helper.drawAbilityIMG(abl.props.tex, entry.posX + 4, entry.posY + remainingPixels, 32, 32 - remainingPixels, 0f, (remainingPixels) / 32.0f,1f,1f);

				} else {
					Helper.drawAbilityIMG(abl.props.tex, entry.posX, entry.posY + remainingPixels, 32, 32 - remainingPixels, 0f, ( remainingPixels) / 32.0f,1f,1f);
					
				}
			} else if(entry.posY > this.y + this.height && entry.posY < this.y + this.height + 32) {
				int remainingPixels = 32 -( entry.posY - (this.y + this.height));
				if(entry == this.selectedEntry) {
					Helper.drawAbilityIMG(abl.props.tex, entry.posX + 4, entry.posY, 32, remainingPixels, 0f,0f, 1.0f, (remainingPixels) / 32.0f);

				} else {
					Helper.drawAbilityIMG(abl.props.tex, entry.posX, entry.posY, 32, remainingPixels, 0f,0f,1.0f, (remainingPixels) / 32.0f);

				}
			}
			
			RenderSystem.disableBlend();
			
		}
	}

	@Override
	public boolean mouseClicked(double mX, double mY, int p_mouseClicked_5_) {
		for(int i = 0; i < this.entries.size(); i++) {
			Entry entry = this.entries.get(i);
			
			if(entry.posY >= this.y && entry.posY <= this.y + this.height&& this.isInRange(entry.posX, entry.posX + 51, mX) && this.isInRange(entry.posY, entry.posY + 32, mY)) {
			
				if(entry == selectedEntry) {
					this.onActivateEntry(entry);
				}else {
					
				this.selectedEntry = entry;
				this.onClickEntry(entry);

				}
			
			} else if(entry.posY < this.y && entry.posY > this.y - 32 && this.isInRange(entry.posX, entry.posX + 51, mX)) {
				int remainingPixels = 32 - (this.y - entry.posY);
				if(this.isInRange(this.y, this.y + remainingPixels, mY)) {
					if(entry == selectedEntry) {
						this.onActivateEntry(entry);
					}else {
						
					this.selectedEntry = entry;
					this.onClickEntry(entry);

					}
				}
			} else if(entry.posY > this.y + this.height && entry.posY < this.y + this.height + 32 && this.isInRange(entry.posX, entry.posX + 51, mX)) {
				int remainingPixels =  entry.posY - (this.y + this.height);
				int endPos = this.y + this.height;
				if(this.isInRange(endPos + remainingPixels, endPos + 32, mY)) {
					if(entry == selectedEntry) {
						this.onActivateEntry(entry);
					}else {
						
					this.selectedEntry = entry;
					this.onClickEntry(entry);
					}
				}

			}
		}

		return true;
	}

	
	private List<Ability> getAbilities() {
		List<Ability> ABILITIES = new ArrayList<>();
		for(int i = 0; i < this.entries.size(); i++) {
			
			Entry entry = this.entries.get(i);
			
			ABILITIES.add(ModAbilities.getAbilityOfId(entry.id));
		}
		return ABILITIES;
	}
	
	
	private Ability getAbility(ListSlider.Entry entry) {
		return ModAbilities.getAbilityOfId(entry.id);
	}
	
	@Override
	protected void onDrag(double mX, double mY, double p_onDrag_5_, double p_onDrag_7_) {
		super.onDrag(mX, mY, p_onDrag_5_, p_onDrag_7_);
		for(int i = 0; i < this.entries.size(); i++) {
			Entry entry = this.entries.get(i);
			double originIndex = Helper.fromRangeToRange(this.minValue, this.maxValue, 0, this.entries.size() - 1, (int)this.value);
		
		
			double difference = i - originIndex;

			entry.posY = (int) (this.y + (45 * difference));
		}
	}

	public boolean isInRange(double min,double max,double val) {
		if(val <= max && val >= min) {
			return true;
		}
		return false;
	}
	public void onActivateEntry(Entry entry) {

		this.onActivate.onActivate(entry, this);
	}
	public void onClickEntry(Entry entry) {

		this.onInitClick.onInitClick(entry, this);
	}


	interface onActivateEntry {
		
		void onActivate(Entry entry,AbilitiesListSlider slider);
	}

	interface onInitialClickEntry {
		
		void onInitClick(Entry entry,AbilitiesListSlider slider);
	}

}
