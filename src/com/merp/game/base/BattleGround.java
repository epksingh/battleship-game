package com.merp.game.base;

import java.util.List;

import com.merp.game.exception.ShipOverLappingException;
import com.merp.game.ships.Ship;
import com.merp.game.utils.ResultState;
import com.merp.game.utils.Type;

public class BattleGround {
	private final int noOfRow;
	private final int noOfCol;
	private List<Ship> shipList;
	private ICell[][] battleCells;
	private int shipDestroyedCount;

	public BattleGround(int noOfRow, int noOfCol) {
		this.noOfRow = noOfRow;
		this.noOfCol = noOfCol;
		battleCells = new ICell[noOfRow][noOfCol];
		for (int i = 0; i < battleCells.length; i++) {
			for (int j = 0; j < battleCells[i].length; j++) {
				battleCells[i][j] = CellFactory.getCell(Type.W, new Location(i,j));
			}
		}
	}

	public boolean isOutOfBoundry(int xCord, int yCord) {
		return xCord < 0 || xCord >= noOfRow || yCord < 0 || yCord >= noOfCol;
	}

	public void setCell(int xCord, int yCord, ICell cell) {
		battleCells[xCord][yCord] = cell;
	}
	public boolean isCellEmpty(int xCord, int yCord) {
		return !(battleCells[xCord][yCord] != null && (battleCells[xCord][yCord].getCellType() == Type.P
				|| battleCells[xCord][yCord].getCellType() == Type.Q));
	}

	public void deployeeShip(List<Ship> shipList ) throws IndexOutOfBoundsException, ShipOverLappingException {
		this.shipList = shipList;
		for (Ship ship : shipList) {
			ship.deployeeShip(this);
		}
	}
	public boolean isAllShipDestroyed() {
		return this.shipList.size() == shipDestroyedCount;
		
	}

	private ICell getCells(Location targate) {
		return battleCells[targate.getX()][targate.getY()];
	}
	public ResultState hitTarget(Location targate){
		if (isOutOfBoundry(targate.getX(), targate.getY())) {
			return ResultState.NO_HIT;
		} 
		boolean isEmpty = isCellEmpty(targate.getX(), targate.getY());
		if(isEmpty) {
			return ResultState.NO_HIT;
		}
		ICell cell = this.getCells(targate);
		ResultState result = cell.hitCell();
		if(result == ResultState.SHIP_DESTROYED) {
			shipDestroyedCount++;
		}
		
		return result;
	}

	public void printBattleGround() {
		for (int i = 0; i < noOfRow; i++) {
			for (int j = 0; j < noOfCol; j++) {
				if (isCellEmpty(i, j)) {
					System.out.print("- ");
				} else {
					System.out.print(battleCells[i][j].getCellType().toString() +" ");
				}
			}
			System.out.println();
		}
	}

}