package view;

import controller.UserController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Represents the registration view of the application built with JavaFX.
 * <p>
 * This view allows users to register by providing their name, password, phone
 * number, address, and selecting a role (Buyer or Seller). The form is
 * submitted via the {@link UserController}.
 * <p>
 * Layout is managed using {@link BorderPane}, with input fields and buttons
 * organized in a {@link GridPane}. Success or error messages are displayed
 * dynamically based on user input.
 * </p>
 * 
 * @see UserController
 * @see LoginView
 */
public class RegisterView extends BorderPane {
	private Stage stage;
	private GridPane gp;
	private FlowPane fp;

	private Label titleLabel;
	private Label nameLabel, passwordLabel, phoneLabel, addressLabel, roleLabel;
	private TextField nameTextField, phoneTextField, addressTextField;
	private PasswordField passwordField;
	private RadioButton buyerRadioButton, sellerRadioButton;
	private ToggleGroup roleToggleGroup;
	private Button registerButton;
	private Label loginRedirectLabel, errorLabel;

	/**
	 * Initializes the UI components for the register view, including labels, text
	 * fields, radio buttons, buttons, and error messages.
	 */
	private void init() {
		gp = new GridPane();
		fp = new FlowPane(Orientation.HORIZONTAL);

		titleLabel = new Label("Register");

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
		errorLabel.setTextFill(Color.RED);
	}
	
	private void setGridPaneConstraints() {
	    ColumnConstraints labelColumn = new ColumnConstraints();
	    labelColumn.setPercentWidth(40); 
	    ColumnConstraints inputColumn = new ColumnConstraints();
	    inputColumn.setPercentWidth(60); 

	    gp.getColumnConstraints().addAll(labelColumn, inputColumn);
	}

	/**
	 * Sets the layout of the register view.
	 * <p>
	 * Organizes all UI components within a {@link GridPane} and aligns them
	 * appropriately.
	 * </p>
	 */
	private void setLayout() {
		setGridPaneConstraints();
		titleLabel.setAlignment(Pos.CENTER);

		loginRedirectLabel.setWrapText(true);
		errorLabel.setWrapText(true);

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
		gp.add(fp, 1, 4);

		
		gp.add(registerButton, 0, 5);
		gp.add(errorLabel, 1, 5);

		gp.add(loginRedirectLabel, 0, 6);

		gp.setAlignment(Pos.BASELINE_CENTER);
		this.setTop(titleLabel);
		this.setCenter(gp);
	}

	/**
	 * Configures event handlers for the {@code RegisterView}.
	 * <p>
	 * Includes actions for the registration button and login redirection link,
	 * which switches to the {@link LoginView}.
	 * </p>
	 */
	private void setEvents() {
		registerButton.setOnAction(e -> {
			String name = nameTextField.getText().strip();
			String password = passwordField.getText().strip();
			String phone = phoneTextField.getText().strip();
			String address = addressTextField.getText().strip();
			String role = (roleToggleGroup.getSelectedToggle() != null)
					? ((Labeled) roleToggleGroup.getSelectedToggle()).getText()
					: "";
			String message = UserController.register(name, password, phone, address, role);

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

	/**
	 * Constructs a new {@code RegisterView}. Initializes the stage, sets up the
	 * layout, and configures event handlers.
	 *
	 * @param stage the primary stage for displaying the registration view
	 */
	public RegisterView(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		setEvents();

		Scene scene = new Scene(this, 500, 200);
		stage.setScene(scene);
		stage.show();
	}
}
