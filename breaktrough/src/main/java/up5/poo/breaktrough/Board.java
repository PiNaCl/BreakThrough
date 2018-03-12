package up5.poo.breaktrough;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import up5.poo.breaktrough.Coordinate;


public class Board extends GridPane{
	
	private Boolean isWhite;
	public GameManager gm;
	
	public Board(GameManager gm){
		
		this.gm = gm;
		isWhite = false;
		Color color;
		for(int i=0; i< gm.getBoard().length; i++){
			if(i%8==0){
				isWhite = !isWhite;
			}
			if(isWhite){
				color = Color.WHEAT;
				
			} else {
				color = Color.BROWN;
			}
			Coordinate coord = new Coordinate(i);

			if(gm.getBoardValue(i) ==0){
			
				isWhite = !isWhite;
				add(new Cell ( color, coord, null), coord.getX(), coord.getY() );
			}
			else if (gm.getBoardValue(i)==1){
				isWhite = !isWhite;

				add(new Cell ( color, coord, new Pawn(1, coord)), coord.getX(), coord.getY() );
			}
			else {
				isWhite = !isWhite;
					add(new Cell ( color, coord,  new Pawn(-1, coord)), coord.getX(), coord.getY() );
			}
		}		
	}
	
	
}