package up5.poo.breaktrough;

import java.util.ArrayList;
import java.util.List;

public class Node implements Cloneable {

	private ArrayList<Node> children;
	private int[] state;
	private Move moveToPlay;
	private Node parent;
	private int currentPlayerValue;
	private boolean isTerminal;
	private int score; // used as the number of win in case of MCTS

	private int numberOfVisit;

	/**
	 * Create root node
	 */
	Node() {
		score = 0;
		this.state = Utils.generateBoard();
		isTerminal = Utils.isEndGame(state);
		parent = null;
		children = new ArrayList<>();
		moveToPlay = null;
		currentPlayerValue = 1; // White plays first

		numberOfVisit = 0; // For MCTS
	}

	/**
	 * Create a new node, using the state of parent and play the given move to
	 * get to his child state
	 * 
	 * @param parent
	 *            the parent node of the new one
	 * @param moveToPlay
	 *            the move to get to the state of the created node
	 */
	Node(Node parent, Move moveToPlay) {
		score = 0;
		this.currentPlayerValue = parent.getPlayer() == 1 ? -1 : 1;
		this.moveToPlay = moveToPlay;
		if (moveToPlay != null) {
			state = Utils.play(moveToPlay, parent.getState().clone());
		} else {
			state = parent.getState().clone();
		}
		this.parent = parent;
		isTerminal = Utils.isEndGame(state);
		children = new ArrayList<>();
	}
	
	Node(int[] state, int currentPlayer){
		score = 0;
		currentPlayerValue = currentPlayer;
		moveToPlay = null;
		this.state = state.clone();
		//if it gets created with this constructor, it is not terminal
		isTerminal = false;
		children = new ArrayList<>();
		
	}

	/**
	 * Create a node by copy
	 * @param node
	 */
	Node(Node node) { // Clone
		this.score = node.score;
		this.currentPlayerValue = node.currentPlayerValue;
		this.parent = null;
		this.state = Utils.copyBoard(node.getState());
		isTerminal = Utils.isEndGame(state);
		this.moveToPlay = new Move(node.getMove().getPosition(), currentPlayerValue, node.getMove().getDestination());
		this.children = new ArrayList<>();
		this.numberOfVisit = node.numberOfVisit;
	}

	public void createChildren() {
		ArrayList<Move> legalMoves = Utils.getPlayerLegalMoves(state, currentPlayerValue);
		for (int i = 0; i < legalMoves.size(); i++) {
			children.add(new Node(this, legalMoves.get(i)));
		}
	}
	
	public Node expandOneChild() {
		ArrayList<Move> legalMoves = Utils.getPlayerLegalMoves(state, currentPlayerValue);

		Move firstNotPlayedMove = legalMoves.get(children.size());
		Node child = new Node(this, firstNotPlayedMove);
		this.children.add(child);
		return child;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void clearChildren() {
		int childsize = children.size();
		for (int i = 0; i<childsize; i++){
			children.remove(0);
		}
	}

	public boolean isTerminal() {
		return this.isTerminal;
	}

	public void updateScore(int i) {
		score += i;
	}

	public int getScore() {
		return score;
	}

	/***
	 * Get the value of the current player of this node
	 * 
	 * @return 1 for white, -1 for black
	 */
	public int getPlayer() {
		return currentPlayerValue;
	}

	public int[] getState() {
		return state;
	}

	public String toString() {
		Utils.printState(state);
		String string = "";
		if (children != null)
			string += "number of childs = " + children.size();
		else
			string += "child null";
		// string += "\number of Moves played = " + (movesPlayed.size()!=0 ?
		// movesPlayed.size() : 0);
		if (moveToPlay != null)
			string += "\nmove to play from :" + moveToPlay.getPosition().getIndex() + " to "
					+ moveToPlay.getDestination().getIndex();
		else
			string += "\nmoveToPlay is null";
		string += "\ncurrentPlayerValue =" + currentPlayerValue;
		if (isTerminal)
			string += "And he his terminal";
		string += "\nhis score = " + score;
		return string;
	}

	public Move getMove() {
		return moveToPlay;
	}

	public void addChildren(Node child) {
		children.add(child);
	}



	public void visitNode() {
		this.numberOfVisit++;
	}

	public int getNbVisit() {
		return numberOfVisit;
	}

	public Node getParent() {
		return this.parent;
	}

	public void PrintTree(String indentation, Boolean last) {
		System.out.print(indentation);

		if (last) {
			System.out.print("\\-");
			indentation += "  ";
		} else {
			System.out.print("| ");
			indentation += "| ";
		}
		System.out.println("("+this.score + "," + this.numberOfVisit + ")");
		for (int i = 0; i < children.size(); i++) {
			children.get(i).PrintTree(indentation, i == children.size() - 1);
		}
	}

}
