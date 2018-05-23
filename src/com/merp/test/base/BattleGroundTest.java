package com.merp.test.base;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.merp.game.base.BattleGround;

public class BattleGroundTest {
	String expectedOutPut="- - - \n" + 
			"- - - \n" + 
			"- - - \n";
	private PrintStream sysOut;
	private final ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		sysOut = System.out;
		System.setOut(new PrintStream(outPutStream));
	}

	@After
	public void revertStreams() {
		System.setOut(sysOut);
	}

	@Test
	public void createBattleGroundTest() {
		int row = 3;
		int col = 3;
		BattleGround ground = new BattleGround(row, col);
		ground.printBattleGround();
		assertEquals(expectedOutPut, outPutStream.toString());
	}

}
