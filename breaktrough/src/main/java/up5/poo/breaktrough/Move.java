package up5.poo.breaktrough;

import up5.poo.breaktrough.BreakthroughUtils.Coordinate;

public class Move {
	
	
	private Coordinate position;
	private Coordinate destination; 
	private int color;
	
	Move(Pawn token, int owner, Coordinate destination){
		this.position = token.getCoordinate();
		this.destination = destination;
		color = owner;		
	}
	
	
	
	public Coordinate getPosition(){
		return position;
	}
	
	public Coordinate getDestination(){
		return destination;
	}
	
	public int getColor(){
		return color;
	}
	
	public boolean isLegal(){
		boolean legal = true;
		//TO IMPLEMENT
		System.out.println("isLegalNotImplemented yet, always return true");
		return legal;
	}

}
