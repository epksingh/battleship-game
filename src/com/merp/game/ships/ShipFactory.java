package com.merp.game.ships;

import com.merp.game.base.Location;
import com.merp.game.utils.Type;

public class ShipFactory {
	public static Ship getShip( Type shipType, final int row, final int col, final Location startLocation) {
		Ship ship = null;
		switch(shipType) {
		case P:
			ship = new PTypeShip(row, col, startLocation);
			break;
		case Q:
			ship = new QTypeShip(row, col, startLocation);
			break;
		default:
			ship = null;
		}
		return ship;
	}
}
