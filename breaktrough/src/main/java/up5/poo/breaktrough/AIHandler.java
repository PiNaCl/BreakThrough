package up5.poo.breaktrough;

import java.util.List;

public class AIHandler extends Player {

	Move moveToPlay;
	GameManager gm;
	java.util.Random rand;
	double explRate;
	AIHandler(int couleur, GameManager gm, double explorationRate) {
		super(couleur, PlayerType.AI);
		this.gm = gm;
		moveToPlay = null;
		rand = new java.util.Random();
		explRate = explorationRate;
	}
	
	/**
	 * @param movesPlayed
	 * @return
	 * @throws SimulationException
	 */
	public Move searchMove(List<Move> movesPlayed) throws SimulationException {
		Node root = new Node(gm.getBoard().getTab(), gm.getPlayerValue());
		Node currentNode = new Node(root);
		currentNode.createChildren();
		Node actualNode = new Node(currentNode);
		actualNode.createChildren();
		for (int i = 0; i < actualNode.getChildren().size(); i++) {

		//	currentNode = actualNode.getChildren().get(i);
			for (int j = 0; j < 500; j++) {
				currentNode = actualNode.getChildren().get(i);
				actualNode.getChildren().get(i).updateScore(simulateMove(currentNode));
			}
		}
		int selectedChildIndex = 0;
		if (this.getValue() == 1) { // White player
			for (int i = 0; i < actualNode.getChildren().size(); i++) {
				System.out.println("\n score :" + actualNode.getChildren().get(i).getScore());
				if (actualNode.getChildren().get(i).getScore() > actualNode.getChildren().get(selectedChildIndex)
						.getScore()) {
					selectedChildIndex = i;
				}
			}
		} else { // black player
			for (int i = 0; i < actualNode.getChildren().size(); i++) {
				System.out.println("\n score :" + actualNode.getChildren().get(i).getScore());
				if (actualNode.getChildren().get(i).getScore() < actualNode.getChildren().get(selectedChildIndex)
						.getScore()) {
					selectedChildIndex = i;
				}
			}
		}
		Move foundMove = actualNode.getChildren().get(selectedChildIndex).getMove();
		//System.out.println("score du noeud selectionné" + actualNode.getChildren().get(selectedChildIndex).getScore());

		return foundMove;

	}

	/**
	 * Simulation of a move, plays randomly until a player wins
	 * 
	 * @param currentNode
	 *            the starting node of the simulation
	 * @return 1 if white win, -1 if black wins
	 * @throws SimulationException
	 */
	public int simulateMove(Node startingNode) throws SimulationException {
		Node currentNode = new Node(startingNode); //Clone the node
		while (!currentNode.isTerminal()) {
			currentNode.createChildren();
			if (!currentNode.getChildren().isEmpty()) {
				int random = rand.nextInt(currentNode.getChildren().size());
				Node selectedChild = currentNode.getChildren().get(random);
				currentNode.clearChildren();
				currentNode.getChildren().add(selectedChild);
				currentNode = selectedChild;
			} else {
				if (!currentNode.isTerminal()) {
					throw new SimulationException("No children in a non terminal Node");
				} else {
					break;
				}
			}
		}
		return -currentNode.getPlayer(); // If the node is terminal, the last
											// player won, 1 : White, -1 : Black
	}
	
	/**
	 * new search method, uses MonteCarlo Tree Search
	 * @return the move to get to the most visited Node
	 * @throws SimulationException 
	 */
	public Move monteCarloTreeSearch() throws SimulationException {
	    long begin = System.currentTimeMillis();
		int numberOfSimulation = 0;
		Node root = new Node(gm.getBoard().getTab(), gm.getPlayerValue());
		root.createChildren();
		for (int i = 0; i < root.getChildren().size(); i++){
			 if(root.getChildren().get(i).isTerminal()){
				//System.out.println("finisher move found");
				return root.getChildren().get(i).getMove();
			}	
		}	
		root.clearChildren();
		Node currentNode = null;
		TreeMC tree = new TreeMC(root, explRate);
		while (ressourceAvailable(begin)){
			
			currentNode = tree.selectChild(numberOfSimulation);
			currentNode = currentNode.expandOneChild();
			int hasWon = simulateMove(currentNode);
			tree.backPropagation(currentNode, hasWon);
			numberOfSimulation++;
		}
		int mostVisited = 0;
		int bestChildIndex = 0;
		for(int i =0; i < root.getChildren().size(); i++){
			if (root.getChildren().get(i).getNbVisit() > mostVisited) {
				bestChildIndex = i;
				mostVisited = root.getChildren().get(i).getNbVisit(); 
			}
		}
		//System.out.println(numberOfSimulation+" simulations made, score of the node : "+root.getChildren().get(bestChildIndex).getScore()+ " it was visited "+root.getChildren().get(bestChildIndex).getNbVisit()+" times");
		return root.getChildren().get(bestChildIndex).getMove();
		
	}
	
	/**
	 *	
	 * @param begin The time where the search started
	 * @return true if there is time left
	 */
	public Boolean ressourceAvailable(long begin){
		return System.currentTimeMillis() - 2500 > begin ? false : true;
	}


}
