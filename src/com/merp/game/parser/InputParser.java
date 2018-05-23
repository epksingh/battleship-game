package com.merp.game.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.merp.game.base.Location;
import com.merp.game.exception.InvalidInputException;
import com.merp.game.ships.Ship;
import com.merp.game.ships.ShipFactory;
import com.merp.game.utils.Constaint;
import com.merp.game.utils.Constant;
import com.merp.game.utils.Converter;
import com.merp.game.utils.Type;

public class InputParser {
	/*
	 * public Map<String, Object> parser(String fileName) throws IOException {
	 * BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
	 * return parser(br); }
	 */
	public static int getBattleAreaWidth(String width) throws InvalidInputException {
		if (!Constaint.isValidGroundWidth(width)) {
			throw new InvalidInputException(
					"Please provide gorund width between " + Constant.COL_MIN + " to " + Constant.COL_MAX);
		}
		return Converter.stringToInt(width, Constant.COL_MIN) + 1;
	}

	public static int getBattleAreaHieght(String hight) throws InvalidInputException {
		if (!Constaint.isValidGroundHight(hight)) {
			throw new InvalidInputException(
					"Please provide gorund Hight between " + Constant.ROW_MIN + " to " + Constant.ROW_MAX);
		}
		return Converter.stringToInt(hight, Constant.ROW_MIN) + 1;
	}

	public static Map<String, Object> parser(BufferedReader br) throws IOException, InvalidInputException {
		Map<String, Object> playerData = new HashMap<>();

		String[] battleArea = br.readLine().split(" ");
		int noOfRow = getBattleAreaHieght(battleArea[1]);
		int noOfCol = getBattleAreaWidth(battleArea[0]);
		int noOfShip = Integer.parseInt(br.readLine());
		playerData.put(Constant.NO_OF_ROW, noOfRow);
		playerData.put(Constant.NO_OF_COLUMN, noOfCol);
		playerData.put(Constant.NO_OF_SHIP, noOfShip);
		// parsing ship data
		List<List<Ship>> ships = new ArrayList<>();
		ArrayList<Ship> shipList1 = new ArrayList<>();
		ArrayList<Ship> shipList2 = new ArrayList<>();
		for (int i = 0; i < noOfShip; i++) {
			String[] shipData = br.readLine().split(" ");
			Type shipType = Type.valueOf(shipData[0]);
			int shipRowSize = Integer.parseInt(shipData[2]);
			int shipColSize = Integer.parseInt(shipData[1]);
			shipList1.add(ShipFactory.getShip(shipType, shipRowSize, shipColSize, new Location(shipData[3])));
			shipList2.add(ShipFactory.getShip(shipType, shipRowSize, shipColSize, new Location(shipData[4])));
		}
		ships.add(shipList1);
		ships.add(shipList2);
		playerData.put(Constant.SHIP_DATA, ships);

		List<Location[]> locations = new ArrayList<>();

		String[] targates1 = br.readLine().split(" ");
		Location[] targateLocaiton1 = new Location[targates1.length];
		for (int i = 0; i < targates1.length; i++) {
			targateLocaiton1[i] = new Location(targates1[i]);
		}
		locations.add(targateLocaiton1);

		String[] targates2 = br.readLine().split(" ");
		Location[] targateLocaiton2 = new Location[targates2.length];
		for (int i = 0; i < targates2.length; i++) {
			targateLocaiton2[i] = new Location(targates2[i]);
		}
		locations.add(targateLocaiton2);
		playerData.put(Constant.TARGATE_LOCATION, locations);
		return playerData;
	}

}
