package com.merp.game.ships;

import java.util.Map;

import com.merp.game.base.BattleGround;
import com.merp.game.base.CellFactory;
import com.merp.game.base.ICell;
import com.merp.game.base.Location;
import com.merp.game.exception.ShipOverLappingException;
import com.merp.game.utils.ResultState;
import com.merp.game.utils.Type;

public abstract class Ship {
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

	public abstract Type getShipType();

	public abstract Location getStartLocation();

	public abstract int getOccupiedRow();

	public abstract int getOccupiedCol();

	public abstract Map<String, ICell> getCellMap();
	
	public abstract boolean isShipDestroyed();
}