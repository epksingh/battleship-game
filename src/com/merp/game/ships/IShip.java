package com.merp.game.ships;

import com.merp.game.base.BattleGround;
import com.merp.game.base.ICell;
import com.merp.game.exception.ShipOverLappingException;
import com.merp.game.utils.ResultState;

public interface IShip {
	public void deployeeShip(BattleGround battleGround) throws IndexOutOfBoundsException, ShipOverLappingException;
	public ResultState updateCell(ICell cell, ResultState result);
}
