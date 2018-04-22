package up5.poo.breaktrough;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private ArrayList<Node> children;
	private ArrayList<Move> movesPlayed;
	private int[] state;
	private Move moveToPlay;
	private int currentPlayerValue;
	private boolean isTerminal;
	private int score;

	Node() {
		movesPlayed = new ArrayList<>();
		score = 0;
		this.state = Utils.generateBoard();
		isTerminal = Utils.isEndGame(state);

		children = new ArrayList<>();
		moveToPlay = null;
		currentPlayerValue = 1;
	}

	Node(int[] oldState, Move moveToPlay, int currentPlayer) {
		score = 0;
		this.currentPlayerValue = -currentPlayer;
		this.moveToPlay = moveToPlay;
		if(moveToPlay !=null){
		state = Utils.play(moveToPlay, oldState);
		} else {
			state = oldState.clone();
		}
		isTerminal = Utils.isEndGame(state);
		children = new ArrayList<>();
	}
	
	Node(Node node){
		this.score= node.score;
		this.currentPlayerValue = node.currentPlayerValue;
		this.state = node.state.clone();
		isTerminal = Utils.isEndGame(state);
		this.moveToPlay = node.moveToPlay;
		this.children = new ArrayList<>();
	}

	public void createChildren() {
		ArrayList<Move> legalMoves = Utils.getPlayerLegalMoves(state, currentPlayerValue);
		//System.out.println("nombres d'enfant ="+legalMoves.size());
		for (int i = 0; i < legalMoves.size(); i++) {
			addChildren(new Node(this.state.clone(), legalMoves.get(i), this.currentPlayerValue));
		}
	}

	public List<Node> getChildren() {
		return children;
	}

	public void clearChildren() {
		this.children = new ArrayList<>();
	}

	public boolean isTerminal() {
		return Utils.isEndGame(state);
	}

	public void updateScore(int i) {
		score+=i;
	}

	public int getScore() {
		return score;
	}

	/***
	 * Get the value of the current player of this node 
	 * @return 1 for white, -1 for black
	 */
	public int getPlayer() {
		return currentPlayerValue;
	}
	
	public int[] getState(){
		return state;
	}
	
	public String toString(){
		Utils.printState(state);
		String string ="";
		if (children != null)
		string += "number of childs = "+ children.size();
		else string +="child null";
		string += "\number of Moves played = " + movesPlayed.size();
		if(moveToPlay != null)
		string += "\nmove to play from :" + moveToPlay.getPosition().getIndex()+" to "+ moveToPlay.getDestination().getIndex();
		else string +="\nmoveToPlay is null";
		string += "\ncurrentPlayerValue =" + currentPlayerValue;
		if(isTerminal) string +="And he his terminal";
		string+= "\nhis score = " + score;
		return string;
	}
	
	public Move getMove(){
		return moveToPlay;
	}
	
	public void addChildren(Node child){
		children.add(child);
	}
}
