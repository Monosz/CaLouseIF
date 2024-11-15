package view;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomeView extends GridPane {
	private Stage stage;
	
	
	public HomeView(Stage stage) {
		this.stage = stage;
		
		Scene scene = new Scene(this, 400, 300);
		stage.setScene(scene);
		stage.setTitle("CaLouseIF/Home");
		stage.show();
	}
	
}
