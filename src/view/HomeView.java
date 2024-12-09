package view;

import java.util.List;
import controller.ItemController;
import controller.TransactionController;
import controller.UserController;
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

	private GridPane viewLeftGP;

	private ScrollPane scroll;

	private TableView<Item> itemTV;	
	private TableColumn<Item, String> nameColumn;
	private TableColumn<Item, String> categoryColumn;
	private TableColumn<Item, String> sizeColumn;
	private TableColumn<Item, String> priceColumn;
	private TableColumn<Item, String> offerPriceColumn;

	private Button viewItemsButton; // all
	private Button viewWishlistButton, viewHistoryButton; // buyer
	private Button viewOfferItemButton; // seller
	private Button viewRequestedItemButton; // admin

	//	SET TABLES (items, wishlist, history, requested items)

	// initial table (default: view items, also for: wish list, requested item)
	private void initTable() {
		itemTV = new TableView<>();

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
	//	TODO: a mess
	//	have to display: transaction id + item name, category, size, and price
	//	private void initHistoryTable() {
	//		//		user historyTV instead ? 
	//		//		private TableView<Transaction + Item>??? historyTV;	
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
	//		itemTV.getColumns().add(nameColumn);
	//		itemTV.getColumns().add(categoryColumn);
	//		itemTV.getColumns().add(sizeColumn);
	//		itemTV.getColumns().add(priceColumn);
	//
	//		viewItems();
	//	}
	//	private void viewHistory() {
	//		// 	TODO: possible sol: https://stackoverflow.com/questions/54083513/javafx-populating-tableview-with-models-from-different-classes
	//		List<Object> history = TransactionController.viewHistory(user.getId());
	//		ObservableList<Object> historyOL = FXCollections.observableList(history);
	//		itemTV.setItems(historyOL);
	//	}


	//	offer item table (initial table + offer price)
	//	TODO: back-end nya kelarin duls
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
	//		List<Item> items = ItemController.viewOfferItem(zzz);
	//		ObservableList<Item> itemOL = FXCollections.observableList(items);
	//		itemTV.setItems(itemOL);
	//	}



	private void init() {
		viewLeftGP = new GridPane();

		viewItemsButton = new Button("View items");
		viewWishlistButton = new Button("View wishlist");
		viewHistoryButton = new Button("View history");
		viewOfferItemButton = new Button("View offered item(s)");
		viewRequestedItemButton = new Button("View requested item(s)");

		scroll = new ScrollPane();	

		initTable();
		viewItems();
	}

	private void setLeftGPBuyer() {
		viewLeftGP = new GridPane();
		viewLeftGP.add(viewWishlistButton, 0, 0);
		viewLeftGP.add(viewHistoryButton, 0, 1);
	}

	private void setLeftGPSeller() {
		viewLeftGP = new GridPane();
		viewLeftGP.add(viewOfferItemButton, 0, 0);
	}

	private void setLeftGPAdmin() {
		viewLeftGP = new GridPane();
		viewLeftGP.add(viewRequestedItemButton, 0, 0);
	}

	private void setLeftGPAll() {
		viewLeftGP = new GridPane();
		viewLeftGP.add(viewItemsButton, 0, 0);
	}

	private void setLeftGPInitial() {
		if(user.getRole().equals("Admin")) {
			setLeftGPAdmin();
		} else if (user.getRole().equals("Seller")) {
			setLeftGPSeller();
		} else if (user.getRole().equals("Buyer")){
			setLeftGPBuyer();
		} else {
			System.out.println("Unrecognized user role.");
		}
	}

	private void setLayout() {
		setLeftGPInitial();
		this.setLeft(viewLeftGP);

		this.setRight(scroll);
		scroll.setContent(itemTV);
	}


	private void setEvents() {
		viewWishlistButton.setOnAction(e -> {
			// set left grid pane
			setLeftGPAll();
			this.setLeft(viewLeftGP);

			// set right table
			initTable();
			viewWishlist();
			scroll.setContent(itemTV);
		});

		viewHistoryButton.setOnAction(e -> {
			System.out.println("bruh"); 
			setLeftGPAll();
			this.setLeft(viewLeftGP);

			// initHistoryTable();
			// viewHistory();
			// scroll.setContent(historyTV);
		});

		viewOfferItemButton.setOnAction(e -> {
			System.out.println("bruh 2");
			setLeftGPAll();
			this.setLeft(viewLeftGP);

			// initOfferItemTable();
			// viewOfferItem();
			// scroll.setContent(offerItemTV);
		});

		viewRequestedItemButton.setOnAction(e -> {
			setLeftGPAll();
			this.setLeft(viewLeftGP);

			initTable();
			viewRequestedItem();
			scroll.setContent(itemTV);
		});

		viewItemsButton.setOnAction(e -> {
			setLeftGPInitial();
			this.setLeft(viewLeftGP);

			initTable();
			viewItems();
			scroll.setContent(itemTV);
		});;
	}


	public HomeView(Stage stage, User user) {
		this.stage = stage;
		this.user = user;

		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 450, 400);
		stage.setScene(scene);
		stage.show();
	}

}
