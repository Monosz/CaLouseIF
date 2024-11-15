package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.RegisterView;

public class Main extends Application {

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("CaLouseIF");
		
		new RegisterView(stage);
	}

}
