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
		Label text = new Label("test");
		Platform.runLater(() -> {
		this.setTop(gm.getBoard());
		});
		
		/* InfoPane info =  new InfoPane(gm);
		this.setRight(info);*/
	}
	/*
	private class InfoPane extends BorderPane {
		private Label tourDe;
		private Label tourNb;
		private int compteurTour = 1;
		public InfoPane(GameManager gm){
			tourDe = new Label("C'est au tour des " + (gm.getPlayerIndex() == 0 ? "blancs" : "noirs") );
			tourNb = new Label("tour n°" + compteurTour);
			this.setLeft(tourDe);
			this.setCenter(tourNb);
		}
		
		public void setTourDe (int tour){
			tourDe.setText("C'est au tour des" + (tour==1 ? "Blancs" : "Noirs"));
			compteurTour++;
			tourNb.setText("tour n°" + compteurTour);
		}
	}*/
	
	
}
