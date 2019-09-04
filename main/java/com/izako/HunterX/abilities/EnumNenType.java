package com.izako.HunterX.abilities;

public enum EnumNenType {

	EHANCEMENT, 
	CONJURATION,
	EMMISSION,
	MANIPULATION,
	TRANSMUTATION,
	SPECIALIZATION;
	public int getIndex(EnumNenType type) {
		if(type == this.CONJURATION) {
			return 1;
		} else if(type == this.EHANCEMENT) {
			return 2;
		} else if(type == this.EMMISSION) {
			return 3;
		} else if(type == this.MANIPULATION) {
			return 4;
		} else if(type == this.SPECIALIZATION) {
			return 5;
		} else if(type == this.TRANSMUTATION) {
			return 6;
		}
		return -1;
	} 
}
