package up5.poo.breaktrough;

import up5.poo.breaktrough.BreakthroughUtils.Coordinate;
/**
 * 
 * a piece in the game
 *
 */
public class Pawn {
	/** 1 for WHITE, -1 for BLACK*/
	private int owner;
	private Coordinate coord;
	private boolean captured;
	
	Pawn(int owner, Coordinate coord){
		this.owner = owner;
		this.coord = coord;
		captured = false;
	}
	
	public int getOwner(){
		return owner;
	}
	
	public boolean isCaptured(){
		return captured;
	}
	
	public Coordinate getCoordinate(){
		return coord;
	}

}
