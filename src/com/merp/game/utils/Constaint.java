package com.merp.game.utils;

public class Constaint {
	public static boolean isValidGroundWidth(String str) {
		char groundWidth = str.charAt(0);
		return groundWidth >= Constant.COL_MIN && groundWidth <= Constant.COL_MAX; 
	}
	public static boolean isValidGroundHight(String str) {
		char groundHeight = str.charAt(0);
		return groundHeight >= Constant.ROW_MIN && groundHeight <= Constant.ROW_MAX; 
	}

}
