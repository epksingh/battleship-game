package com.merp.game.base;

import com.merp.game.ships.Ship;
import com.merp.game.utils.ResultState;
import com.merp.game.utils.Type;

public interface ICell {
	public Location getCellPostion();
	public Type getCellType();
	public String getCellLocation();
	public ResultState hitCell();
	public void setShip(Ship ship);
	
}