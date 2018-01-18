package gamestudio.game.puzzle.consoleui;

import java.util.Scanner;

import gamestudio.consoleui.ConsoleGameUI;
import gamestudio.game.puzzle.core.*;

public class ConsoleUI implements ConsoleGameUI {
	//private Field field = new Field(4, 5);
	
		private Field field;
	
	private Scanner scanner = new Scanner(System.in);
	public long gametime;
	public long getGametime() {
		return gametime;
	}
	public ConsoleUI(Field field) {
		this.field = field;
	}
	
	public void startTime(long gametime) {
		this.gametime = System.currentTimeMillis();
	}

	public static void setGametime(long gametime) {
		//gametime;
	}

	public void run() {
		print();
		while(!field.isSolved()) {
			processInput();
			print();
		}
		System.out.println("You won!");
	}

	private void processInput() {
		System.out.print("Enter tile number or X to exit: ");
		String input = scanner.nextLine().trim().toUpperCase();
		if("X".equals(input))
			System.exit(0);
		try {
			int tile = Integer.parseInt(input);
			if(!field.moveTile(tile)) {
				System.out.println("Really crazy?");
			}
		} catch (NumberFormatException e) {
			System.out.println("Are you crazy?");
		}
	}

	private void print() {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				int tile = field.getTile(row, column);
				if (tile == Field.EMPTY_TILE)
					System.out.printf("   ", tile);
				else
					System.out.printf(" %2d", tile);
			}
			System.out.println();
			this.gametime = System.currentTimeMillis();
		}
		//Date date = new Date();
		//System.out.println(System.currentTimeMillis());
		//long gametime;
		// gametime = System.currentTimeMillis();
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "15 Puzzle";
	}
}
