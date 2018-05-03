package up5.poo.breaktrough;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Tinap
 *
 *The panel of the game
 */
public class PanelBreakThrough extends BorderPane{
	
	public PanelBreakThrough(GameManager gm){	
		super();
		this.setTop(gm.getBoard());
		this.setLeft(gm.getInfo());
		/*TO IMPLEMENTS using reInit
		private Button replayAI;
		private Button replayAIH;
		private Button replayH;*/
		
	}	
	
}
