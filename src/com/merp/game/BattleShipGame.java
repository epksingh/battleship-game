package com.merp.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.merp.game.base.Location;
import com.merp.game.exception.InvalidInputException;
import com.merp.game.exception.ShipOverLappingException;
import com.merp.game.parser.InputParser;
import com.merp.game.ships.Ship;
import com.merp.game.users.Player;
import com.merp.game.utils.Constant;
import com.merp.game.utils.ResultState;

public class BattleShipGame {

	Player[] players = { new Player(1), new Player(2) };

	/**
	 * Get the reader for Input File and initialize both player
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws ShipOverLappingException
	 * @throws IndexOutOfBoundsException
	 * @throws InvalidInputException 
	 */
	public BattleShipGame() throws IOException, IndexOutOfBoundsException, InvalidInputException, ShipOverLappingException{
		this(new BufferedReader(new InputStreamReader(System.in)));
	}

	public BattleShipGame(String fileName) throws IOException, IndexOutOfBoundsException, InvalidInputException, ShipOverLappingException{
		this(new BufferedReader(new FileReader(new File(fileName))));
	}
	/**
	 * Method invoke parser and initialize all players
	 * @param br
	 * @throws IOException
	 * @throws IndexOutOfBoundsException
	 * @throws InvalidInputException 
	 * @throws ShipOverLappingException 
	 */
	public BattleShipGame(BufferedReader br) throws IOException, IndexOutOfBoundsException, InvalidInputException, ShipOverLappingException {
			Map<String, Object> parsedData = InputParser.parser(br);
			int noOfRow = (int) parsedData.get(Constant.NO_OF_ROW);
			int noOfCol = (int) parsedData.get(Constant.NO_OF_COLUMN);
			int noOfShip = (int) parsedData.get(Constant.NO_OF_SHIP);
			@SuppressWarnings("unchecked")
			List<List<Ship>> shipData = (List<List<Ship>>) parsedData.get(Constant.SHIP_DATA);
			@SuppressWarnings("unchecked")
			List<Location[]> locationData = (List<Location[]>) parsedData.get(Constant.TARGATE_LOCATION);
			players[0].createBattleGround(noOfRow, noOfCol);
			players[0].deployeeShips(shipData.get(0));
			players[0].setMissileTargate(locationData.get(0));

			players[1].createBattleGround(noOfRow, noOfCol);
			players[1].deployeeShips(shipData.get(1));
			players[1].setMissileTargate(locationData.get(1));

	}

	/**
	 * This method will take care of turn on player,
	 * and print Result of game.
	 */
	public void playGame() {

		int turn = 0;
		int length = players.length;
		Player player = players[turn];
		Player oponent = players[turn + 1];
		do {

			ResultState result = player.fireMissile(oponent);
			if (result == ResultState.NO_MISSILE || result == ResultState.NO_HIT) {
				turn = (turn + 1) % length;
			} else if (result == ResultState.SHIP_DESTROYED && oponent.isAllShipDestroyed()) {
				System.out.printf("Player-%d won the battle", player.getPlayerId());
				break;
			}

			player = players[turn];
			oponent = players[(turn + 1) % length];

		} while (player.isMissileAvailable() || oponent.isMissileAvailable());
		if (!player.isMissileAvailable() && !oponent.isMissileAvailable()) {
			System.out.println("Peace Declared");
		}
	}

}
