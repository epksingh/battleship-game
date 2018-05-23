package com.merp.game.utils;

public enum Type {
	W(0),
	P(1),
	Q(2);
	
	public int getMaxHit() {
		return maxHit;
	}
	int maxHit = 0;
	private Type(final int maxHit) {
		this.maxHit = maxHit;
	}
	
	
}
