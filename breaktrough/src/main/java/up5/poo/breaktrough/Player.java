package up5.poo.breaktrough;

public class Player {
	
public static final int WHITE = 0;
public static final int BLACK = 1;

private int couleur;
private Pawn[] pieces;

Player(int couleur){
	this.couleur = couleur;
	this.pieces = new Pawn[16];
	
}

}
