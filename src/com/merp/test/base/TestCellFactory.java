package com.merp.test.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.merp.game.base.CellFactory;
import com.merp.game.base.ICell;
import com.merp.game.base.PCell;
import com.merp.game.base.Location;
import com.merp.game.utils.Type;

public class TestCellFactory {
	
	@Test
	public void testGetFactory() {
		Type cellType = Type.P;
		Location location = new Location(7,4);
		ICell cell = CellFactory.getCell(cellType, location);
		assertNotNull(cell);
		assertTrue(cell instanceof PCell);
		assertEquals(cell.getCellType(), Type.P);
		assertEquals(7, cell.getCellPostion().getX());
		assertEquals(4, cell.getCellPostion().getY());
	}
}
