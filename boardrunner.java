/**
 * Board Runner Class - Main Class
 *
 */
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 


public class boardrunner extends Application{ 
	
	//Initialize Objects for Board, Human Player and AI Player 
	Board board;
	Player human1;
	Player human2;
	AI ai;
	Button aiAction;
	Label message = new Label("");
	Label infomessage = new Label("");
	Label newgamemessage = new Label("");
	Label roundinfo = new Label("");
	GridPane gridpane;
	
	int choice = 0;
	private int blackStone = 1;
	private int whiteStone = 2;
	
	/**
	 * Runs entire program
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Builds stage for game with JavaFX
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		gridpane = new GridPane();
		
		//set label colors
		message.setTextFill(Color.web("#FF76a3"));
		infomessage.setTextFill(Color.web("#FF8633"));
		roundinfo.setTextFill(Color.web("#F39C12"));
		newgamemessage.setTextFill(Color.web("#F1948A"));
		
		//place labels into a vbox
		VBox messagelbox = new VBox(message,infomessage,newgamemessage);
		messagelbox.setAlignment(Pos.BASELINE_CENTER);
		VBox myvbox = new VBox(5,gridpane,messagelbox);

		board = new Board(25,25);
		message.setText("Welcome to Gomoku! Select New Game for 2 Players or Select AI to Start.");
		
		for(int i=0; i<board.board_length; i++){
			for(int j=0;j<board.board_weight;j++){
				gridpane.add(setButton(), i, j);
			}
		}
		
		//Player vs Player - New Game Button
		//If the player selects New Game, the board will rebuild to handle 2 human players.
		
		Button start = new Button("New Game");
		start.setPrefSize(100, 40);
		start.setStyle("-fx-base: LightGrey");
		start.setOnAction(e->{
			choice = 1;
			board = new Board(25,25);
			
			
			human1 = new HumanPlayer("Player1", blackStone);
			human2 = new HumanPlayer("Player2", whiteStone);
			
			//Set the board weight and length.
			
			
			message.setText("You selected Player vs Player! Player1 is the black stone. Player2 is the white stone.");
			message.setText("Rules: Black Stone moves first. White Stone plays next. To win, have 5 stones in a row. ");
			newgamemessage.setText("");
			roundinfo.setText("");
			
			for(int i=0;i<board.board_length;i++){
				for(int j=0;j<board.board_weight;j++){
					gridpane.add(setButton(), i, j);
					board.board[i][j] = 0;
				}
			}
		});
		
		//Player vs AI - Start Game Button
		Button fightAI = new Button("New AI");
		fightAI.setPrefSize(80,40);
		fightAI.setStyle("-fx-base: Silver");
		
		fightAI.setOnAction(e->{
			choice = 2;
			board = new Board(25,25);
			
			human1 = new HumanPlayer("Player1", blackStone);
			ai = new AI("Dumb AI", whiteStone);

			message.setText("Human Player is the black stone. Computer Player is the white stone. Good luck.");
			message.setText("Rules: Black Stone moves first. White Stone plays next. To win, have 5 stones in a row. ");
			newgamemessage.setText("");
			roundinfo.setText("");
			
			for(int i=0;i<board.board_length;i++){
				for(int j=0;j<board.board_weight;j++){
					gridpane.add(setButton(), i, j);
					board.board[i][j] = 0;
				}
			}
		});
	
		//End Game Button
		Button end = new Button("End");
		end.setOnAction(e->{
			System.exit(0);
		});
		end.setPrefSize(80, 40);
		end.setStyle("-fx-base: DimGray");
		
		VBox menu = new VBox(30,roundinfo, start, fightAI, end);
		HBox screen = new HBox(5, myvbox, menu);
		menu.setAlignment(Pos.CENTER);
		
		//scene side.
		Scene scene = new Scene(screen,840,810);
		primaryStage.setScene(scene);
		primaryStage.setTitle("demo");
		primaryStage.show();
	}
	
	/** 
	 * Set titles on board.
	 * This function will make the Gomoku tiles to appear on the board and 
	 * handles the player's action when the tile is clicked.
	 * @return  buttons
	 */
	public Button setButton(){
		Button button = new Button("");
		
		
		button.setPrefSize(30, 30);
	
		button.setOnAction(e->{
			
			//Choice 1 - Player VS Player
			
			if(choice==1){
				
				//If there is no winner, build board and set tiles.
				
				if(human1.getWinner()!=true&&human2.getWinner()!=true) {
					
					//Gomoku Rules: Player1 will be black stone and will play first
					//Round %2=0 ensure black stone goes first which means player1 go first
					//Player1 will always have black stone by default
					
					if(board.round%2==0){
						
						//Set X and Y position of stone and match it to the player 
						
						human1.setCurrentX(GridPane.getRowIndex(button));
						human1.setCurrentY(GridPane.getColumnIndex(button));
				
							//If position X and Y are not empty, display message
						
							if(board.board[human1.getCurrentX()][human1.getCurrentY()]!=0){	
								message.setText("There is a stone here. Try again.");
								}
						else {
							//If position is empty, place stone
							
							board.myTurn(human1);
							message.setText(board.getTurnInfo());
							button.setStyle("-fx-base: DarkGray");
								
							if(human1.getWinner()){
								//Display winning text to players
								
								message.setText("The winner is " + human1.getName()+ ".\n" + human1.getLabel());
									
							}
								
						}
					}
			
					//%2!=0  Player 2 goes
					else{
						
						//Set X and Y position of stone and match it to the player 
						
						human2.setCurrentX(GridPane.getRowIndex(button));
						human2.setCurrentY(GridPane.getColumnIndex(button));
						
						//If position X and Y are not empty, display message
						
						if(board.board[human2.getCurrentX()][human2.getCurrentY()]!=0){
							message.setText("There is a stone here. Try again.");
							}
						else {
							
							//If position is empty, place stone
							
							board.myTurn(human2);
							message.setText(board.getTurnInfo());
							button.setStyle("-fx-base: White");
							
							if(human2.getWinner()){
								
								//Display winning text to players
								
								message.setText("The winner is "+ human2.getName()+".\n"+human2.getLabel());
								
							}
						}
					}
				}
			}
			
			//Choice 2 - Player VS AI
			
			else if(choice==2){
	
				//If there is no winner, build board and set tiles.
				
				if(human1.getWinner()!=true&&ai.getWinner()!=true) {
					//Set X and Y position of stone and match it to the player 
					human1.setCurrentX(GridPane.getRowIndex(button));
					human1.setCurrentY(GridPane.getColumnIndex(button));
					
					//If position X and Y are not empty, display message
					if(board.board[human1.getCurrentX()][human1.getCurrentY()]!=0){
						message.setText("There is a stone here.");
					}
					else {
						//If position is empty, place stone
					
						board.myTurn(human1);
						message.setText(board.getTurnInfo());
						button.setStyle("-fx-base: DarkGray");
					
						if(human1.getWinner()){
						
							//Display winning text to players
							message.setText("The winner is "+human1.getName()+".\n"+human1.getLabel());
						
						}
					
						//If the human player doesn't win... RUN!
						if(human1.getWinner()!=true){
							//Create a new button
						
							aiAction = new Button();
						
							//Set size
						
							aiAction.setPrefSize(30, 30);
						
							//set ai's x y, and the x y that he get will never have a stone there.
							ai.get_x_y(board);
						
						
							//put the stone there
						
							board.myTurn(ai);
						
							// add the new button on top of the board at the x y.
						
							gridpane.add(aiAction, ai.getCurrentY(),ai.getCurrentX());
						
							//show the user where the AI put his stone.
						
							message.setText(board.getTurnInfo());
							
							//AI click the button.
						
							aiAction.fire();
						
							//ai's action.
							aiAction.setOnAction(new AImove());
						
							if(ai.getWinner()){
								message.setText("The winner is "+ai.getName()+".\n"+ai.getLabel());
							}	
						}
					}
				}
		}
			//If the user doesn't select New Game or Start
			else{
				message.setText("Please click new game or new AI in order to starte the game.");
			}
});
		//the button of the board.
		button.setStyle("-fx-base: Beige");
		return button;
}
	
	//when the AI click the button by himself, we run this action. And if the user click that stone, we tell him that there is a stone there, he can't put at that spot.
	/** AI's actions.
	 * This is AI's actions, when AI click the button by himself, the button set it to white means that the put a white stone there.
	 * @author xiaoy
	 *
	 */
	class AImove implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(board.board[ai.getCurrentX()][ai.getCurrentY()]!=0)
			{
				message.setText("There is a stone here.");
			}
			else {
			aiAction.setStyle("-fx-base: White");
			aiAction.setVisible(true);
		}
		}
	}
}