package up5.poo.breaktrough;

/**
 * Represent a player, and it's information, can be human or AI
 */
public class Player {

	public enum PlayerType {
		HUMAN, AI
	}

	public enum Color {
		WHITE, BLACK
	}

	private int value;
	private PlayerType type;
	private Move moveToPlay;
	private GameManager gm;

	Player(int value, GameManager gm) {
		this.gm = gm;
		this.value = value; // 1 = white / 0 = black
		this.type = PlayerType.HUMAN;
		moveToPlay = null;
	}
	
	Player(int value, PlayerType type){
		this.value = value;
		this.type = type;
		moveToPlay = null;		
	}

	public int getValue() {
		return value;
	}

	public void setMove(Move move) {
		moveToPlay = move;
	}
	
	public Move getMove(){
		return moveToPlay;
	}
	
	public PlayerType getPlayerType(){
		return type;
	}

}
