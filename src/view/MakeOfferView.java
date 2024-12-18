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
import model.User;

public class MakeOfferView extends BorderPane{
	private Stage stage;
	private User user;
	private Item item;

	private GridPane formGP, topGP;
	private Label titleLabel;
	private Label nameLabel, priceLabel, priceValueLabel, currentOfferLabel, currentOfferValueLabel, newOfferLabel;
	private TextField newOfferTF;
	private Button submitOfferButton, backButton;
	private Label errorLabel;
	

	private void init() {
		backButton = new Button("Go back to home page");

		topGP = new GridPane();
		titleLabel = new Label("Make Offer");
		
		formGP = new GridPane();
		
		nameLabel = new Label();
		nameLabel.setText(item.getName());
		
		priceLabel = new Label("Current price: ");
		priceValueLabel = new Label();
		Integer temp = item.getPrice();
		String value = temp.toString();
		priceValueLabel.setText(value);
		
		
		currentOfferLabel = new Label("Current offer: ");
		
		currentOfferValueLabel = new Label();
		temp = item.getOfferStatus();
		value = temp == 0 ? "No offers yet" : temp.toString();
		currentOfferValueLabel.setText(value);
		
		newOfferLabel = new Label("Your Offer:");
		newOfferTF = new TextField();

		submitOfferButton = new Button("Submit Offer");
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

		formGP.add(nameLabel, 0, 0, 2, 1);

		formGP.add(priceLabel, 0, 1);
		formGP.add(priceValueLabel, 1, 1);
		
		formGP.add(currentOfferLabel, 0, 2);
		formGP.add(currentOfferValueLabel, 1, 2);

		formGP.add(newOfferLabel, 0, 3);
		formGP.add(newOfferTF, 1, 3);

		formGP.add(errorLabel, 1, 4);

		formGP.add(submitOfferButton, 0, 5, 2, 1);
		GridPane.setHalignment(submitOfferButton, HPos.CENTER);


		formGP.setAlignment(Pos.BASELINE_CENTER);
		this.setTop(topGP);
		this.setCenter(formGP);
	}

	private void setEvents() {
		backButton.setOnMouseClicked(e -> {
			new HomeView(stage);
		});

		submitOfferButton.setOnMouseClicked(e -> {
			String offer = newOfferTF.getText();  
			String message = ItemController.offerPrice(item.getId(), offer, user.getId());
			if (message.equals("Offer placed successfully")) {
				new HomeView(stage);				
			} else {
				errorLabel.setText(message);
			}
		});
	}

	public MakeOfferView(Stage stage, int id) {
		this.stage = stage;
		this.user = (User) stage.getUserData();
		this.item = Item.getItem(id);
		
		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 400, 300);
		stage.setScene(scene);
		stage.show();
	}
}
