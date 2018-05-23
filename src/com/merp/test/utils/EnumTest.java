package com.merp.test.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.merp.game.utils.Type;

public class EnumTest {
	@Test
	public void testEnumType() {
		Type pType = Type.valueOf("P");
		assertEquals(pType.getMaxHit(),1);
		assertEquals(pType, Type.P);
	}
}
