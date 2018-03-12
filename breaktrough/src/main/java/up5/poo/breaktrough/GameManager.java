package up5.poo.breaktrough;

import up5.poo.breaktrough.Player.Color;

public class GameManager {
	
	private Player[] players;
	private int[] board;
	private Color currentPlayerIndex;
	Boolean selected;
	int selectedTokenIndex;
	
	GameManager(){
		selected = false;
		board = new int[64];
		players = new Player[2];
		players[0]= new Player(Color.WHITE);
		players[1] = new Player(Color.BLACK);
		currentPlayerIndex = Color.WHITE;
		generateBoard();
	}
	
	
	/**
	 * generate the tokens on the board
	 */
	public void generateBoard(){
		for (int i = 0; i < board.length; i++){
			board[i] = 0;
			if ( (Math.abs(( i - ((board.length+1) / 2)))) >= 16) {
				if (i <= 15) board[i] = -1;
				else if(i> 16) board[i] = 1;
			}
			if(i%8 ==0) System.out.println("");		
			System.out.print(Integer.valueOf(board[i]) + ", ");
		}
	}
	
	public int[] getBoard(){
		return board;
	}
	public int getBoardValue(int i){
		return board[i];
	}
	
	public void updateBoard(Move move){
		
	}
	
	public void movePawn(int position, int owner, int destination){
		
	}
	
	
	public void switchPlayerIndex(){
		if (currentPlayerIndex == Color.WHITE) this.currentPlayerIndex = Color.BLACK;
		else this.currentPlayerIndex = Color.WHITE;
	}
	
	public void play(Move move){
		for (int i  =0; i < board.length; i++){
			if (i == move.getDestination().getIndex()){
				if (board[move.getDestination().getIndex()] != 0) {
					
				}
				board[move.getPosition().getIndex()] -= move.getColor();
				board[move.getDestination().getIndex()] += move.getColor();
				break;
			}
		}
	}
	public boolean isEndGame(){
		for(int i =0; i<8; i++){
			if (board[i] == 1) {
				return true; 
			}
		}
		
		for(int i = 48; i< board.length; i++){
			if (board[i] == -1) {
				return true;
			}
		}
		return false;
	}
	
}
