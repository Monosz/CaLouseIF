package model;

import database.Database;

/*

CREATE TABLE items (
	item_id 			INT 			PRIMARY KEY	AUTO_INCREMENT,
	item_name 			VARCHAR(255)	NOT NULL,
	item_size 			VARCHAR(255) 	NOT NULL,
	item_price 			INT 			NOT NULL,
	item_category 		VARCHAR(255) 	NOT NULL,
	item_status 		VARCHAR(10) 	NOT NULL,
	item_wishlist 		VARCHAR(10) 	NOT NULL,
	item_offer_status	VARCHAR(10) 	NOT NULL
);

DROP TABLE items;

*/

// TODO: Verify item model requirement @lab_assistant

public class Item {
	private static Database db = Database.getInstance();
	
	private int id;
	private String name, size;
	private int price;
	private String category, status, wishlist, offerStatus;
	
	public Item(int id, String name, String size, int price, String category, String status, String wishlist,
			String offerStatus) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.price = price;
		this.category = category;
		this.status = status;
		this.wishlist = wishlist;
		this.offerStatus = offerStatus;
	}
	
	// ==================================================
	// =                     LOGIC                      =
	// ==================================================
	
	public static void uploadItem(String name, String category, String size, int price) {
		// TODO:
	}
	
	public static void editItem(int id, String name, String category, String size, int price) {
		// TODO:
	}
	
	public static void deleteItem(int id) {
		// TODO:
	}
	
	public static void browseItem(String name) {
		// TODO:
	}
	
	public static void viewItem() {
		// TODO: 
	}
	
	public static void checkItemValidation(String name, String category, String size, int price) {
		// TODO: 
	}
	
	public static void viewRequestedItem(int id, String status) {
		// TODO:
	}
	
	public static void offerPrice(int id, int price) {
		// TODO:
	}
	
	public static void acceptOffer(int id) {
		// TODO:
	}
	
	public static void declineOffer(int id) {
		// TODO:
	}
	
	public static void approveItem(int id) {
		// TODO:
	}
	
	public static void declineItem(int id) {
		// TODO:
	}
	
	public static void viewAcceptedItem(int id) {
		// TODO:
	}
	
	public static void viewOfferItem(int userId) {
		// TODO;
	}
	
	// ==================================================
	// =                 GETTER-SETTER                  =
	// ==================================================

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWishlist() {
		return wishlist;
	}

	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	
}
