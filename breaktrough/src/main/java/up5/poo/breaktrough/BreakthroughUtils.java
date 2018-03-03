package up5.poo.breaktrough;
/**All the information that dont belong in a class of the game but is still necessary
 */
public class BreakthroughUtils {
	/**Represent where the pawn is on the board
	 */
	public class Coordinate {

		private int x,y;

		Coordinate(int x, int y){
			this.x = x;
			this.y = y;
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
}
