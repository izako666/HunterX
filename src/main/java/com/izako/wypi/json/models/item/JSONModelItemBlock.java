package com.izako.wypi.json.models.item;

import com.izako.wypi.json.models.JSONModelItem;

public class JSONModelItemBlock extends JSONModelItem
{

	public JSONModelItemBlock(String itemName)
	{
		super(itemName, "item_block");
	}

	@Override
	public String[] getModel()
	{
		return this.replaceMarkedElements();
	}

}
