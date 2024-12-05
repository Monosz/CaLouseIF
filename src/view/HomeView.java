package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

public class HomeView extends BorderPane {
	private Stage stage;
	private User user;
	
	private void init() {
	}
	
	private void setLayout() {
		
	}
	
	private void setEvents() {
		
	}
	
	public HomeView(Stage stage) {
		this.stage = stage;
		this.user = (User) stage.getUserData();
		
		init(); setLayout(); setEvents();
		
		Scene scene = new Scene(this);
	
		stage.setScene(scene);
		stage.show();
	}
	
}
