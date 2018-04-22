package up5.poo.breaktrough;

import java.util.List;

public class AIHandler extends Player {

	Move moveToPlay;
	GameManager gm;

	AIHandler(int couleur, GameManager gm) {
		super(couleur, PlayerType.AI);
		this.gm = gm;
		moveToPlay = null;
	}

	public Move searchMove(List<Move> movesPlayed) throws SimulationException {
		Node root = new Node();
		Node currentNode = new Node(root);

		if (!movesPlayed.isEmpty()) {
			for (int i = 0; i < movesPlayed.size(); i++) {
				// System.out.println("oui" + currentNode.getChildren());
				currentNode.addChildren(new Node(currentNode.getState(), movesPlayed.get(i), currentNode.getPlayer()));
				currentNode = currentNode.getChildren().get(0);
			}
		}
		currentNode.createChildren();
		Node actualNode = new Node(currentNode);
		actualNode.createChildren();
		// System.out.println("taille nb gosse" +
		// actualNode.getChildren().size());
		for (int i = 0; i < actualNode.getChildren().size(); i++) {
			// System.out.println("boucle de chaque enfant");

			currentNode = actualNode.getChildren().get(i);
			for (int j = 0; j < 250; j++) {
				//System.out.println("boucle de test n°" + j);
				currentNode = actualNode.getChildren().get(i);
				actualNode.getChildren().get(i).updateScore(simulateMove(currentNode));
			}
		}
		int selectedChildIndex = 0;
		switch (this.getValue()){
		case 1:
			for (int i = 0; i < actualNode.getChildren().size(); i++) {
				System.out.println("\n score :" + actualNode.getChildren().get(i).getScore());
				if (actualNode.getChildren().get(i).getScore() > actualNode.getChildren().get(selectedChildIndex).getScore()){
					selectedChildIndex = i;
				}
			}
			break;
		case -1:
			for (int i = 0; i < actualNode.getChildren().size(); i++) {
				System.out.println("\n score :" + actualNode.getChildren().get(i).getScore());
				if (actualNode.getChildren().get(i).getScore() < actualNode.getChildren().get(selectedChildIndex).getScore()){
					selectedChildIndex = i;
				}
			}
		}
		
		// Utils.printState(actualNode.getChildren().get(selectedChildIndex).getState());
		Move foundMove = actualNode.getChildren().get(selectedChildIndex).getMove();
		System.out.println("fin searchMove");
		System.out.println("score du noeud selectionné" + actualNode.getChildren().get(selectedChildIndex).getScore());
		
		return foundMove;

	}

	public int simulateMove(Node currentNode) throws SimulationException {
		java.util.Random rand = new java.util.Random();
		while (!currentNode.isTerminal()) {
			// System.out.println("iteration du coup");
			currentNode.createChildren();
			if (!currentNode.getChildren().isEmpty()) {
				int random = rand.nextInt(currentNode.getChildren().size());
				Node selectedChild = currentNode.getChildren().get(random);
				currentNode.clearChildren();
				currentNode.getChildren().add(selectedChild);
				currentNode = selectedChild;
			} else {
				if(!currentNode.isTerminal()){
				Utils.printState(currentNode.getState());
				throw new SimulationException("No childrens in non terminal Node");
				} else {
					break;
				}
			}
		}
		//Utils.printState(currentNode.getState());
		return -currentNode.getPlayer(); //If the node is terminal, the last player won
	}

}
