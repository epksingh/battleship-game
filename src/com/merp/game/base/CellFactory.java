package com.merp.game.base;

import com.merp.game.base.Location;
import com.merp.game.utils.Type;

public class CellFactory {
	public static ICell getCell(final Type cellType, final Location location) {
		ICell cell = null;
		if (cellType == null) {
			return cell;
		}
		switch (cellType) {
		case P:
			cell = new PCell(location);
			break;
		case Q:
			cell = new QCell(location);
			break;
		case W:
		default:
			cell = new WaterCell(location);
			break;
		}
		return cell;
	}
}