package up5.poo.breaktrough;


public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Generate a coordinate by index
	 * @param index the index of the box
	 */
	public Coordinate (int index)
	{
		x = index % 8;
		y = 0;
		for(int i =8; i <= index; i+=8){
			y++;
		}
	}
	
	/**get x index of the coordinate
	@return x index*/
	public int getX(){
		return x;
	}
	/**get y index of the coordinate
	@return y index*/		
	public int getY(){
		return y;
	}
	/**get the index of the coordinate, line per line
	@return the index of the coordinate*/
	public int getIndex(){
		return 8*y+x;
	}


	public Coordinate getN(int color) {
		return new Coordinate(x, y-color);	
	}
	
	public Coordinate getNW(int color) {
		return new Coordinate(x-color,y- color);	
	}
	
	public Coordinate getNE(int color) {
		return new Coordinate(x+color, y-color); 
	}
	
	public boolean isInsideBorder(){
		return ( ( x<8 && x>=0 && y< 8 && y>=0) ? true : false);
		
	}
	
	public boolean equals(Coordinate toTest){
		return ( (this.x == toTest.getX() && this.y == toTest.getY()) ? true : false);
	}
	
	@Override
	public String toString(){
		return "[x = " + x + "y = " + y;
		
	}
}