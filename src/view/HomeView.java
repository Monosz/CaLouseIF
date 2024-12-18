package view;

import java.util.List;
import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class HomeView extends BorderPane {
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
	private Label selectedLabel, errorLabel;
	private TextField selectedItemTF; // item id
	private Button purchaseItemButton, makeOfferButton, addToWishlistButton; // buyer
	private Button removeFromWishlistButton; // buyer
	private Button editItemButton, deleteItemButton; // seller
	private Button acceptOfferButton, declineOfferButton; // seller
	private Button approveItemButton, declineItemButton;// admin
	private int tempItemId;

	private Button logOutButton;

	// INITIALIZE TABLES (right grid pane) 
	// initial table (default: view items, also used to: view wishlist, requested item)
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

		itemTV.setOnMouseClicked(tableClicked());
	}

	// transaction history table (for buyers) (used to view history)
	// columns = transaction id + name, category, size, price	
	private void initHistoryTable() {
		itemTV = new TableView<>();
		itemTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		itemTV.getColumns().clear();

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
		
		itemTV.setOnMouseClicked(tableClicked());
	}

	// Seller items table (for seller) (used to view seller items)
	// columns = name, category, size, price + status
	private void initSellerItemsTable() {
		itemTV = new TableView<>();
		itemTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		itemTV.getColumns().clear();

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
		
		itemTV.setOnMouseClicked(tableClicked());
	}

	// offer items table (for seller) (used to view offered items)
	// columns = name, category, size, price + status
	private void initOfferItemTable() {
		itemTV = new TableView<>();
		itemTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		itemTV.getColumns().clear();

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
		
		itemTV.setOnMouseClicked(tableClicked());
	}

	private void refreshTable(List<Item> items) {
		itemTV.getItems().clear();

		ObservableList<Item> itemOL = FXCollections.observableList(items);
		itemTV.setItems(itemOL);
	}


	private void viewItems() {
		List<Item> items = ItemController.viewItem();
		refreshTable(items);
	}

	private void viewWishlist() {
		List<Item> wishlist = WishlistController.viewWishlist(user.getId());
		refreshTable(wishlist);
	}

	private void viewRequestedItem() {
		List<Item> requestedItems = ItemController.viewRequestedItem(1, "PENDING");
		refreshTable(requestedItems);
	}

	private void viewHistory() {
		List<Item> history = TransactionController.viewHistory(user.getId());
		refreshTable(history);
	}


	private void viewSellerItems() {
		List<Item> sellerItems = ItemController.viewSellerItem(user.getId());
		refreshTable(sellerItems);
	}

	private void viewOfferItem() {
		List<Item> offerItems = ItemController.viewOfferItem(user.getId());
		refreshTable(offerItems);
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
		selectedItemTF = new TextField();
		errorLabel = new Label();

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

		tempItemId = 0;
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

	private void setViewGPInitial() {
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


	// SET TABLE GP (right grid pane)
	private void setTableGPInitial() {
		tableGP.getChildren().clear();
		tableGP.add(tableLabel, 0, 0);
		tableGP.add(scroll, 0, 1);
	}


	//	SET ACTION GP (bottom grid pane)

	private void setActionGPBase() {
		actionGP.getChildren().clear();
		actionGP.add(selectedLabel, 0, 0);
		actionGP.add(selectedItemTF, 1, 0); 
		actionGP.add(errorLabel, 0, 1, 2, 1);
		
		errorLabel.setText("");
	}

	private void setActionGPAdminInit() {
		setActionGPBase();
	}

	private void setActionGPSellerInit() {
		setActionGPBase();
		actionGP.add(editItemButton, 0, 2);
		actionGP.add(deleteItemButton, 1, 2);
	}

	private void setActionGPBuyerInit() {
		setActionGPBase();
		actionGP.add(purchaseItemButton, 0, 2);
		actionGP.add(makeOfferButton, 1, 2);
		actionGP.add(addToWishlistButton, 2, 2);
	}

	private void setActionGPInitial() {
		if(user.getRole().equals("Admin")) {
			setActionGPAdminInit();
		} else if (user.getRole().equals("Seller")) {
			setActionGPSellerInit();
		} else if (user.getRole().equals("Buyer")){
			setActionGPBuyerInit();
		} else {
			System.out.println("Unrecognized user role.");
		}
	}

	// Requested items
	private void setActionGPAdminRequests() {
		setActionGPBase();
		actionGP.add(approveItemButton, 0, 2);
		actionGP.add(declineItemButton, 1, 2);
	}

	private void setActionGPSellerOffers() {
		setActionGPBase();
		actionGP.add(acceptOfferButton, 0, 2);
		actionGP.add(declineOfferButton, 1, 2);
	}

	private void setActionGPBuyerWishlist() {
		setActionGPBase();  
		actionGP.add(removeFromWishlistButton, 1, 2);
	}

	private void setLayout() {
		setViewGPInitial();
		this.setLeft(viewGP);

		setTableGPInitial();
		this.setRight(tableGP);
		scroll.setContent(itemTV);

		actionGP.setAlignment(Pos.CENTER);
		selectedItemTF.setPrefWidth(50);
		setActionGPInitial();
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

			// set bottom grid pane
			setActionGPBuyerWishlist();
			reinitializeSelectedItemTF();
		});

		viewHistoryButton.setOnAction(e -> {
			setViewGPInitBA();
			this.setLeft(viewGP);

			tableLabel.setText("Transaction History");
			initHistoryTable();
			viewHistory();
			scroll.setContent(itemTV);

			setActionGPBase();
			reinitializeSelectedItemTF();
		});

		viewOfferItemButton.setOnAction(e -> {
			setViewGPInitS();
			this.setLeft(viewGP);

			tableLabel.setText("Offered Items");
			initOfferItemTable();
			viewOfferItem();
			scroll.setContent(itemTV);

			setActionGPSellerOffers();
			reinitializeSelectedItemTF();
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

			setActionGPAdminRequests();
			reinitializeSelectedItemTF();
		});

		viewItemsButton.setOnAction(e -> {
			setViewGPInitial();
			this.setLeft(viewGP);

			tableLabel.setText("Items");
			initTable();
			viewItems();
			scroll.setContent(itemTV);

			setActionGPInitial();
			reinitializeSelectedItemTF();
		});

		viewItemsSellerButton.setOnAction(e -> {
			setViewGPInitial();
			this.setLeft(viewGP);

			tableLabel.setText("Items");
			initSellerItemsTable();
			viewSellerItems();
			scroll.setContent(itemTV);

			setActionGPInitial();
			reinitializeSelectedItemTF();
		});
		
		// ACTIONS
		
		purchaseItemButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
			purchaseItem(user.getId(), tempItemId);
			viewItems();
	        itemTV.refresh();
	        reinitializeSelectedItemTF();
		});

		makeOfferButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
		    new MakeOfferView(stage, tempItemId);
		});

		editItemButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
		    new EditItemView(stage, tempItemId);
		});

		deleteItemButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
		    int res = ItemController.deleteItem(tempItemId);
		    if (res > 0) {
		        viewSellerItems();
		        itemTV.refresh();
		        errorLabel.setText("Item deleted successfully");
		        reinitializeSelectedItemTF();
		    } else {
		        errorLabel.setText("Item failed to be deleted");
		    }
		});

		addToWishlistButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
			int res = WishlistController.addWishlist(user.getId(), tempItemId);
			if (res > 0) {				
				viewWishlist();
				itemTV.refresh();
		        errorLabel.setText("Item added to wishlist");
				reinitializeSelectedItemTF();
			} else {
		        errorLabel.setText("Failed to add item to wishlist");

			}
		});

		removeFromWishlistButton.setOnAction(e -> {
		    if(itemPicked() == 0) {
		        return;
		    }

		    // Get the wishlist ID of the selected item
		    int wishlistId = WishlistController.getWishlistIdForItem(tempItemId, user.getId());

		    if (wishlistId == -1) {
		        errorLabel.setText("Item not found in wishlist.");
		        return;
		    }

		    int res = WishlistController.removeWishlist(wishlistId);
		    if (res > 0) {
		        viewWishlist();  // Refresh the wishlist view
		        itemTV.refresh();
		        errorLabel.setText("Item removed from wishlist.");
		        reinitializeSelectedItemTF();
		    } else {
		        errorLabel.setText("Failed to remove item from wishlist.");
		    }
		});


		acceptOfferButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
			int res = WishlistController.addWishlist(user.getId(), tempItemId);
			if (res > 0) {				
				viewWishlist();
				itemTV.refresh();
		        errorLabel.setText("Item added to wishlist");
				reinitializeSelectedItemTF();
			} else {
		        errorLabel.setText("Failed to add item to wishlist");

			}

			viewOfferItem();
			itemTV.refresh();
			reinitializeSelectedItemTF();
		});

		declineOfferButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
			int res = WishlistController.addWishlist(user.getId(), tempItemId);
			if (res > 0) {				
				viewWishlist();
				itemTV.refresh();
		        errorLabel.setText("Item added to wishlist");
				reinitializeSelectedItemTF();
			} else {
		        errorLabel.setText("Failed to add item to wishlist");

			}
			
			viewOfferItem();
			itemTV.refresh();
			reinitializeSelectedItemTF();
		});

		approveItemButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
				
		    int res = ItemController.approveItem(tempItemId);
		    if (res > 0) {
		        viewRequestedItem();
		        itemTV.refresh();
		        errorLabel.setText("Item approved");
		        reinitializeSelectedItemTF();
		    } else {
		        errorLabel.setText("Item failed to be approved");
		    }
		});

		declineItemButton.setOnAction(e -> {
			if(itemPicked()==0) {
				return;
			}
			
			int res = WishlistController.addWishlist(user.getId(), tempItemId);
			if (res > 0) {				
				viewWishlist();
				itemTV.refresh();
		        errorLabel.setText("Item added to wishlist");
				reinitializeSelectedItemTF();
			} else {
		        errorLabel.setText("Failed to add item to wishlist");

			}
			
			reinitializeSelectedItemTF();
		});
	}
	
	private int itemPicked() {
		if(tempItemId==0 && selectedItemTF.getText().isBlank()) {
			errorLabel.setText("Please select an item");
			return 0;
		} else {
			return 1;
		}
	}
	
	private void reinitializeSelectedItemTF() {
		tempItemId = 0;
		selectedItemTF.setText("");
	}
	
	private void purchaseItem(int uid, int iid) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Purchase Confirmation");
	    alert.setHeaderText("Are you sure you want to purchase this item?");
	    alert.setContentText("This action cannot be undone.");

	    ButtonType buttonTypeYes = new ButtonType("Yes");
	    ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);

	    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

	    alert.showAndWait().ifPresent(response -> {
	        if (response == buttonTypeYes) {
			    errorLabel.setText(TransactionController.purchaseItem(user.getId(), tempItemId));
	        } else {
	            System.out.println("Item not purchased.");
	        }
	    });
	}

	private EventHandler<MouseEvent> tableClicked() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				TableSelectionModel<Item> tableSelectionMode = itemTV.getSelectionModel();
				tableSelectionMode.setSelectionMode(SelectionMode.SINGLE);

				Item item = tableSelectionMode.getSelectedItem();
				selectedItemTF.setText(item.getName());
				tempItemId = item.getId();
			}
		};
	}

	public HomeView(Stage stage) {
		this.stage = stage;
		this.user = (User) stage.getUserData();

		init(); setLayout(); setEvents();

		Scene scene = new Scene(this, 600, 400);
		stage.setScene(scene);
		stage.show();
	}


}
