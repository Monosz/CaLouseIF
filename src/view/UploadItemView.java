package view;

import controller.ItemController;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.User;

public class UploadItemView extends BorderPane{
	private Stage stage;
	private User user;

	private GridPane formGP, backButtonGP;
	private ColumnConstraints col1;
	private Label nameLabel, categoryLabel, sizeLabel, priceLabel;
	private TextField nameTextField, categoryTextField, sizeTextField, priceTextField;
	private Button uploadItemButton, backButton;
	private Label errorLabel;

	private void init() {
		backButtonGP = new GridPane();
		col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		backButtonGP.getColumnConstraints().add(col1);
		backButton = new Button(">> Go back to home page");
		backButtonGP.getChildren().add(backButton);
		GridPane.setColumnIndex(backButton, 1);

		formGP = new GridPane();

		nameLabel = new Label("Name:");
		categoryLabel = new Label("Category:");
		sizeLabel = new Label("Size:");
		priceLabel = new Label("Price:");

		nameTextField = new TextField();
		categoryTextField = new TextField();
		sizeTextField = new TextField();
		priceTextField = new TextField();

		uploadItemButton = new Button("Upload Item");
		errorLabel = new Label();
		errorLabel.setWrapText(true);
	}

	private void setLayout() {

		formGP.add(nameLabel, 0, 0);
		formGP.add(nameTextField, 1, 0);

		formGP.add(categoryLabel, 0, 1);
		formGP.add(categoryTextField, 1, 1);

		formGP.add(sizeLabel, 0, 2);
		formGP.add(sizeTextField, 1, 2);

		formGP.add(priceLabel, 0, 3);
		formGP.add(priceTextField, 1, 3);

		formGP.add(uploadItemButton, 0, 6);
		formGP.add(errorLabel, 1, 5);


		this.setTop(backButtonGP);
		this.setCenter(formGP);
	}

	private void setEvents() {
		backButton.setOnMouseClicked(e -> {
			new HomeView(stage);
		});

		uploadItemButton.setOnMouseClicked(e -> {
			String name = nameTextField.getText().strip(),
					category = categoryTextField.getText().strip(),
					size = sizeTextField.getText().strip(),
					price= priceTextField.getText().strip(),
					message = ItemController.uploadItem(name, size, price, category, user.getId());
			if (message.equals("Register success")) {
				new LoginView(stage);				
			} else {
				errorLabel.setText(message);
			}
		});
	}

	public UploadItemView(Stage stage) {
		this.stage = stage;
		this.user = (User) stage.getUserData();

		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 500, 400);
		stage.setScene(scene);
		stage.show();
	}
}
