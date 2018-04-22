package up5.poo.breaktrough;


import up5.poo.breaktrough.Coordinate;

/**
 * a Move is the action pawn can do
 */
public class Move {
	
	
	private Coordinate position;
	private Coordinate destination; 
	private int color;
	
	
	Move(Coordinate position,int owner, Coordinate destination){
		this.position = position;
		this.destination = destination;
		color = owner;		
	}
	
	public Coordinate getPosition(){
		return position;
	}
	
	public Coordinate getDestination(){
		return destination;
	}
	
	/**
	 * the gameColor of the pawn that perform that move
	 * @return 1 if the pawn is White, -1 if the pawn is Black
	 */
	public int getColor(){
		return color;
	}
	

}
