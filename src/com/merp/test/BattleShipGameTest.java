package com.merp.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.merp.game.BattleShipGame;
import com.merp.game.exception.ShipOverLappingException;

public class BattleShipGameTest {

	String expectedOutPut[] = {
			"Player-1 fires a missile with target A1 which got miss\n"
					+ "Player-2 fires a missile with target A1 which got hit\n"
					+ "Player-2 fires a missile with target B2 which got miss\n"
					+ "Player-1 fires a missile with target B2 which got hit\n"
					+ "Player-1 fires a missile with target B2 which got hit\n"
					+ "Player-1 fires a missile with target B3 which got miss\n"
					+ "Player-2 fires a missile with target B3 which got miss\n"
					+ "Player-1 has no more missiles left to launch\n"
					+ "Player-2 fires a missile with target A1 which got hit\n"
					+ "Player-2 fires a missile with target D1 which got miss\n"
					+ "Player-1 has no more missiles left to launch\n"
					+ "Player-2 fires a missile with target E1 which got miss\n"
					+ "Player-1 has no more missiles left to launch\n"
					+ "Player-2 fires a missile with target D4 which got hit\n"
					+ "Player-2 fires a missile with target D4 which got miss\n"
					+ "Player-1 has no more missiles left to launch\n"
					+ "Player-2 fires a missile with target D5 which got hit\n" + "Player-2 won the battle",
			"Player-1 fires a missile with target A1 which got miss\n"
					+ "Player-2 fires a missile with target B1 which got hit\n"
					+ "Player-2 fires a missile with target B2 which got hit\n"
					+ "Player-2 fires a missile with target B3 which got hit\n"
					+ "Player-2 fires a missile with target B1 which got hit\n"
					+ "Player-2 fires a missile with target D1 which got hit\n"
					+ "Player-2 fires a missile with target B2 which got hit\n"
					+ "Player-2 fires a missile with target D2 which got hit\n"
					+ "Player-2 fires a missile with target B3 which got hit\n"
					+ "Player-2 fires a missile with target E1 which got hit\n"
					+ "Player-2 fires a missile with target E2 which got hit\n"
					+ "Player-2 fires a missile with target F1 which got miss\n"
					+ "Player-1 fires a missile with target B2 which got miss\n"
					+ "Player-2 fires a missile with target F2 which got miss\n"
					+ "Player-1 fires a missile with target B3 which got miss\n"
					+ "Player-2 fires a missile with target F3 which got hit\n"
					+ "Player-2 fires a missile with target F4 which got hit\n"
					+ "Player-2 fires a missile with target F5 which got hit\n"
					+ "Player-2 fires a missile with target F6 which got hit\n"
					+ "Player-2 fires a missile with target D1 which got hit\n"
					+ "Player-2 fires a missile with target D2 which got hit\n"
					+ "Player-2 fires a missile with target E1 which got hit\n"
					+ "Player-2 fires a missile with target E2 which got hit\n" + "Player-2 won the battle" };
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
	public void testFullGameFromFileInput() throws IndexOutOfBoundsException, IOException, ShipOverLappingException {
		URL url = getClass().getResource("input_data1.txt");
		try {
			BattleShipGame bsg = new BattleShipGame(url.getPath());
			bsg.playGame();

			assertEquals(expectedOutPut[0], outPutStream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFullGameFromFileInput1() throws IndexOutOfBoundsException, IOException {

		InputStream inputStream = getClass().getResourceAsStream("input_data2.txt");
		try {
			BattleShipGame bsg = new BattleShipGame(new BufferedReader(new InputStreamReader(inputStream)));
			bsg.playGame();

			assertEquals(expectedOutPut[1], outPutStream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test

	public void testFullGameFromDirectInput() throws IndexOutOfBoundsException, IOException {
		String gameInput = "5 E\n" + "2\n" + "Q 1 1 A1 B2\n" + "P 2 1 D4 C3\n" + "A1 B2 B2 B3\n"
				+ "A1 B2 B3 A1 D1 E1 D4 D4 D5 D5\n";
		try {
			BattleShipGame bsg = new BattleShipGame(new BufferedReader(
					new InputStreamReader(new ByteArrayInputStream(gameInput.getBytes(StandardCharsets.UTF_8)))));
			bsg.playGame();

			assertEquals(expectedOutPut[0], outPutStream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFullGameFromConsoleInput() throws IndexOutOfBoundsException, IOException {
		/*
		 * BattleShipGame bsg = new BattleShipGame(); bsg.playGame();
		 * assertEquals(expectedOutPut[1], outPutStream.toString());
		 */
	}
}
