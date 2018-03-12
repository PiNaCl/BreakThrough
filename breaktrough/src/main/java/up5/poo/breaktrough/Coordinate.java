package up5.poo.breaktrough;
public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinate (int index)
	{
		x = index % 8;
		y = 0;
		for(int i =8; i <= index; i+=8){
			y++;
			//System.out.println(index);
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
	
}