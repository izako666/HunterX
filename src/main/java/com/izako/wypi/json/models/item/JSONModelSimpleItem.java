package com.izako.wypi.json.models.item;

import com.izako.wypi.json.models.JSONModelItem;

public class JSONModelSimpleItem extends JSONModelItem
{
	public JSONModelSimpleItem(String itemName)
	{
		super(itemName, "simple_item");
	}
	
	public JSONModelSimpleItem(String itemName, String parentItemName)
	{
		super(itemName, "simple_item", parentItemName);
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
