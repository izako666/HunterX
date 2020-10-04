package com.izako.hunterx.izapi.ability;

public enum NenType {

	UNKNOWN, CONJURER, EMITTER, ENHANCER, MANIPULATOR,TRANSMUTER, SPECIALIST;
	
	public static NenType getTypeFromOrdinal(int o) {
		
		switch(o) {
		
		case 0:
		return UNKNOWN;
		case 1:
		return CONJURER;
		case 2: 
		return EMITTER;
		case 3: 
		return ENHANCER;
		case 4: 
		return MANIPULATOR;
		case 5:
		return TRANSMUTER;
		case 6:
	    return SPECIALIST;
	    default:
	    return UNKNOWN;
		}
	}
}
