package racko;


import java.util.*;

public class RackoGame {
	private Player[] players;
	private ArrayList<Integer> deckOfCards;
	//Random random = new Random();
	private Stack<Integer> stockPile = new Stack<Integer>();
	private Stack<Integer> discardPile = new Stack<Integer>();
	
	/**
	 * The constructor of a Racko Game.
	 * @param playersList Takes a list of players from the user.
	 */
	public RackoGame(ArrayList<String> playersList) {
		deckOfCards = new ArrayList<Integer>();	//initializes the deckOfCards to a an arrayList
		//places the numbers 1 through 60 in the deck
		for (int x = 0; x < 60; x++) {
			deckOfCards.add(x+1);
		}
		//shuffles the deck
		Collections.shuffle(deckOfCards);
		
		//assigns the playersList to an array of players that is type Player
		players = new Player[playersList.size()];
		for (int i = 0; i < playersList.size(); i++) {
			players[i] = new Player(playersList.get(i));
		}
		
		//call to the createStockPile passing in the deckOf cards
		createStockPile(deckOfCards);
		
		//call to the createRack method passing the stockPile stack
		createRack(stockPile);
		
		//call to the playGame method passing the players
		playGame(players);
		
	}
	
	/**
	 * This method creates a stockPile using the data structure stack
	 * @param deckOfCards The arrayList of cards
	 */
	public void createStockPile(ArrayList<Integer> deckOfCards) {
		for (int i = 0; i < deckOfCards.size(); i++) {
			stockPile.push(deckOfCards.get(i));	
		}
	
	}
	
	/**
	 * This method uses the cards from the stockPile to fill up the players' racks
	 * @param stockPile The stack of cards 
	 */
	public void createRack(Stack<Integer> stockPile) {
		for(int j = 0; j < players.length; j++) {
			for (int i = 9; i >= 0 ; i--) {
				players[j].fillRack(i, stockPile.pop());				
			}
		}
		discardPile.push(stockPile.pop()); //places one card from the stockPile into the discardPile
	}
	
	/**
	 * This method is where each player takes a turn to choose from either the 
	 * stockPile or discardPile and decides if to swap and which card on his/her
	 * rack to swap it with.
	 * @param players The array of players
	 */
	public void playGame(Player[] players) {
		Scanner input = new Scanner(System.in);
		boolean gameOver = false;
		//As long as the game is not over, the for loop will continue to execute 
		//and each player will get a turn.
		while(!gameOver) {
		for (int x =0; x < players.length; x++) {
			System.out.println(players[x].toString()); //call to the toString method of the Player class which prints the player's rack
			//Player can choose from stockPile or discardPile
			System.out.println("1. Stock Pile ");
			System.out.println("2. Discard Pile: " + discardPile.peek());
			System.out.println("Choose 1 or 2: ");
			int choice = input.nextInt();
			//input validation for player's choice
			while(choice != 1 && choice != 2) {
				System.out.println("Invalid! Choose 1 or 2: ");
				choice = input.nextInt();
			}
			
			int temp = -1;
			int swap = 1;
			//Depending on what the player chooses, different things will occur.
			switch(choice) {
			//if player chooses 1
			case 1:
				//checks to see the stockPile is not empty
				//if it is, refills it with the discardPile by turning the cards over
				if (stockPile.isEmpty()) {
					while(!discardPile.isEmpty()) {
					stockPile.push(discardPile.pop());
					}
				}
				//a temporary variable to hold the card drawn from the stockPile
				temp = stockPile.pop();
				System.out.println("Card: " + temp); //displays the card to the player
				//player chooses if wants to swap the card
				System.out.print("Would you like to swap this card?\n1.Yes \n2.No ");
				swap = input.nextInt();
				//input validation on the swap choice
				while(swap != 1 && swap != 2) {
					System.out.print("Invalid! Would you like to swap this card?\n1.Yes \n2.No\n ");
					swap = input.nextInt();
				}
				
				break;
			//if player chooses 2 
			case 2:
				//no need to check if discardPile is empty because it isn't possible
				//a temporary variable to hold the card drawn from the discard pile
				temp = discardPile.pop();
				
				System.out.println("Card: " + temp);//displays the card to the player
				break;
				
		
			
		}
			//variable to hold the card that will be placed on the discardPile
			int holder;
			//If they want to swap the card then...
			if(swap == 1) {
				//they choose which card to swap it with by typing what number place the card comes in
				System.out.print("Choose which card to swap with: ");
				int index = input.nextInt()-1;
				
				//input validation
				while (index < 0 || index > 9) {
					System.out.print("Invalid! Choose which card to swap with: ");
					index = input.nextInt()-1;
				}
				input.nextLine();//clear the buffer
				//the holder is assigned to the card value the player chose
				holder = players[x].swapCard(index, temp);
			}
			else {
				//otherwise the holder is assigned what player drew from the stockPile
				holder = temp;
			}
			
			//pushes the card onto the discardPile
			discardPile.push(holder);
			//checks if the player has all his/her cards in ascending order 
			//which means the game is over cuz the currPlayer won
			gameOver = gameOver(players[x]);
			//if game is over displays the message.
			if (gameOver) {
				System.out.println("Congratulations " + players[x].getName() + "!");
				System.out.println("You won the game!");
			}
		}
	}
}
	
	/**
	 * This method checks to see if the current player has all his cards in ascending order
	 * @param currPlayer The player who's turn it is
	 * @return boolean - true/false
	 */
	public boolean gameOver(Player currPlayer) {
		for (int i = 0; i < 9; i++) {
			//if a previous index value is greater than the index value right after it
			//right away returns false - the game is not over
			if(currPlayer.getIndexValue(i) > currPlayer.getIndexValue(i+1)) {
				return false;
			}
		} return true; //otherwise returns true, and the game ends.
	}

}

