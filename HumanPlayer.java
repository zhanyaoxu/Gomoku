/**
 * HumanPlayer Class
 * 
 */
package application;

public class HumanPlayer extends Player {

	HumanPlayer(String name, int stone) {
		super(name, stone);
		
	}

	//If player wins, display label message
	@Override
	public void setlabel() {
		String stone ="";
		
		if(playerStone==1){
			stone = "Black";
		}
		
		else{
			stone = "White";
		}
		label= playerName + " with " + stone + " stone is too smart.";
	}
}