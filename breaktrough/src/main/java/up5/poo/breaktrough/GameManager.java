package up5.poo.breaktrough;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import up5.poo.breaktrough.Player.PlayerType;

public class GameManager extends Thread {

	private Player[] players;
	private int currentPlayerIndex;
	private Boolean selected;
	Boolean isReadyToPlay = false;
	private int selectedTokenIndex;
	private Board board;
	private GameMode mode;
	private ArrayList<Move> movesPlayed;
	private Label info;
	private double eRAI1;
	private double eRAI2;

	GameManager(GameMode mode, double explorationRateAI1, double explorationRateAI2) {
		selected = false;
		eRAI1 = explorationRateAI1;
		eRAI2 = explorationRateAI2;

		players = new Player[2];
		currentPlayerIndex = 0;
		movesPlayed = new ArrayList<>();
		isReadyToPlay = false;
		this.mode = mode;
		this.board = new Board(this);
		info = new Label("Turn n°" + movesPlayed.size() + 1 + "it's White's turn");
	}

	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (mode) {
		case HUMAN:
			players[0] = new Player(1, this);
			players[1] = new Player(-1, this);
			break;
		case AIH:
			players[0] = new Player(1, this);
			players[1] = new AIHandler(-1, this, eRAI1);
			break;
		case AI:
			players[0] = new AIHandler(1, this, eRAI1);
			players[1] = new AIHandler(-1, this, eRAI2);
			break;
		default:
			players[0] = new Player(1, this);
			players[1] = new Player(-1, this);
		}
		try {
			gameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void gameLoop() throws InterruptedException {
		while (!Utils.isEndGame(board.getTab())) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (players[currentPlayerIndex].getMove() != null) {
				nextTurn();
			} else {
				if (players[currentPlayerIndex].getPlayerType() == PlayerType.AI) {
					try {
						setPlayerMove(getAI(currentPlayerIndex).monteCarloTreeSearch());
					} catch (SimulationException e) {
						System.out.println("Broken AI");
					}
				}
			}
		}
		Thread.sleep(500);
		if (currentPlayerIndex == 0)
			info.setText("Blacks win");
		else
			info.setText("White wins");
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getPlayer(int index) {
		return players[index];
	}

	public AIHandler getAI(int index) {
		return (AIHandler) players[index];
	}

	public void switchPlayerIndex() {
		if (currentPlayerIndex == 0)
			this.currentPlayerIndex = 1;
		else
			this.currentPlayerIndex = 0;
	}

	public void selectToken(int index) {
		selectedTokenIndex = index;
		selected = true;
	}

	public void deselectToken() {
		selected = false;
	}

	public Boolean hasSelectedToken() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public int getSelectedTokenIndex() {
		return selectedTokenIndex;
	}

	public void setSelectedTokenIndex(int selectedTokenIndex) {
		this.selectedTokenIndex = selectedTokenIndex;
	}

	public Board getBoard() {
		return board;
	}

	public void updateBoard(Move move) {
		Platform.runLater(() -> board.updateBoard(move));
	}

	public void updateBoard() {
		Platform.runLater(() -> board.updateBoard());
	}

	public int getPlayerValue() {
		return currentPlayerIndex == 0 ? 1 : -1;
	}

	public int getPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setPlayerMove(Move move) {
		players[currentPlayerIndex].setMove(move);
	}

	public void readyToPlay() {
		isReadyToPlay = true;
	}

	public enum GameMode {
		AIH, HUMAN, AI
	}

	public GameMode getMode() {
		return mode;
	}

	public List<Move> getMovesPlayed() {
		return movesPlayed;
	}

	/**
	 * Play the move, change the current player, animate the move
	 * 
	 * @throws InterruptedException
	 */
	public void nextTurn() {
		Move moveToPlay = players[currentPlayerIndex].getMove();
		board.setTab(Utils.play(moveToPlay, board.getTab()));
		updateBoard(moveToPlay);
		movesPlayed.add(moveToPlay);
		switchPlayerIndex();
		setPlayerMove(null);
		if (currentPlayerIndex == 0)
			Platform.runLater(() -> info.setText("Turn n°" + (1 + movesPlayed.size()) + " | It's White's turn"));
		else
			Platform.runLater(() -> info.setText("Turn n°" + (1 + movesPlayed.size()) + " | It's Black's turn"));
	}

	/**
	 * getter for turn information, to be moved to the panel
	 * @return
	 */
	public Label getInfo() {
		return info;
	}

	/**
	 * setter for information, to be moved to the panel
	 * @param info
	 */
	public void setInfo(Label info) {
		this.info = info;
	}
	
	/**
	 * reInit the game, with possibly a different mode, to implements
	 * @param mode
	 */
	public void reInit(GameMode mode){
		currentPlayerIndex = 0;
		movesPlayed = new ArrayList<>();
		isReadyToPlay = false;
		this.mode = mode;
		this.board.setTab(Utils.generateBoard());
		this.updateBoard();
		switch (this.mode) {
		case HUMAN:
			players[0] = new Player(1, this);
			players[1] = new Player(-1, this);
			break;
		case AIH:
			players[0] = new Player(1, this);
			players[1] = new AIHandler(-1, this, eRAI1);
			break;
		case AI:
			players[0] = new AIHandler(1, this, eRAI1);
			players[1] = new AIHandler(-1, this, eRAI2);
			break;
		default:
			players[0] = new Player(1, this);
			players[1] = new Player(-1, this);
		}
		try {
			gameLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
