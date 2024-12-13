package view;

import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class LoginView extends BorderPane {
	private Stage stage;
	private GridPane gp;

	private Label titleLabel;
	
	private Label nameLabel, passwordLabel;
	private TextField nameTextField;
	private PasswordField passwordField;
	private Button loginButton;
	private Label registerRedirectLabel, errorLabel;

	private void init() {
		gp = new GridPane();

		titleLabel = new Label("Login");
		
		nameLabel = new Label("Name:");
		passwordLabel = new Label("Password:");

		nameTextField = new TextField();
		passwordField = new PasswordField();

		loginButton = new Button("Login");
		registerRedirectLabel = new Label("Don't have an account? Register");
		errorLabel = new Label();
	}

	private void setLayout() {
		titleLabel.setMaxWidth(Double.MAX_VALUE);
		titleLabel.setAlignment(Pos.CENTER);

		errorLabel.setWrapText(true);
		
		gp.add(nameLabel, 0, 0);
		gp.add(nameTextField, 1, 0);

		gp.add(passwordLabel, 0, 1);
		gp.add(passwordField, 1, 1);

		gp.add(loginButton, 0, 2);
		gp.add(errorLabel, 1, 2);
		gp.add(registerRedirectLabel, 0, 3);
		
		gp.setAlignment(Pos.BASELINE_CENTER);
		this.setTop(titleLabel);
		this.setCenter(gp);
	}

	private void setEvents() {
		loginButton.setOnAction(e -> {
			String name = nameTextField.getText(),
					password = passwordField.getText();
			User user = UserController.login(name, password);

			if (user != null) {
				stage.setUserData(user);
				new HomeView(stage);				
			} else {
				errorLabel.setText("Invalid login details.");
			}
		});

		registerRedirectLabel.setOnMouseClicked(e -> {
			new RegisterView(stage);
		});
	}

	public LoginView(Stage stage) {
		this.stage = stage;
		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 400, 200);
		stage.setScene(scene);
		stage.show();
	}

}
