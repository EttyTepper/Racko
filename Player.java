package racko;

public class Player {
	private int[] rack;
	private String name;
	
	/**
	 * Constructor for the Player class
	 * @param name The name of the player
	 */
	public Player(String name) {
		this.name = name;
		rack = new int[10]; //initializes the rack to an int array size of 10
		
	}
	
	/**
	 * This method returns the name of the player.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method returns a value at a specified index.
	 * @param index The index passed in.
	 * @return The value of the card at that index.
	 */
	public int getIndexValue(int index) {
		return rack[index];
	}
	
	/**
	 * This method fills up the rack when the game first begins.
	 * @param index The index specified to hold the value
	 * @param card The value of the card
	 */
	public void fillRack(int index, int card) {
		rack[index] = card;
	}
	
	/**
	 * This method swaps a chosen card with a card on the player's rack.
	 * @param index The index of the card the user wants to swap on his rack
	 * @param card The card the user chose that should be swapped in its place
	 * @return holder Returns the card that was previously in the rack.
	 */
	public int swapCard(int index, int card) {
		int holder;
	  	holder = rack[index];
		rack[index] = card;
		return holder;
	}
	
	/**
	 * A toString method to display the player's rack.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(name + ":\n");
		for (int i = rack.length-1; i >= 0; i--) {
			str.append((i+1) + ". " + rack[i] + "\n");
		}
		
		return str.toString();
		
	}

}
