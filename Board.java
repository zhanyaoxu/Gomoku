package application;

public class Board {

    int board_weight, board_length, round = 0, countstone, min_board_weight, min_board_length;
    String moves;
    int[][] board;

    Board(int lenght, int weight) {
        board_weight = weight;
        board_length = lenght;
        board = new int[lenght][weight];
        countstone = 0;
        min_board_weight = 0;
        min_board_length = 0;

        for (int i = 0, j = 0; i < lenght && j < weight; j++, i++) {
            board[i][j] = 0;
        }
    }

    /**
     * Sets label message 
     * The label will tell the users where the players made their move
     *
     * @param String
     */
    public void setTurnInfo(String info) {
        moves = info;
    }

    /**
     * Returns label message 
     * The label will tell the users where the players made their move
     *
     * @return String
     */
    public String getTurnInfo() {
        return moves;
    }

    /**
     * Returns current round of plays
     * 
     *
     * @return integer
     */
    public int getRound() {
        return round;
    }

    /**
     * Returns board weight
     * 
     *
     * @return integer
     */
    public int getBoard_weight() {
        return board_weight;
    }

    /**
     * Returns board length
     * 
     *
     * @return integer
     */

    public int getBoard_length() {
        return board_length;
    }

    //player put his stone, and when it is round 8 or more, we can checkwin, there is no point to check wins when round is not at 8 because there is only 7 or lesser stone on the board.
    /**
     * players turn
     * player's turn. which they will put a stone at that coordinates that they picked.
     * @param player
     */
    public void myTurn(Player player) {
        setTurnInfo(player.playerName + " put a stone on coordinate [" + player.getCurrentX() + "][" + player.getCurrentY() + "].");
        board[player.getCurrentX()][player.getCurrentY()] = player.getStone();
        round++;
        if (round >= 8)
            checkWin(player);
    }

    //if player win, we say who win and set the winner to true.
    /**win settings.
     * if the player win, there will be a message that said they win. and everything set back to normal.
     * @param player
     */
    public void win(Player player) {
        player.setlabel();
        countstone = 0;
        player.setWinner();
    }
    /**check wins.
     * if there is 5 stones that next to each other, that means the player win.
     * @param player
     */
    public void checkWin(Player player) {
        //check right only, and if you put a stone at y = board_weight-1, no point to check your right side.
        if (player.getCurrentY() != (board_weight - 1)) {
            for (int x = player.getCurrentX(), y = player.getCurrentY(); y < board_weight; y++) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() & board[x][y + 1] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else {
                        //break;
                    }
                } catch (Exception e) {
                    //break;
                }
            }
        }
        //left only. and if your currenty is 0, there is no point to check the left side.
        if (player.getCurrentY() != 0 && player.getWinner() != true) {
            //y need to bigger than 0.
            for (int x = player.getCurrentX(), y = player.getCurrentY(); y > min_board_weight; y--) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x][y - 1] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    //break;
                }
            }
        }
        // if there is nothing on the left side and nothing on the right side, count will go back to 0.
        countstone = 0;
        //check down side. and there is no point to check if currentx is board_lenght-1. 
        if (player.getCurrentX() != board_length && player.getWinner() != true) {
            //x need to smaller than board_lenght.
            for (int x = player.getCurrentX(), y = player.getCurrentY(); x < board_length; x++) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x + 1][y] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    //break;
                }
            }
        }
        //check up side, there is no point to check when currentx is 0. Nothing up there. you are in the board line.
        if (player.getCurrentX() != 0 && player.getWinner() != true) {
            //x need to be bigger than 0 because array don't have -1 if your x is 0 then if we check it there will be errors.
            for (int x = player.getCurrentX(), y = player.getCurrentY(); x > min_board_length; x--) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x - 1][y] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else
                        break;
                } catch (Exception e) {
                    //break;
                }
            }
        }
        //if we count the up and down side and countstone still don't equal to 4, we set it back to 0.
        countstone = 0;
        // form up left to down right. and there is no point to check when you are in board[board_lenght-1][board_weight-1]. right?
        if (player.getCurrentX() != board_length - 1 && player.getCurrentY() != board_weight && player.getWinner() != true) {
            //x need to smaller than board_lenght and y need to small than board_weight.
            for (int x = player.getCurrentX(), y = player.getCurrentY(); x < board_length && y < board_weight; x++, y++) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x + 1][y + 1] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else
                        break;
                } catch (Exception e) {
                    //break;
                }
            }
        }
        //form down right to top left. no point to check when you are in board[0][0]. [0][0] is the smallest coordinate, there is no [-1][-1].
        if (player.getCurrentX() != 0 && player.getCurrentY() != 0 && player.getWinner() != true) {
            //x and y need to bigger than 0 in order to check from down right to top left.
            for (int x = player.getCurrentX(), y = player.getCurrentY(); y > min_board_weight && x > min_board_length; x--, y--) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x - 1][y - 1] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else
                        break;
                } catch (Exception e) {
                    //break;
                }
            }
        }
        //if countstone!=4, set it back to 0.
        countstone = 0;
        //from down left to top right. no point to check when your y is at 0, there is no -1 and there is no point to check when your x is at the board_lenght-1.
        if (player.getCurrentY() != 0 && player.getCurrentX() != (board_length - 1) && player.getWinner() != true) {
            //x need to bigger than board_lenght and y need to bigger than 0 because there is no x coordinate [board_lenght+1] and y coordinate [-1].
            for (int x = player.getCurrentX(), y = player.getCurrentY(); x < board_length && y > min_board_weight; y--, x++) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x + 1][y - 1] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else
                        break;
                } catch (Exception e) {
                    //break;
                }
            }
        }
        //form top right to down left. no point to check when your x is 0 or your y is board_weight-1 there is no -1.
        if (player.getCurrentX() != 0 && player.getCurrentY() != (board_weight - 1) && player.getWinner() != true) {
            //x need to bigger than 0 and y need to smaller than board_weight which is colum.
            for (int x = player.getCurrentX(), y = player.getCurrentY(); x > min_board_length && y < board_weight; x--, y++) {
                try {
                    //if there are 2 stones that next to each other are the same, countstone++, and when countstone ==4 means that you have 5 stones already, you win the game!.
                    if (board[x][y] == player.getStone() && board[x - 1][y + 1] == player.getStone()) {
                        countstone++;
                        if (countstone == 4) {
                            win(player);
                            break;
                        }
                    } else
                        break;
                } catch (Exception e) {
                    //break;
                }
            }
            //run all the loops and still don't get 4, set it back to 0.
            countstone = 0;
        }
    }
}