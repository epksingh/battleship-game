package com.merp.game.users;

import java.util.List;

import com.merp.game.base.BattleGround;
import com.merp.game.base.Location;
import com.merp.game.exception.ShipOverLappingException;
import com.merp.game.ships.Ship;
import com.merp.game.utils.ResultState;

public class Player {
	private int playerId;
	private BattleGround battleGround;
	private Location[] targetLocation; // keep target location
	private int lastMissileFiredIndex = 0;

	public int getPlayerId() {
		return playerId;
	}

	public Player(final int playerId) {
		this.playerId = playerId;
	}

	public void createBattleGround(final int noOfRow, final int noOfCol) {
		battleGround = new BattleGround(noOfRow, noOfCol);
	}

	public void deployeeShips(List<Ship> shipList) throws IndexOutOfBoundsException, ShipOverLappingException {
		battleGround.deployeeShip(shipList);
	}

	public void setMissileTargate(final Location[] targateLocation) {
		this.targetLocation = targateLocation;
	}

	public BattleGround getBattleGround() {
		return battleGround;
	}

	public boolean isMissileAvailable() {
		return lastMissileFiredIndex < targetLocation.length;
	}

	private String getLastMissileLocation() {
		if (lastMissileFiredIndex >= 0) {
			return Location.getLocationString(targetLocation[lastMissileFiredIndex - 1]);
		}
		return "";
	}

	/**
	 * Player fire the missile on opponent and 
	 * print the result on the basis of result 
	 * returned form opponent.
	 * @param opponent
	 * @return
	 */
	public ResultState fireMissile(Player opponent) {
		ResultState result = ResultState.NO_MISSILE;
		if (this.isMissileAvailable()) {
			result = opponent.getBattleGround().hitTarget(this.targetLocation[lastMissileFiredIndex]);
			lastMissileFiredIndex++;
		}
		switch (result) {
		case NO_MISSILE:
			System.out.printf("Player-%d has no more missiles left to launch\n", this.getPlayerId());
			break;
		case NO_HIT:
			System.out.printf("Player-%d fires a missile with target %s which got miss\n", this.getPlayerId(),
					this.getLastMissileLocation());
			break;
		case PARTIALLY_HIT:
		case CELL_DESTROYED:
		case SHIP_DESTROYED:
			System.out.printf("Player-%d fires a missile with target %s which got hit\n", this.getPlayerId(),
					this.getLastMissileLocation());
			break;
		}
		return result;
	}

	public boolean isAllShipDestroyed() {
		return battleGround.isAllShipDestroyed();
	}

	public void printBattleGround() {
		this.battleGround.printBattleGround();
	}

}
