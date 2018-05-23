package com.merp.game.ships;

import java.util.HashMap;
import java.util.Map;

import com.merp.game.base.BattleGround;
import com.merp.game.base.CellFactory;
import com.merp.game.base.ICell;
import com.merp.game.base.Location;
import com.merp.game.exception.ShipOverLappingException;
import com.merp.game.utils.ResultState;
import com.merp.game.utils.Type;

public class Ship implements IShip {
	private final Type shipType;
	private final Location startLocation;
	private final int occupiedRow;
	private final int occupiedCol;
	private final Map<String, ICell> cellMap;
	
	public Ship(final Type type, final int row, final int col, final Location startLocation) {
		this.shipType = type;
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
	/**
	 * Deploying ship is battle field  
	 * @param battleGround
	 * @throws IndexOutOfBoundsException
	 * @throws ShipOverLappingException
	 */
	public void deployeeShip(BattleGround battleGround) throws IndexOutOfBoundsException, ShipOverLappingException {
		for (int i = 0; i < this.getOccupiedRow(); i++) {
			for (int j = 0; j < this.getOccupiedCol(); j++) {
				int effectiveX = this.getStartLocation().getX() + i;
				int effectiveY = this.getStartLocation().getY() + j;
				if (battleGround.isOutOfBoundry(effectiveX, effectiveY)) {
					throw new IndexOutOfBoundsException("Cell location is out of battle field boundry");
				} else if (!battleGround.isCellEmpty(effectiveX, effectiveY)) {
					throw new ShipOverLappingException("Location " + Location.getLocationString(effectiveX, effectiveY)
							+ " already occupied by other ship");
				}
				ICell cell = CellFactory.getCell(this.getShipType(), new Location(effectiveX, effectiveY));
				cell.setShip(this);
				battleGround.setCell(effectiveX, effectiveY, cell);
				this.getCellMap().put(Location.getLocationString(effectiveX, effectiveY), cell);
			}
		}
	}

	/**
	 * Update cell list of ship if last attack destroyed the cell  
	 * @param cell
	 * @param result 
	 * @return : SHIP_DESTROYED if there is no cell remains in ship.
	 */
	public ResultState updateCell(ICell cell, ResultState result) {
		if (result == ResultState.CELL_DESTROYED) {
			String location = cell.getCellLocation();
			if (this.getCellMap().containsKey(location)) {
				this.getCellMap().remove(location);
			}
			if (this.getCellMap().size() == 0) {
				result = ResultState.SHIP_DESTROYED;
			}
		}
		return result;
	}

	

}