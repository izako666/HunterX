package com.izako.hunterx.izapi.ability;

public enum NenType {

	CONJURER, EMITTER, ENHANCER, MANIPULATOR,TRANSMUTER, SPECIALIST;
	
	public static NenType getTypeFromOrdinal(int o) {
		
		switch(o) {
		
		case 0:
		return NenType.CONJURER;
		case 1:
		return EMITTER;
		case 2: 
		return ENHANCER;
		case 3: 
		return MANIPULATOR;
		case 4: 
		return TRANSMUTER;
		case 5:
		return SPECIALIST;
		}
		return null;
	}
}
