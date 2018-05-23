package com.merp.game.ships;

import com.merp.game.base.Location;
import com.merp.game.utils.Type;

public class ShipFactory {
	public static IShip getShip( Type shipCellType, final int row, final int col, final Location startLocation) {
		return new Ship(shipCellType, row, col, startLocation);
	}
}
