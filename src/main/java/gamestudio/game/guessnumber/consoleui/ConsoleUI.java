package gamestudio.game.guessnumber.consoleui;

import java.util.Scanner;

import gamestudio.consoleui.ConsoleGameUI;

public class ConsoleUI {
	// public class ConsoleUI implements ConsoleGameUI {
	private int count = 1;

	public String guessnumber;

	int rannum;

	public int getRannum() {
		return rannum;
	}

	public void setRannum(int rannum) {
		this.rannum = rannum;
	}

	private String message1;

	public String getMessage1() {
		return message1;
	}

	public ConsoleUI(int level) {
		rannum = (int) (Math.random() * level) + 1;
		//run1();
	}

	

	public void run() {

		System.out.println("Uhadni cislo od 1 do 10");

		Scanner scanner = new Scanner(System.in);

		int rannum = (int) (Math.random() * 10) + 1;

		String line = scanner.nextLine();
		if ("x".equals(line)) {
			System.exit(0);

		}
		int number = Integer.parseInt(line);
		System.out.println(rannum);

		do {

			if (number == rannum) {

				System.out.println("Uhadol si :-)");
				System.exit(0);
				count++;
			} else {

				if ("x".equals(line)) {
					System.exit(0);

				}

				System.out.println("Hadaj zas ! ");
				number = scanner.nextInt();

				count++;

			}

		} while (number != rannum);

		System.out.println("Uhadol si :-)");
		System.out.println("------------------------");
		System.out.println("Pocet Tahov: " + count);

	}

	public void process(int guessnumber) {

		// int hint = Integer.parseInt(guessnumber);

		if (guessnumber == rannum) {
			message1 = "Great, you have found a secret number";
		}
		if (guessnumber > rannum) {
			message1 = "Your Tip is too High, try again";

		}
		if (guessnumber < rannum) {
			message1 = "Your Tip is too Low, try again";
		}

	}
	// override
	/*
	 * public String getName() { // TODO Auto-generated method stub return
	 * "GessNumber"; }
	 */
}
