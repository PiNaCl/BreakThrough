package up5.poo.breaktrough;


public class TreeMC {
	private Node root;
	private double explCst;
	
	TreeMC(Node root, double exploration) {
		this.root = root;
		explCst = exploration;
	}

	public void backPropagation(Node currentNode, int playerThatWon) {
		while (currentNode.getParent() != null) {
			currentNode.visitNode();
			currentNode.updateScore(playerThatWon);
			currentNode = currentNode.getParent();
		}
	}

	/**
	 * Choose a children, All children must have been created prior to use this
	 * method
	 * 
	 * @param explorationConstant
	 * @return the selectedChild
	 */
	public Node selectChild(int totalSim) {
		int selectedChildIndex = 0;
		double bestScore = 0;
		double scoreTmp = 0;
		Node currentNode = root;
		while (currentNode.getChildren().size() == Utils.getPlayerLegalMoves(currentNode.getState(), currentNode.getPlayer()).size()) {
			for (int i = 0; i < currentNode.getChildren().size(); i++) {
				Node child = currentNode.getChildren().get(i);
				scoreTmp = ( (double) child.getScore()*currentNode.getPlayer() / (double) child.getNbVisit())
						+  explCst * Math.sqrt(Math.log((double) totalSim/ (double)child.getNbVisit()));
				if ( scoreTmp > bestScore) {
					selectedChildIndex = i;
					bestScore = scoreTmp;
				}
			}
			if(currentNode.getChildren().size() == Utils.getPlayerLegalMoves(currentNode.getState(), currentNode.getPlayer()).size() && !currentNode.getChildren().isEmpty())
				currentNode = currentNode.getChildren().get(selectedChildIndex);
			selectedChildIndex = 0;
		}
		return currentNode;
	}

}
