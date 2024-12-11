package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterView extends BorderPane {
	private Stage stage;
	private GridPane gp;
	private FlowPane fp;

	private Label nameLabel, passwordLabel, phoneLabel, addressLabel, roleLabel;
	private TextField nameTextField, phoneTextField, addressTextField;
	private PasswordField passwordField;
	private RadioButton buyerRadioButton, sellerRadioButton;
	private ToggleGroup roleToggleGroup;
	private Button registerButton;
	private Label loginRedirectLabel, errorLabel;

	private void init() {
		gp = new GridPane();
		fp = new FlowPane();

		nameLabel = new Label("Name:");
		passwordLabel = new Label("Password:");
		phoneLabel = new Label("Phone Number:");
		addressLabel = new Label("Address:");
		roleLabel = new Label("Role:");

		nameTextField = new TextField();
		phoneTextField = new TextField();
		addressTextField = new TextField();

		passwordField = new PasswordField();

		buyerRadioButton = new RadioButton("Buyer");
		sellerRadioButton = new RadioButton("Seller");
		roleToggleGroup = new ToggleGroup();
		buyerRadioButton.setToggleGroup(roleToggleGroup);
		sellerRadioButton.setToggleGroup(roleToggleGroup);

		registerButton = new Button("Register");
		loginRedirectLabel = new Label("Already have an account? Sign in");
		errorLabel = new Label();
	}

	private void setLayout() {
		gp.add(nameLabel, 0, 0);
		gp.add(nameTextField, 1, 0);

		gp.add(passwordLabel, 0, 1);
		gp.add(passwordField, 1, 1);

		gp.add(phoneLabel, 0, 2);
		gp.add(phoneTextField, 1, 2);

		gp.add(addressLabel, 0, 3);
		gp.add(addressTextField, 1, 3);

		gp.add(roleLabel, 0, 4);
		fp.getChildren().addAll(buyerRadioButton, sellerRadioButton);
		gp.add(fp, 1, 4, 2, 1);

		gp.add(registerButton, 0, 5);
		gp.add(errorLabel, 1, 5, 3, 1);

		gp.add(loginRedirectLabel, 0, 6);
		this.setCenter(gp);
	}

	private void setEvents() {
		registerButton.setOnAction(e -> {
			String name = nameTextField.getText().strip(),
					password = passwordField.getText().strip(),
					phone = phoneTextField.getText().strip(),
					address = addressTextField.getText().strip(),
					role = (roleToggleGroup.getSelectedToggle() != null) ? ((Labeled) roleToggleGroup.getSelectedToggle()).getText() : "",
							message = UserController.register(name, password, phone, address, role);
			if (message.equals("Register success")) {
				new LoginView(stage);				
			} else {
				errorLabel.setText(message);
			}
		});

		loginRedirectLabel.setOnMouseClicked(e -> {
			new LoginView(stage);
		});
	}

	public RegisterView(Stage stage) {
		this.stage = stage;
		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 400, 300);
		stage.setScene(scene);
		stage.show();
	}

}
