package view;

import java.util.List;
import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Item;
import model.User;

public class HomeView extends BorderPane {
	private Stage stage;
	private User user;
		
	private MenuBar menubar;
	private Menu menu;
	private MenuItem item;
	
	private ScrollPane scroll;
	private TableView<Item> itemTV;	
	private TableColumn<Item, String> nameColumn;
	private TableColumn<Item, String> categoryColumn;
	private TableColumn<Item, String> sizeColumn;
	private TableColumn<Item, String> priceColumn;

	private void viewItems() {
		List<Item> items = ItemController.viewItem();
		ObservableList<Item> itemOL = FXCollections.observableList(items);
		itemTV.setItems(itemOL);
	}
	
	private void init() {
		menubar = new MenuBar();
		menu = new Menu("View");
		item = new MenuItem("View item");

		scroll = new ScrollPane();
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
		viewItems();
	}
	
	private void setLayout() {
		this.setTop(menubar);
		this.setCenter(scroll);
		
		scroll.setContent(itemTV);
		
		menubar.getMenus().add(menu);
		menu.getItems().add(item);
	}
	
	
	private void setEvents() {
		
	}
	
	
	public HomeView(Stage stage, User user) {
		this.stage = stage;
		this.user = user;
		
		init(); setLayout(); setEvents();
		
		Scene scene = new Scene(this, 400, 300);
		stage.setScene(scene);
		stage.show();
	}
	
}
