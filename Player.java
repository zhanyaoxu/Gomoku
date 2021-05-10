/**
 *  Abstract Class Player
 *  
 */
package application;

public abstract class Player implements PlayerAction {
	String playerName,label;
	int playerStone;
	boolean winner = false;
	private int currentX, currentY;
	
	Player(String name, int stone){
		playerStone = stone;
		playerName = name;
	}
	
    /**
    * Returns the current Y position of player's stone
    *
    * @return integer
    */
	public int getCurrentY(){
		return currentY;
	}
	
    /**
    * Returns the current X position of player's stone
    *
    * @return integer
    */
	public int getCurrentX(){
		return currentX;
	}
	
    /**
    * Sets the current X position of player's stone
    *
    * @param integer
    */
	public void setCurrentX(int x){
		currentX = x;
	}
	
    /**
    * Sets the current Y position of player's stone
    *
    * @param integer
    */
	public void setCurrentY(int y){
		currentY = y;
	}
	
    /**
    * Sets the winning player
    * using boolean
    * 
    */
	public void setWinner(){
		winner = true;
	}
	
	
    /**
    * Returns the winning player's status
    *
    * @return boolean
    */
	public boolean getWinner(){
		return winner;
	}
	
    /**
    * Returns the player's name
    *
    * @return String
    */
	public String getName(){
		return playerName;
	}
	
    /**
    * Sets the player's name
    *
    * @param String
    */
	public void setName(String name){
		playerName = name;
	}
	
    /**
    * Returns the player's stone
    *
    * @param integer
    */
	public int getStone(){
		return playerStone;
	}
	

	/**
	 * Returns the corresponding label
	 *
	 * @param String
	 */
	public String getLabel(){
		return label;
	}
	
	public abstract void setlabel();
}