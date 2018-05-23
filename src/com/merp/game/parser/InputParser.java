package com.merp.game.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.merp.game.base.Location;
import com.merp.game.exception.InvalidInputException;
import com.merp.game.ships.IShip;
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
	
	public static Map<String, Object> parser(BufferedReader br) throws IOException, InvalidInputException {
		Map<String, Object> playerData = new HashMap<>();

		String[] battleArea = br.readLine().split(" ");
		int noOfRow = getBattleAreaHieght(battleArea[1]);
		int noOfCol = getBattleAreaWidth(battleArea[0]);
		int noOfShip = Integer.parseInt(br.readLine());
		playerData.put(Constant.NO_OF_ROW, noOfRow);
		playerData.put(Constant.NO_OF_COLUMN, noOfCol);
		playerData.put(Constant.NO_OF_SHIP, noOfShip);
		playerData.put(Constant.SHIP_DATA, parseShips(noOfShip, br));
		playerData.put(Constant.TARGATE_LOCATION, parseTargate(br));
		return playerData;
	}
	
	private static List<Location[]> parseTargate(BufferedReader br) throws IOException {
		List<Location[]> locations = new ArrayList<>();
		locations.add(parseTargate(br.readLine()));
		locations.add(parseTargate(br.readLine()));
		return locations;
	}
	
	private static Location[] parseTargate(String targate) {
		String[] targates = targate.split(" ");
		Location[] targateLocaiton = new Location[targates.length];
		for (int i = 0; i < targates.length; i++) {
			targateLocaiton[i] = new Location(targates[i]);
		}
		return targateLocaiton;
	}

	public static List<List<IShip>> parseShips(int noOfShip, BufferedReader br) throws IOException {
		// parsing ship data
		List<List<IShip>> ships = new ArrayList<>();
		ArrayList<IShip> shipList1 = new ArrayList<>();
		ArrayList<IShip> shipList2 = new ArrayList<>();
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
		return ships;
	}
	
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


}
