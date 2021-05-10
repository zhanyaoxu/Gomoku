package application;

public class AI extends Player {
	
	//countX, CountY count how many steps did y and x coordinated moved.
	int countX=1, countY=1, AI_x, AI_y;
	boolean AI_next=false;
	
	public AI(String name,int stone) {
		super(name, stone);
	}
	
	
	/**
	 * Selects X and Y coordinates for AI
	 * 
	 * AI get his currentX and currentY.
	 * AI get his column coordinate and row coordinate.
	 * AI picks coordinates by using random functions.
	 * 
	 * @param board
	 */
	public void randx_y(Board board){
		AI_x=((int)(Math.random()*board.board_length));
		AI_y=((int)(Math.random()*board.board_weight));
		
		//If: there is an existing stone on the coordinate
		if(board.board[AI_x][AI_y]!=0){
			
			//Find an empty spot to set stone using Matn.random
			do {
				AI_x=((int)(Math.random()*board.board_length));
				AI_y=((int)(Math.random()*board.board_weight));
				setCurrentX(AI_x);
				setCurrentY(AI_y);
			}
			
			while(board.board[AI_x][AI_y]!=0);
			
		} 
		//Else: position is empty, place stone	
		else {
			setCurrentX(AI_x);
			setCurrentY(AI_y);
		}		

	}
	
    /**
    * This constructor controls AI's moves
    *
    * @param board
    */
	
	public void get_x_y(Board board){
		//first, AI will find a random spot.
		
		if(board.getRound()<=2){	
			randx_y(board);
		}
		
		//after AI find a random spot, he will check the following:
		
		else{
			AI_x = getCurrentX();
			AI_y = getCurrentY();
		
			//check his right side to see if the spot is empty, if it is empty he will put his stone at that spot.
			
			if(AI_y<board.getBoard_weight()){
				try {
					if(board.board[AI_x][AI_y+1]==0){
						setCurrentX(AI_x);
						setCurrentY(AI_y+1);
						countY++;
						AI_next=true;
					}
				}catch(Exception e){
					//
				}
			}
			//if right spot is not empty, he will check his left side to see if the spot is empty, if it is empty he will put his stone at that spot.
			
			if(AI_y>0&&AI_next!=true){
				try {
					if(board.board[AI_x][AI_y-countY]==0){
						setCurrentX(AI_x);
						setCurrentY(AI_y-countY);
						countY=1;
						AI_next=true;
					}
				}catch(Exception e){
					//
				}
			}
			
			//right and left are both not empty, then he will check his top side to see if the spot is empty or not, if it is empty he will put his stone at that spot.
			
			if(AI_x>0&&AI_next!=true){
				try {
					if(board.board[AI_x-1][AI_y]==0){
						setCurrentX(AI_x-1);
						setCurrentY(AI_y);
						countX++;
						countY=1;
						AI_next=true;
					}
				}catch(Exception e){
					
				}
			}
			
			//right left top side are not empty, then check down side.
			if(AI_x<board.getBoard_length()&&AI_next!=true){
				try {
					if(board.board[AI_x+countX][AI_y]==0){
						setCurrentX(AI_x+countX);
						setCurrentY(AI_y);
						countX=1;
						countY=1;
						AI_next=true;
					}
				}catch(Exception e){
					
				}
			}
			
			//if all the situations are false, then he will check his down right side.
			if(AI_x<board.getBoard_length()&&AI_y<board.getBoard_weight()&&AI_next!=true){
				try {
					if(board.board[AI_x+1][AI_y+1]==0){
						setCurrentX(AI_x+1);
						setCurrentY(AI_y+1);
						countX++;
						countY++;
						AI_next=true;
					}
				}catch(Exception e){
					
				}
			}
			//down right side are false, then check top left side.
			
			if(AI_x>0&&AI_y>0&&AI_next!=true){
				try {
					if(board.board[AI_x-countX][AI_y-countY]==0){
						setCurrentX(AI_x-countX);
						setCurrentY(AI_y-countY);
						countX=1;
						countY=1;
						AI_next=true;
					}
				}catch(Exception e){
					
				}
			}
			//still false, then check top right side.
			
			if(AI_x>0&&AI_y<board.getBoard_weight()&&AI_next!=true){
				try{
					if(board.board[AI_x-1][AI_y+1]==0){
						setCurrentX(AI_x-1);
						setCurrentY(AI_y+1);
						countX++;
						countY++;
						AI_next=true;
					}
				}catch(Exception e){
					
				}
			}
			//last one down left side.
			
			if(AI_x<board.getBoard_length()&&AI_y>0&&AI_next!=true){
				try{
					if(board.board[AI_x+countX][AI_y-countY]==0){
						setCurrentX(AI_x+countX);
						setCurrentY(AI_y-countY);
						countX=1;
						countY=1;
						AI_next=true;
					}
				}catch(Exception e){
					
				}
			}
			if(AI_next!=true){
				randx_y(board);
				countX=1;
				countY=1;
			}
			AI_next=false;
		}
}
	@Override
	public void setlabel() {
		label="He is better than you. Hope you can win him next time.";
	}
}
