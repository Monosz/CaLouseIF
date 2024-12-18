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

public class DeclineOfferView extends BorderPane{
	private Stage stage;
	private User user;
	private Item item;

	private GridPane formGP, topGP;
	private Label titleLabel;
	private Label nameLabel, reasonLabel;
	private TextField reasonTF;
	private Button submitReasonButton, backButton;
	private Label errorLabel;
	

	private void init() {
		backButton = new Button("Go back to home page");

		topGP = new GridPane();
		titleLabel = new Label("Make Offer");
		
		formGP = new GridPane();
		
		nameLabel = new Label();
		nameLabel.setText(item.getName());
		
		reasonLabel = new Label();
		reasonLabel = new Label("Please provide a reason: ");
		
		reasonTF = new TextField();

		submitReasonButton = new Button("Decline Offer");
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

		formGP.add(reasonLabel, 0, 1);
		formGP.add(reasonTF, 1, 1);
		
		formGP.add(errorLabel, 1, 4);

		formGP.add(submitReasonButton, 0, 5, 2, 1);
		GridPane.setHalignment(submitReasonButton, HPos.CENTER);


		formGP.setAlignment(Pos.BASELINE_CENTER);
		this.setTop(topGP);
		this.setCenter(formGP);
	}

	private void setEvents() {
		backButton.setOnMouseClicked(e -> {
			new HomeView(stage);
		});

		submitReasonButton.setOnMouseClicked(e -> {
			String reason = reasonTF.getText();
			if(reason.isEmpty()) {
				errorLabel.setText("Please fill in a reason");
				return;
			}
			int res = ItemController.declineOffer(item.getId());
			if (res == 1) {
				new HomeView(stage);				
			} else {
				errorLabel.setText("Failed to decline offer");
			}
		});
	}

	public DeclineOfferView(Stage stage, int id) {
		this.stage = stage;
		this.user = (User) stage.getUserData();
		this.item = Item.getItem(id);
		
		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 400, 300);
		stage.setScene(scene);
		stage.show();
	}
}
