package com.merp.game.base;

import com.merp.game.ships.Ship;
import com.merp.game.utils.ResultState;
import com.merp.game.utils.Type;
/**
 * 
 * Fill empty space of battle ground
 * 
 *
 */
public class WaterCell implements ICell{
	private final Location location;
	private final Type cellType;
	private Ship ship = null;
	
	public WaterCell(final Location location) {
		this.cellType = Type.W;
		this.location = location;
	}
	@Override
	public Location getCellPostion() {
		return this.location;
	}
	@Override
	public Type getCellType() {
		return this.cellType;
	}

	
	@Override
	public String getCellLocation() {
		// TODO Auto-generated method stub
		return Location.getLocationString(location);
	}

	@Override
	public ResultState hitCell() {
		// TODO Auto-generated method stub
		return ResultState.NO_HIT;
	}
	@Override
	public void setShip(Ship ship) {
		// TODO Auto-generated method stub
		
	}

	

}