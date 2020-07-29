package com.izako.wypi.json.models.item;

import com.izako.wypi.json.models.JSONPredicateObject;

public class JSONModelRod extends JSONModelPredicates
{
	public JSONModelRod(String itemName)
	{
		super(itemName, "rod");
	}

	public JSONModelRod(String itemName, JSONPredicateObject... predicates)
	{
		super(itemName, "rod", predicates);
	}
	
	@Override
	public String[] getModel()
	{		
		return super.getModel();
	}
}
