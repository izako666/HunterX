package com.izako.wypi.json.models.item;

import com.izako.wypi.json.models.JSONModelItem;

public class JSONModelSpawnEgg extends JSONModelItem
{
	public JSONModelSpawnEgg(String itemName)
	{
		super(itemName, "spawn_egg");
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
