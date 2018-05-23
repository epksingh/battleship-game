package com.merp.game.utils;

public class Converter {
	
	
	public static int stringToInt(String str, char min) {
		int res = -1;
		if(str!= null && str.length() > 0) {
			res = str.charAt(0) - min;
		}
		return res;
	}
	
}
