package up5.poo.breaktrough;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import up5.poo.breaktrough.Cell;

/**
 * 
 * @author Tinap
 *
 *The panel of the game
 */
public class PanelBreakThrough extends Pane{
	
	private Board board;
	public PanelBreakThrough(GameManager gm){
		
		board = new Board(gm);
		getChildren().add(board);
	}

}
