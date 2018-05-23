package com.merp.game.ships;

import java.util.HashMap;
import java.util.Map;

import com.merp.game.base.ICell;
import com.merp.game.base.Location;
import com.merp.game.utils.Type;

public class PTypeShip extends Ship{
	private final Type shipType;
	private final Location startLocation;
	private final int occupiedRow;
	private final int occupiedCol;
	private final Map<String, ICell> cellMap;
	
	public PTypeShip(final int row, final int col, final Location startLocation) {
		this.shipType = Type.P;
		this.startLocation = startLocation;
		this.occupiedRow = row;
		this.occupiedCol = col;
		cellMap = new HashMap<>();
	}
	
	public Type getShipType() {
		// TODO Auto-generated method stub
		return this.shipType;

	}

	public Location getStartLocation() {
		return startLocation;
	}

	public int getOccupiedRow() {
		return occupiedRow;
	}

	public int getOccupiedCol() {
		return occupiedCol;
	}

	public Map<String, ICell> getCellMap() {
		return cellMap;
	}
	
	public boolean isShipDestroyed() {
		return cellMap.size() == 0;
	}
}