package com.merp.game.base;

public class Location {
	private final int x;
	private final int y;
	public Location(final int x, final int y){
		this.x = x;
		this.y = y;
	}
	
	public Location(final String location) {
		this(location.charAt(0) - 'A', location.charAt(1) - '1');
	}
	
	/**
	 * @param location : location in form of [A-Z][1-9]
	 * @return : location in the the Battle Ground;
	 */
	public static Location getLocation(final String locationString) {
		return new Location(locationString.charAt(0) - 'A', locationString.charAt(1) - '1');
	}
	
	public static String getLocationString(final int xCord, final int yCord) {
		StringBuffer sb = new StringBuffer();
		sb.append((char)(xCord + 'A'));
		sb.append((char)(yCord + '1'));
		return sb.toString();
	}
	public static String getLocationString(final Location location) {
		return getLocationString(location.getX(), location.getY());
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
}
