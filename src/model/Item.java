package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

/*

CREATE TABLE items (
	item_id 		INT PRIMARY KEY	AUTO_INCREMENT,
	item_name		VARCHAR(255)	NOT NULL,
	item_size 		VARCHAR(255) 	NOT NULL,
	item_price 		INT 	NOT NULL,
	item_category 		VARCHAR(255) 	NOT NULL,
	item_status 		VARCHAR(10) 	NOT NULL,
	item_wishlist 		INT 	NOT NULL,
	item_offer_status	INT	NOT NULL
);

DROP TABLE items;

 */

// TODO: Verify item model requirement @lab_assistant

public class Item {
	private static Database db = Database.getInstance();

	private int id;
	private String name, size;
	private int price;
	private String category, status;
	private int wishlist, offerStatus;

	public Item(int id, String name, String size, int price, String category) {
		this.id = id;
		this.name = name;
		this.size = size;
		this.price = price;
		this.category = category;
	}

	public Item(int id, String name, String size, int price, String category, String status, int wishlist,
			int offerStatus) {
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

	public static int uploadItem(String name, String size, int price, String category) {
		String query = "INSERT INTO items(item_name, item_size, item_price, item_category, item_status, item_wishlist, item_offer_status) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = db.prepareStatement(query);

		int res = 0;
		try {
			ps.setString(1, name);
			ps.setString(2, size);
			ps.setInt(3, price);
			ps.setString(4, category);
			ps.setString(5, "PENDING");
			ps.setInt(6, 0);
			ps.setInt(7, 0);

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static int editItem(int id, String name, String size, int price, String category) {
		String query = "UPDATE items SET item_name = ?, item_size = ?, item_price = ?, item_category = ? "
				+ "WHERE item_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		int res = 0;
		try {
			ps.setString(1, name);
			ps.setString(2, size);
			ps.setInt(3, price);
			ps.setString(4, category);
			ps.setInt(5, id);

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static int deleteItem(int id) {
		String query = "DELETE FROM items WHERE item_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		int res = 0;
		try {
			ps.setInt(1, id);

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static List<Item> browseItem(String name) {
		String query = "SELECT * FROM items WHERE item_name = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Item> list = new ArrayList<>();
		try {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int iId = rs.getInt("item_id"), iPrice = rs.getInt("item_price"),
						iOfferStatus = rs.getInt("item_offer_status"), iWishlist = rs.getInt("item_wishlist");
				String iName = rs.getString("item_name"), iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category"), iStatus = rs.getString("item_status");

				list.add(new Item(iId, iName, iSize, iPrice, iCategory, iStatus, iWishlist, iOfferStatus));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<Item> viewItem() {
		String query = "SELECT * FROM items WHERE item_status = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Item> list = new ArrayList<>();
		try {
			ps.setString(1, "ACCEPTED");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int iId = rs.getInt("item_id"), iPrice = rs.getInt("item_price"),
						iOfferStatus = rs.getInt("item_offer_status"), iWishlist = rs.getInt("item_wishlist");
				String iName = rs.getString("item_name"), iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category"), iStatus = rs.getString("item_status");

				list.add(new Item(iId, iName, iSize, iPrice, iCategory, iStatus, iWishlist, iOfferStatus));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static boolean checkItemValidation(String name, String size, int price, String category) {
		if (name.isEmpty() || name.length() < 3)
			return false;
		else if (size.isEmpty())
			return false;
		else if (price == -1)
			return false;
		else if (category.isEmpty() || category.length() < 3)
			return false;
		return true;
	}

	public static List<Item> viewRequestedItem(int id, String status) {
		String query = "SELECT * FROM items WHERE item_status = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Item> list = new ArrayList<>();
		try {
			ps.setString(1, "PENDING");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int iId = rs.getInt("item_id"), iPrice = rs.getInt("item_price"),
						iOfferStatus = rs.getInt("item_offer_status"), iWishlist = rs.getInt("item_wishlist");
				String iName = rs.getString("item_name"), iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category"), iStatus = rs.getString("item_status");
				list.add(new Item(iId, iName, iSize, iPrice, iCategory, iStatus, iWishlist, iOfferStatus));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
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

	public static int approveItem(int id) {
		String query = "UPDATE items SET item_status = ? WHERE item_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		int res = 0;
		try {
			ps.setString(1, "ACCEPTED");
			ps.setInt(2, id);

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static int declineItem(int id) {
		String query = "UPDATE items " + "SET item_status = ? WHERE item_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		int res = 0;
		try {
			ps.setString(1, "DECLINED");
			ps.setInt(2, id);

			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static List<Item> viewAcceptedItem(int id) {
		// TODO: Dupe functionality of viewItem() ?

		String query = "SELECT * FROM items WHERE item_status = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Item> list = new ArrayList<>();
		try {
			ps.setString(1, "ACCEPTED");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int iId = rs.getInt("item_id"), iPrice = rs.getInt("item_price"),
						iOfferStatus = rs.getInt("item_offer_status"), iWishlist = rs.getInt("item_wishlist");
				String iName = rs.getString("item_name"), iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category"), iStatus = rs.getString("item_status");

				list.add(new Item(iId, iName, iSize, iPrice, iCategory, iStatus, iWishlist, iOfferStatus));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void viewOfferItem(int userId) {
		// TODO;
	}

	// ===========================================================================
	// = ADDITIONAL METHODS (not in class diagram, but in sequence diagram(s)=
	// ===========================================================================
	// (getItem(by Id) method, found in view wishlist sequence diagram, also needed in view history)
	public static Item getItem(int id) { 
		String query = "SELECT * FROM items WHERE item_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		Item item = null;
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int iId = rs.getInt("item_id"), iPrice = rs.getInt("item_price"),
						iOfferStatus = rs.getInt("item_offer_status"), iWishlist = rs.getInt("item_wishlist");
				String iName = rs.getString("item_name"), iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category"), iStatus = rs.getString("item_status");

				item = new Item(iId, iName, iSize, iPrice, iCategory, iStatus, iWishlist, iOfferStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;
	}

	// ==================================================
	// = GETTER-SETTER =
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

	public int getWishlist() {
		return wishlist;
	}

	public void setWishlist(int wishlist) {
		this.wishlist = wishlist;
	}

	public int getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(int offerStatus) {
		this.offerStatus = offerStatus;
	}

}
