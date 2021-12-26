package racko;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<String> players = new ArrayList<String>(); //arrayList of players
		
		int numPlayers;
		//asks user for amount of players
		System.out.println("Please enter the number of players:(2-4) ");
		numPlayers = input.nextInt();
		//input validation on numPlayers
		while(numPlayers != 2 && numPlayers != 3 && numPlayers != 4) {
			System.out.println("Invalid! Please enter the number of players:(2-4) ");
			numPlayers = input.nextInt();
		}
		
		input.nextLine();//clears the buffer
		String name;
		//asks for the names of all the players
		for (int x = 0; x < numPlayers; x++) {
			System.out.println("Enter the name of Player " + (x+1));
			name = input.nextLine();
			players.add(name);
		}
		//instantiates a new game
		RackoGame game = new RackoGame(players);

		
	}

}
