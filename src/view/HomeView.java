package view;

import java.util.List;
import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class HomeView extends BorderPane implements EventHandler<ActionEvent> {
	private Stage stage;
	private User user;

	private GridPane viewGP;
	private Button viewItemsButton; // buyer, admin
	private Button viewWishlistButton, viewHistoryButton; // buyer
	private Button viewItemsSellerButton; // seller
	private Button viewOfferItemButton, uploadItemButton; // seller
	private Button viewRequestedItemButton; // admin


	private GridPane tableGP;
	private Label tableLabel;
	private ScrollPane scroll;
	private TableView<Item> itemTV;	
	private TableColumn<Item, Integer> idColumn;
	private TableColumn<Item, String> nameColumn;
	private TableColumn<Item, String> categoryColumn;
	private TableColumn<Item, String> sizeColumn;
	private TableColumn<Item, String> priceColumn;
	private TableColumn<Item, String> offerPriceColumn;
	private TableColumn<Item, String> statusColumn;

	private GridPane actionGP;
	private Label selectedLabel;
	private Button purchaseItemButton, makeOfferButton; // buyer
	private Button addToWishlistButton, removeFromWishlistButton; // buyer
	private Button editItemButton, deleteItemButton; // seller
	private Button acceptOfferButton, declineOfferButton; // seller
	private Button approveItemButton, declineItemButton;// admin

	private Button logOutButton;

	// INITIALIZE TABLES (right grid pane) 
	// initial table (default: view items, also used for: wish list, requested item, seller item)
	// columns = name, category, size, price
	private void initTable() {
		itemTV = new TableView<>();
		itemTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

		categoryColumn = new TableColumn<>("Category");
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));

		sizeColumn = new TableColumn<>("Size");
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));

		priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

		itemTV.getColumns().add(nameColumn);
		itemTV.getColumns().add(categoryColumn);
		itemTV.getColumns().add(sizeColumn);
		itemTV.getColumns().add(priceColumn);
	}

	private void viewItems() {
		List<Item> items = ItemController.viewItem();
		ObservableList<Item> itemOL = FXCollections.observableList(items);
		itemTV.setItems(itemOL);
	}

	private void viewWishlist() {
		List<Item> wishlist = WishlistController.viewWishlist(user.getId());
		ObservableList<Item> wishlistOL = FXCollections.observableList(wishlist);
		itemTV.setItems(wishlistOL);
	}

	private void viewRequestedItem() {
		List<Item> requestedItems = ItemController.viewRequestedItem(1, "PENDING");
		ObservableList<Item> requestedItemsOL = FXCollections.observableList(requestedItems);
		itemTV.setItems(requestedItemsOL);
	}

	// transaction history table (for buyers)
	// columns = transaction id + name, category, size, price	
	private void initHistoryTable() {
		itemTV = new TableView<>();

		idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

		nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

		categoryColumn = new TableColumn<>("Category");
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));

		sizeColumn = new TableColumn<>("Size");
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));

		priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

		itemTV.getColumns().add(idColumn);
		itemTV.getColumns().add(nameColumn);
		itemTV.getColumns().add(categoryColumn);
		itemTV.getColumns().add(sizeColumn);
		itemTV.getColumns().add(priceColumn);
	}

	private void viewHistory() {
		List<Item> history = TransactionController.viewHistory(user.getId());
		ObservableList<Item> historyOL = FXCollections.observableList(history);
		itemTV.setItems(historyOL);
	}

	// Seller items table (for seller)
	// columns = name, category, size, price + status
	private void initSellerItemsTable() {
		itemTV = new TableView<>();

		nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

		categoryColumn = new TableColumn<>("Category");
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));

		sizeColumn = new TableColumn<>("Size");
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));

		priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

		statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));

		itemTV.getColumns().add(nameColumn);
		itemTV.getColumns().add(categoryColumn);
		itemTV.getColumns().add(sizeColumn);
		itemTV.getColumns().add(priceColumn);
		itemTV.getColumns().add(statusColumn);
	}

	private void viewSellerItems() {
		List<Item> sellerItems = ItemController.viewSellerItem(user.getId());
		ObservableList<Item> sellerItemsOL = FXCollections.observableList(sellerItems);
		itemTV.setItems(sellerItemsOL);
	}

	private void initOfferItemTable() {
		itemTV = new TableView<>();

		nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

		categoryColumn = new TableColumn<>("Category");
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));

		sizeColumn = new TableColumn<>("Size");
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));

		priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

		offerPriceColumn = new TableColumn<>("Offer Price");
		offerPriceColumn.setCellValueFactory(new PropertyValueFactory<>("OfferStatus"));


		itemTV.getColumns().add(nameColumn);
		itemTV.getColumns().add(categoryColumn);
		itemTV.getColumns().add(sizeColumn);
		itemTV.getColumns().add(priceColumn);
		itemTV.getColumns().add(offerPriceColumn);
	}
	private void viewOfferItem() {
		List<Item> items = ItemController.viewOfferItem(user.getId());
		ObservableList<Item> itemOL = FXCollections.observableList(items);
		itemTV.setItems(itemOL);
	}

	//	INITIALIZE DATA
	private void init() {
		// left grid pane
		viewGP = new GridPane(); 

		// initial
		logOutButton = new Button("Logout"); // all		
		viewItemsButton = new Button("View Items"); // buyer, admin
		viewItemsSellerButton = new Button("View Items"); // seller

		// others
		viewWishlistButton = new Button("View Wishlist");
		viewHistoryButton = new Button("View History");

		viewOfferItemButton = new Button("View Offered Items");
		uploadItemButton = new Button("Upload Item");

		viewRequestedItemButton = new Button("View Requested Items");

		// right grid pane
		tableGP = new GridPane();
		tableLabel = new Label("Items");
		scroll = new ScrollPane();	

		if(user.getRole().equals("Seller")) {
			initSellerItemsTable();
			viewSellerItems();
		} else {
			initTable();
			viewItems();			
		}

		// bottom grid pane
		actionGP = new GridPane();

		selectedLabel = new Label("Selected Item: ");

		// initial
		purchaseItemButton = new Button("Purchase Item");
		makeOfferButton = new Button("Make Offer");

		editItemButton = new Button("Edit Item"); 
		deleteItemButton = new Button("Delete Item"); 


		// other
		addToWishlistButton = new Button("Add to Wishlist");
		removeFromWishlistButton = new Button("Remove from Wishlist"); 

		acceptOfferButton = new Button("Accept Offer");
		declineOfferButton = new Button("Decline Offer"); 

		approveItemButton = new Button("Approve Item"); 
		declineItemButton = new Button("Decline Item");
	}

	//	SET VIEW GP (left grid pane)
	private void setViewGPBuyer() {
		viewGP.getChildren().clear();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewWishlistButton, 0, 1);
		viewGP.add(viewHistoryButton, 0, 2);
	}

	private void setViewGPSeller() {
		viewGP.getChildren().clear();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewOfferItemButton, 0, 1);
		viewGP.add(uploadItemButton, 0, 2);
	}

	private void setViewGPAdmin() {
		viewGP.getChildren().clear();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewRequestedItemButton, 0, 1);
	}

	//	BA = buyer, admin
	private void setViewGPInitBA() {
		viewGP.getChildren().clear();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewItemsButton, 0, 1);
	}
	//	S = seller
	private void setViewGPInitS() {
		viewGP.getChildren().clear();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewItemsSellerButton, 0, 1);
	}

	private void setViewGPInitial() {
		tableGP.getChildren().clear();
		tableGP.add(tableLabel, 0, 0);
		tableGP.add(scroll, 0, 1);

		if(user.getRole().equals("Admin")) {
			setViewGPAdmin();
		} else if (user.getRole().equals("Seller")) {
			setViewGPSeller();
		} else if (user.getRole().equals("Buyer")){
			setViewGPBuyer();
		} else {
			System.out.println("Unrecognized user role.");
		}
	}

	//	SET ACTION GP (bottom grid pane)


	private void setLayout() {
		setViewGPInitial();
		this.setLeft(viewGP);

		this.setRight(tableGP);
		scroll.setContent(itemTV);

		this.setBottom(actionGP);
	}


	private void setEvents() {
		logOutButton.setOnAction(e -> {
			stage.setUserData(null);
			new LoginView(stage);
		});

		viewWishlistButton.setOnAction(e -> {
			// set left grid pane
			setViewGPInitBA();
			this.setLeft(viewGP);

			// set right grid pane
			tableLabel.setText("Wishlist");
			initTable();
			viewWishlist();
			scroll.setContent(itemTV);
		});

		viewHistoryButton.setOnAction(e -> {
			setViewGPInitBA();
			this.setLeft(viewGP);

			tableLabel.setText("Transaction History");
			initHistoryTable();
			viewHistory();
			scroll.setContent(itemTV);
		});

		viewOfferItemButton.setOnAction(e -> {
			setViewGPInitS();
			this.setLeft(viewGP);

			tableLabel.setText("Offered Items");
			initOfferItemTable();
			viewOfferItem();
			scroll.setContent(itemTV);
		});

		uploadItemButton.setOnAction(e -> {
			new UploadItemView(stage);
		});

		viewRequestedItemButton.setOnAction(e -> {
			setViewGPInitBA();
			this.setLeft(viewGP);

			tableLabel.setText("Requested Items");
			initTable();
			viewRequestedItem();
			scroll.setContent(itemTV);
		});

		viewItemsButton.setOnAction(e -> {
			setViewGPInitial();
			this.setLeft(viewGP);

			tableLabel.setText("Items");
			initTable();
			viewItems();
			scroll.setContent(itemTV);
		});

		viewItemsSellerButton.setOnAction(e -> {
			setViewGPInitial();
			this.setLeft(viewGP);

			tableLabel.setText("Items");
			initSellerItemsTable();
			viewSellerItems();
			scroll.setContent(itemTV);
		});
	}

	@Override
	public void handle(ActionEvent event) {
		//		if(event.getSource() == addBtn) {
		//			
		//		}
	}

	public HomeView(Stage stage) {
		this.stage = stage;
		this.user = (User) stage.getUserData();

		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 500, 400);
		stage.setScene(scene);
		stage.show();
	}


}
