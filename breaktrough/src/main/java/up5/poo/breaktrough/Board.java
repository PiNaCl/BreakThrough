package up5.poo.breaktrough;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import up5.poo.breaktrough.Coordinate;

public class Board extends GridPane {

	private Cell[] boxes;
	private GameManager gm;
	private int[] tabValue;

	
	public Board(GameManager gm) {
		super();
		this.gm = gm;
		this.gm.setBoard(this);
		tabValue = Utils.generateBoard();
		boxes = new Cell[64];
		Boolean isWhite = false;
		Color color;
		for (int i = 0; i < tabValue.length; i++) {
			if (i % 8 == 0) {
				isWhite = !isWhite;
			}
			if (isWhite) {
				color = Color.WHEAT;

			} else {
				color = Color.BROWN;
			}
			Coordinate coord = new Coordinate(i);
			isWhite = !isWhite;
			
			Cell cell = new Cell(color, coord, tabValue[i], gm);
			add(cell, coord.getX(), coord.getY());
			boxes[i] = cell;
		}
	}

	public int[] getTab(){
		return tabValue;
	}
	
	public int getTabValue(int i) {
		return tabValue[i];
	}

	public void setTab(int[] newState) {
		this.tabValue = newState;
	}

	public void updateBoard(Move move) {
		Cell child = (Cell) getChildren().get(move.getPosition().getIndex());
		child.hideToken();
		child = (Cell) getChildren().get(move.getDestination().getIndex());
		child.showToken(move.getColor() == 1 ? true : false);
	}
}