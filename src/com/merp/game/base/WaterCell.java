package com.merp.game.base;

import com.merp.game.utils.Type;
/**
 * 
 * Fill empty space of battle ground
 * 
 *
 */
public class WaterCell extends Cell{
	
	public WaterCell(final Location location) {
		super( Type.W, location);
	}

}