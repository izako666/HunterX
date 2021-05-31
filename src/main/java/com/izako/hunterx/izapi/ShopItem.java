package com.izako.hunterx.izapi;

import com.izako.hunterx.gui.SequencedString;

import net.minecraft.item.Item;

public class ShopItem {

	public String name;

	public Item item;

	public int availableAmount = -1;

	public SequencedString[] info;

	public ShopItem(String name, Item item, int amount) {

		this.name = name;
		this.item = item;
		this.availableAmount = amount;
	}

	public ShopItem(String name, SequencedString[] info) {

		this.name = name;
		this.info = info;
	}
	
}
