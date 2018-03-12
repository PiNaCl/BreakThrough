package up5.poo.breaktrough;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
	private Color paint;
	private Coordinate coordinate;
	private Pawn pawn;
	
	
	public Cell(Color color,Coordinate coord, Pawn pawn){
		
	
		
		this.pawn = pawn;
		
		coordinate = coord;
		paint = color;
		Rectangle box = new Rectangle(50d,50d);
		Rectangle highlight = new Rectangle(29d,29d);
		box.setFill(paint);
		getChildren().add(box);
		if ( pawn != null){
			Circle circle = new Circle(14);

			if(pawn.getOwner() ==1) {
				circle.setFill(Color.WHITE);
				circle.setStroke(Color.BLACK);

			}
			else {
				circle.setFill(Color.BLACK);
				circle.setStroke(Color.WHITE);
			}
			
			getChildren().add(circle);
		}
	}
	
	public void setPawn(Pawn pawn){
		this.pawn = pawn;
	}

}
	
