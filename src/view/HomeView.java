package view;

import java.util.List;
import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class HomeView extends BorderPane {
	private Stage stage;
	private User user;

	private GridPane viewGP;

	private ScrollPane scroll;

	private TableView<Item> itemTV;	
	private TableColumn<Item, Integer> idColumn;
	private TableColumn<Item, String> nameColumn;
	private TableColumn<Item, String> categoryColumn;
	private TableColumn<Item, String> sizeColumn;
	private TableColumn<Item, String> priceColumn;
	private TableColumn<Item, String> offerPriceColumn;

	private Button logOutButton;
	
	private Button viewItemsButton; // all
	private Button viewWishlistButton, viewHistoryButton; // buyer
	private Button viewOfferItemButton; // seller
	private Button viewRequestedItemButton; // admin

	//	SET TABLES (items, wishlist, history, requested items)

	// initial table (default: view items, also for: wish list, requested item)
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

	//	view wish list 
	private void viewWishlist() {
		List<Item> wishlist = WishlistController.viewWishlist(user.getId());
		ObservableList<Item> wishlistOL = FXCollections.observableList(wishlist);
		itemTV.setItems(wishlistOL);
	}

	// view requested item 
	private void viewRequestedItem() {
		List<Item> requestedItems = ItemController.viewRequestedItem(1, "PENDING");
		ObservableList<Item> requestedItemsOL = FXCollections.observableList(requestedItems);
		itemTV.setItems(requestedItemsOL);
	}

	// 	transaction history table 
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

		viewItems();
	}
	private void viewHistory() {
		List<Item> history = TransactionController.viewHistory(user.getId());
		ObservableList<Item> historyOL = FXCollections.observableList(history);
		itemTV.setItems(historyOL);
	}

	//	private void initOfferItemTable() {
	//		itemTV = new TableView<>();
	//
	//		nameColumn = new TableColumn<>("Name");
	//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
	//
	//		categoryColumn = new TableColumn<>("Category");
	//		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
	//
	//		sizeColumn = new TableColumn<>("Size");
	//		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("Size"));
	//
	//		priceColumn = new TableColumn<>("Price");
	//		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
	//
	//		offerPriceColumn = new TableColumn<>("Offer Price");
	//		offerPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Offer Price"));
	//
	//
	//		itemTV.getColumns().add(nameColumn);
	//		itemTV.getColumns().add(categoryColumn);
	//		itemTV.getColumns().add(sizeColumn);
	//		itemTV.getColumns().add(priceColumn);
	//		itemTV.getColumns().add(offerPriceColumn);
	//		viewOfferItem();
	//	}
	//	private void viewOfferItem() {
	//		List<Item> items = ItemController.viewOfferItem();
	//		ObservableList<Item> itemOL = FXCollections.observableList(items);
	//		itemTV.setItems(itemOL);
	//	}

	private void init() {
		viewGP = new GridPane();

		logOutButton = new Button("Logout");
		viewItemsButton = new Button("View items");
		viewWishlistButton = new Button("View wishlist");
		viewHistoryButton = new Button("View history");
		viewOfferItemButton = new Button("View offered item(s)");
		viewRequestedItemButton = new Button("View requested item(s)");

		scroll = new ScrollPane();	

		initTable();
		viewItems();
	}

	private void setGPBuyer() {
		viewGP = new GridPane();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewWishlistButton, 0, 1);
		viewGP.add(viewHistoryButton, 0, 2);
	}

	private void setGPSeller() {
		viewGP = new GridPane();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewOfferItemButton, 0, 1);
	}

	private void setGPAdmin() {
		viewGP = new GridPane();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewRequestedItemButton, 0, 1);
	}

	private void setGPAll() {
		viewGP = new GridPane();
		viewGP.add(logOutButton, 0, 0);
		viewGP.add(viewItemsButton, 0, 1);
	}

	private void setGPInitial() {
		if(user.getRole().equals("Admin")) {
			setGPAdmin();
		} else if (user.getRole().equals("Seller")) {
			setGPSeller();
		} else if (user.getRole().equals("Buyer")){
			setGPBuyer();
		} else {
			System.out.println("Unrecognized user role.");
		}
		
	}

	private void setLayout() {
		setGPInitial();
		this.setLeft(viewGP);

		this.setRight(scroll);
		scroll.setContent(itemTV);
	}


	private void setEvents() {
		logOutButton.setOnAction(e -> {
			stage.setUserData(null);
			new LoginView(stage);
		});
		
		viewWishlistButton.setOnAction(e -> {
			// set left grid pane
			setGPAll();
			this.setLeft(viewGP);

			// set right table
			initTable();
			viewWishlist();
			scroll.setContent(itemTV);
		});

		viewHistoryButton.setOnAction(e -> {
			setGPAll();
			this.setLeft(viewGP);

			initHistoryTable();
			viewHistory();
			scroll.setContent(itemTV);
		});

		viewOfferItemButton.setOnAction(e -> {
			System.out.println("bruh 2");
			setGPAll();
			this.setLeft(viewGP);

			//			 initOfferItemTable();
			//			 viewOfferItem();
			//			 scroll.setContent(offerItemTV);
		});

		viewRequestedItemButton.setOnAction(e -> {
			setGPAll();
			this.setLeft(viewGP);

			initTable();
			viewRequestedItem();
			scroll.setContent(itemTV);
		});

		viewItemsButton.setOnAction(e -> {
			setGPInitial();
			this.setLeft(viewGP);

			initTable();
			viewItems();
			scroll.setContent(itemTV);
		});;
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
