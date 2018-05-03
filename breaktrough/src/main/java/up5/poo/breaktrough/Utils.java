package up5.poo.breaktrough;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static boolean isLegal(Move move, int[] state, int playerValue) {
		return (playerValue == state[move.getPosition().getIndex()] ? true : false) && move.getDestination().isInsideBorder() && state[move.getDestination().getIndex()] != move.getColor();
				
	}
	
	public static boolean enemyAhead(Coordinate coord, int[] state, int playerValue){
		return (state[coord.getN(playerValue).getIndex()] == -playerValue);
	}
	
	public static ArrayList<Move> getPlayerLegalMoves(int[] board, int playerValue) {
		ArrayList<Move> legalMoves = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < getBoxLegalMove(board, playerValue, i).size(); j++)
				legalMoves.add(getBoxLegalMove(board, playerValue, i).get(j));
		}
		return legalMoves;
	}

	public static int[] play(Move move, int[] state) {
		state[move.getPosition().getIndex()] = 0;
		state[move.getDestination().getIndex()] = move.getColor();
		return state;
	}

	public static boolean isInsideZone(Coordinate coord, List<Coordinate> zone) {
		for (int i = 0; i < zone.size(); i++) {
			if (coord.equals(zone.get(i)))
				return true;
		}
		return false;
	}

	public static boolean isInsideZoneMove(Coordinate coord, List<Move> zone) {
		for (int i = 0; i < zone.size(); i++) {
			if (coord.equals(zone.get(i).getDestination()))
				return true;
		}
		return false;
	}

	public static ArrayList<Move> getBoxLegalMove(int[] board, int player, int boxIndex) {
		ArrayList<Move> legalMoves = new ArrayList<>();
		if (board[boxIndex] == player) {
			Coordinate coord = new Coordinate(boxIndex);
			Move move = new Move(coord, player, coord.getN(player));

			if (isLegal(move, board, player)) {
				legalMoves.add(move);
				if(Utils.enemyAhead(coord, board, player)){
					return legalMoves;
				}
			}
			move = new Move(coord, player, coord.getNE(player));
			if (isLegal(move, board, player)) {
				legalMoves.add(move);
			}
			move = new Move(coord, player, coord.getNW(player));
			if (isLegal(move, board, player)) {
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}

	public static boolean isEndGame(int[] tabValue) {

		for (int i = 0; i < 8; i++) {
			if (tabValue[i] == 1) {
			//	System.out.println("White win");
			//	printState(tabValue);
				return true;
			}
		}
		for (int i = 56; i < tabValue.length; i++) {
			if (tabValue[i] == -1) {
			//	System.out.println("Black win");
			//	printState(tabValue);
				return true;
			}
		}
		Boolean foundW = false;
		Boolean foundB = false;
		for(int i = 0; i< tabValue.length; i++){
			if (tabValue[i] == -1) foundB = true;
			else
			if (tabValue[i] == 1) foundW = true;
			if (foundW && foundB) return false;
		}
		return true;
	}

	public static void printState(int[] board) {
		for (int i = 0; i < board.length; i++) {
			if (i % 8 == 0) {
				System.out.print("\n| ");
			}
			if (board[i] < 0) {
				System.out.print(Integer.valueOf(board[i]) + "| ");
			} else {
				System.out.print(Integer.valueOf(board[i]) + " | ");
			}
		}
		System.out.print("\n_____________\n");
	}

	/**
	 * generate the tokens on the board
	 */
	public static int[] generateBoard() {
		int[] tabValue = new int[64];
		for (int i = 0; i < tabValue.length; i++) {
			tabValue[i] = 0;
			if ((Math.abs((i - ((tabValue.length + 1) / 2)))) >= 16) {
				if (i <= 15)
					tabValue[i] = -1;
				else if (i > 16)
					tabValue[i] = 1;
			}
		}
		return tabValue;
	}
	
	public static int[] copyBoard(int[] toCopy){
		int[] copy = new int[toCopy.length];
		for(int i =0; i < copy.length; i++){
			copy[i] = toCopy[i];
		}
		return copy;
		
	}

}
