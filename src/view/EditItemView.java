package view;

import controller.ItemController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Item;

public class EditItemView extends BorderPane{
	private Stage stage;

	private GridPane formGP, topGP;
	private Label titleLabel;
	private Label nameLabel, categoryLabel, sizeLabel, priceLabel;
	private TextField nameTextField, categoryTextField, sizeTextField, priceTextField;
	private Button saveChangesButton, backButton;
	private Label errorLabel;

	private Item item;

	private void init() {
		backButton = new Button("Go back to home page");

		topGP = new GridPane();
		titleLabel = new Label("Edit Item");

		formGP = new GridPane();
		nameLabel = new Label("Name:");
		categoryLabel = new Label("Category:");
		sizeLabel = new Label("Size:");
		priceLabel = new Label("Price:");

		nameTextField = new TextField();
		nameTextField.setText(item.getName());
		categoryTextField = new TextField();
		categoryTextField.setText(item.getCategory());
		sizeTextField = new TextField();
		sizeTextField.setText(item.getSize());
		priceTextField = new TextField();
		Integer temp = item.getPrice(); 
		String price = temp.toString();
		priceTextField.setText(price);
		saveChangesButton = new Button("Save Changes");
		errorLabel = new Label();
		errorLabel.setWrapText(true);
	}

	private void setLayout() {
		topGP.add(backButton, 0, 0);
		titleLabel.setMaxWidth(Double.MAX_VALUE);
		titleLabel.setAlignment(Pos.CENTER);
		topGP.add(titleLabel, 0, 1);

		GridPane.setHgrow(backButton, Priority.ALWAYS);
		GridPane.setHgrow(titleLabel, Priority.ALWAYS);

		errorLabel.setWrapText(true);

		formGP.add(nameLabel, 0, 0);
		formGP.add(nameTextField, 1, 0);

		formGP.add(categoryLabel, 0, 1);
		formGP.add(categoryTextField, 1, 1);

		formGP.add(sizeLabel, 0, 2);
		formGP.add(sizeTextField, 1, 2);

		formGP.add(priceLabel, 0, 3);
		formGP.add(priceTextField, 1, 3);

		formGP.add(saveChangesButton, 0, 6, 2, 1);
		GridPane.setHalignment(saveChangesButton, HPos.CENTER);

		formGP.add(errorLabel, 1, 5);

		formGP.setAlignment(Pos.BASELINE_CENTER);
		this.setTop(topGP);
		this.setCenter(formGP);
	}

	private void setEvents() {
		backButton.setOnMouseClicked(e -> {
			new HomeView(stage);
		});

		saveChangesButton.setOnMouseClicked(e -> {
			String name = nameTextField.getText().strip(),
					category = categoryTextField.getText().strip(),
					size = sizeTextField.getText().strip(),
					price= priceTextField.getText().strip(),
					message = ItemController.editItem(item.getId(),name, size, price, category);
			if (message.equals("Changes saved")) {
				new HomeView(stage);				
			} else {
				errorLabel.setText(message);
			}
		});
	}

	public EditItemView(Stage stage, int id) {
		this.stage = stage;
		this.item = Item.getItem(id);

		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 400, 300);
		stage.setScene(scene);
		stage.show();
	}
}
