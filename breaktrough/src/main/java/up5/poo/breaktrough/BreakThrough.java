package up5.poo.breaktrough;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import up5.poo.breaktrough.GameManager.GameMode;

public class BreakThrough extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		
		GameManager gm = new GameManager(GameMode.AIH, 2.25954d,  2.25954d);
		stage.setTitle("BreakThrough");
		PanelBreakThrough mainPane = new PanelBreakThrough(gm);
		Scene scene = new Scene(mainPane, 400, 425);
		gm.setDaemon(true);
		gm.start();
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(event ->Platform.exit());
	}
	
	
}

