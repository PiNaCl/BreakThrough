package up5.poo.breaktrough;
/**Represent a player, and it's information, can be human or AI
 */
public class Player {
	
public enum PlayerType { HUMAN, AI }
	
public enum Color {WHITE, BLACK}

private Color color;
private PlayerType type;
private Pawn[] tokens;
private int nbToken;
private Move moveToPlay;

Player(Color couleur) {
	nbToken = 16;
	tokens = new Pawn[16];
	this.color = couleur;
	moveToPlay = null;
	}

public int getColor(){
	if ( color == Color.WHITE) return 1;
	else return -1;
}

public void setMove(Move move){
	
}

}
