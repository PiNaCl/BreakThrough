package up5.poo.breaktrough;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BreakThrough extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		GameManager gm = new GameManager();
		stage.setTitle("BreakThrough");
		Pane mainPane = new PanelBreakThrough(gm);
		Scene scene = new Scene(mainPane, 600, 400);
		stage.setScene(scene);
		stage.show();
	}
}

