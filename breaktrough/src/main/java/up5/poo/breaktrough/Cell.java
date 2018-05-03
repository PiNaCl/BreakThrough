package up5.poo.breaktrough;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import up5.poo.breaktrough.Player.PlayerType;

public class Cell extends StackPane {

	public static final Color green = new Color(0.5, 1, 0, 0.5);
	public static final Color red = new Color(0.5, 1, 0, 0.5);

	private GameManager gm;
	private Color paint;
	private Coordinate coordinate;
	private Rectangle box;
	private Rectangle highlight;
	private Circle white;
	private Circle black;
	private boolean occuped;
	private int value;

	public Cell(Color color, Coordinate coord, int pawn, GameManager gameManager) {

		gm = gameManager;
		coordinate = coord;
		paint = color;
		highlight = new Rectangle(50d, 50d);
		box = new Rectangle(50d, 50d);
		box.setFill(paint);
		getChildren().add(box);
		getChildren().add(highlight);
		disHighlight();
		white = new Circle(14);
		black = new Circle(14);
		value = pawn;

		white.setFill(Color.WHITE);
		white.setStroke(Color.BLACK);

		black.setFill(Color.BLACK);
		black.setStroke(Color.WHITE);

		getChildren().add(white);
		getChildren().add(black);
		if (value == 0) {
			occuped = false;
			white.toBack();
			black.toBack();
		} else if (value == 1) {
			black.toBack();
			occuped = true;
		} else {
			white.toBack();
			occuped = true;
		}

		this.setOnMouseEntered(event -> {
			if (!Utils.isEndGame(gm.getBoard().getTab())) {
				if (occuped && !gm.hasSelectedToken()) {
					Move move = new Move(coordinate, value, coordinate.getN(value));
					if (Utils.isLegal(move, gm.getBoard().getTab(), gm.getPlayerValue())) {
						Cell cell = (Cell) gm.getBoard().getChildren().get(coordinate.getIndex() + 8 * value * -1);
						cell.highlight(green);
					}
					if (!Utils.enemyAhead(move.getPosition(), gm.getBoard().getTab(), value)) {
						move = new Move(coordinate, value, coordinate.getNW(value));
						if (Utils.isLegal(move, gm.getBoard().getTab(), gm.getPlayerValue())) {
							Cell cell = (Cell) gm.getBoard().getChildren().get(coordinate.getIndex() + 9 * value * -1);
							cell.highlight(green);
						}
						move = new Move(coordinate, value, coordinate.getNE(value));
						if (Utils.isLegal(move, gm.getBoard().getTab(), gm.getPlayerValue()) ) {
							Cell cell = (Cell) gm.getBoard().getChildren().get(coordinate.getIndex() + 7 * value * -1);
							cell.highlight(green);
						}
					}
				}
			}
		});
		this.setOnMouseExited(event -> {
			if (!Utils.isEndGame(gm.getBoard().getTab())) {

				if (occuped && !gm.hasSelectedToken()) {
					Move moveN = new Move(coordinate, value, coordinate.getN(value));
					if (Utils.isLegal(moveN, gm.getBoard().getTab(), gm.getPlayerValue())) {
						Cell cell = (Cell) gm.getBoard().getChildren().get(coordinate.getIndex() + 8 * value * -1);
						cell.disHighlight();
					}
					Move move = new Move(coordinate, value, coordinate.getNW(value));
					if (Utils.isLegal(move, gm.getBoard().getTab(), gm.getPlayerValue())
							|| Utils.enemyAhead(moveN.getPosition(), gm.getBoard().getTab(), value)) {
						Cell cell = (Cell) gm.getBoard().getChildren().get(coordinate.getIndex() + 9 * value * -1);
						cell.disHighlight();
					}
					move = new Move(coordinate, value, coordinate.getNE(value));
					if (Utils.isLegal(move, gm.getBoard().getTab(), gm.getPlayerValue())
							|| Utils.enemyAhead(moveN.getPosition(), gm.getBoard().getTab(), value)) {
						Cell cell = (Cell) gm.getBoard().getChildren().get(coordinate.getIndex() + 7 * value * -1);
						cell.disHighlight();
					}
				}
			}
		});

		this.setOnMouseClicked(event -> {

			if (!gm.hasSelectedToken() && value == gm.getPlayerValue())
				gm.selectToken(coordinate.getIndex());
			else {

				ArrayList<Move> legal = Utils.getBoxLegalMove(gm.getBoard().getTab(), gm.getPlayerValue(),
						gm.getSelectedTokenIndex());
				if (Utils.isInsideZoneMove(coordinate, legal)) {
					for (int i = 0; i < legal.size(); i++) {
						if (legal.get(i).getDestination().equals(coordinate)) {
							gm.setPlayerMove(legal.get(i));
							gm.getPlayer(gm.getPlayerIndex()).setMove(legal.get(i));
							occuped = false;
							Cell cell = (Cell) gm.getBoard().getChildren()
									.get(legal.get(i).getDestination().getIndex());
							cell.isOccuped();
							cell.value = -gm.getPlayerValue();
							gm.deselectToken();
							for (int j = 0; j < legal.size(); j++) {
								cell = (Cell) gm.getBoard().getChildren().get(legal.get(j).getDestination().getIndex());
								cell.disHighlight();
							}
						}
					}
				} else {
					gm.deselectToken();
				}
			}
			if (!gm.hasSelectedToken()) {
				for (int i = 0; i < gm.getBoard().getChildren().size(); i++) {
					Cell cell = (Cell) gm.getBoard().getChildren().get(i);
					cell.disHighlight();
				}
				gm.deselectToken();
			}
		});
	}

	public void hideToken() {
		black.toBack();
		white.toBack();
		value = 0;
		occuped = false;
	}

	public void showToken(Boolean isWhite) {

		if (isWhite) {
			white.toFront();
			value = 1;
		} else {
			black.toFront();
			value = -1;
		}
		occuped = true;
	}

	public void highlight(Color color) {
		 if (gm.getPlayer(gm.getPlayerIndex()).getPlayerType() == PlayerType.HUMAN) {
		highlight.setFill(color);
		highlight.toFront();
		 }
	}

	public void disHighlight() {
		highlight.toBack();
	}

	public void isOccuped() {
		this.occuped = true;
	}

}
