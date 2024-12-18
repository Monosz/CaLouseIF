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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

/**
 * Represents the login view of the application built with JavaFX.
 * <p>
 * This view allows users to log in by providing their username and password. If
 * the login is successful, the application navigates to the home view. Users
 * who do not have an account can redirect to the registration view.
 * <p>
 * Layout is managed using {@link BorderPane}, with input fields and buttons
 * organized in a {@link GridPane}. Error messages are displayed dynamically for
 * invalid login attempts.
 * </p>
 * 
 * @see UserController
 * @see HomeView
 * @see RegisterView
 */
public class LoginView extends BorderPane {
	private Stage stage;
	private GridPane gp;

	private Label titleLabel;

	private Label nameLabel, passwordLabel;
	private TextField nameTextField;
	private PasswordField passwordField;
	private Button loginButton;
	private Label registerRedirectLabel, errorLabel;

	/**
	 * Initializes the UI components for the {@code LoginView}, including labels,
	 * text fields, buttons, and error messages.
	 */
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
		errorLabel.setTextFill(Color.RED);
	}

	/**
	 * Sets the layout of the login view.
	 * <p>
	 * Organizes all UI components within a {@link GridPane} and aligns them
	 * appropriately. The title label is positioned at the top, while input fields
	 * and buttons are centered.
	 * </p>
	 */
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

	/**
	 * Configures event handlers for the {@code LoginView}.
	 * <p>
	 * Includes actions for the login button, which triggers authentication, and the
	 * registration redirect link, which switches to the {@link RegisterView}.
	 * </p>
	 */
	private void setEvents() {
		loginButton.setOnAction(e -> {
			String name = nameTextField.getText(), password = passwordField.getText();
			User user = UserController.login(name, password);

			if (user != null) {
				stage.setUserData(user);
				new HomeView(stage);
			} else {
				errorLabel.setText("Invalid login credentials.");
			}
		});

		registerRedirectLabel.setOnMouseClicked(e -> {
			new RegisterView(stage);
		});
	}

	/**
	 * Constructs a new {@code LoginView}. Initializes the stage, sets up the
	 * layout, and configures event handlers.
	 *
	 * @param stage the primary stage for displaying the login view
	 */
	public LoginView(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		setEvents();

		Scene scene = new Scene(this, 400, 200);
		stage.setScene(scene);
		stage.show();
	}
}
