package up5.poo.breaktrough;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import up5.poo.breaktrough.Player.PlayerType;

public class GameManager extends Thread{

	private Player[] players;
	private int currentPlayerIndex;
	private Boolean selected;
	Boolean isReadyToPlay = false;
	private int selectedTokenIndex;
	private Board board;
	private GameMode mode;
	private ArrayList<Move> movesPlayed;

	@Override
	public void run (){
	/*	try {
			Thread.sleep(500);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
*/
		
		switch (mode) {
		case HUMAN:
			players[0] = new Player(1, this);
			players[1] = new Player(-1, this);
			break;
		case AIH:
			players[0] = new Player(1, this);
			players[1] = new AIHandler(-1,this);
			break;
		case AI:
			players[0] = new AIHandler(1,this);
			players[1] = new AIHandler(-1,this);
			break;
		default:
			players[0] = new Player(1,this);
			players[1] = new Player(-1,this);
		}		
		try {
			gameLoop();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	GameManager(GameMode mode) {
		selected = false;
		players = new Player[2];
		currentPlayerIndex = 0;
		movesPlayed = new ArrayList<>();
		isReadyToPlay = false;
		this.mode = mode;
		this.board = new Board(this);
	}
	
	public void gameLoop() throws InterruptedException{
		while (!Utils.isEndGame(board.getTab())) {
			try{
				Thread.sleep(100);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			if (players[currentPlayerIndex].getMove() != null) {
				nextTurn();
			} else {
				if (players[currentPlayerIndex].getPlayerType() == PlayerType.AI){
					try {
					setPlayerMove(getAI(currentPlayerIndex).searchMove(movesPlayed));
					} catch (SimulationException e) {
						System.out.println("Broken AI");
					}
				}
			}
		}

	}

	public void setBoard(Board board){
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
	 * @throws InterruptedException 
	 */
	public void nextTurn() {
		Move moveToPlay = players[currentPlayerIndex].getMove();
		board.setTab(Utils.play(moveToPlay, board.getTab()));
		updateBoard(moveToPlay);
		movesPlayed.add(moveToPlay);
		switchPlayerIndex();
		setPlayerMove(null);
	}
}
