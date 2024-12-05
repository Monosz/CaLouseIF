package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

/*

CREATE TABLE wishlists (
    wishlist_id	INT	PRIMARY KEY	AUTO_INCREMENT,
    user_id 	INT NOT NULL,
    item_id 	INT NOT NULL,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);

DROP TABLE wishlists;

*/

public class Wishlist {
	private static Database db = Database.getInstance();
	
	private int wishlistId, userId, itemId;
	
	public Wishlist(int wishlistId, int userId, int itemId) {
		this.wishlistId = wishlistId;
		this.userId = userId;
		this.itemId = itemId;
	}
	
	// ==================================================
	// =                     LOGIC                      =
	// ==================================================

	public static List<Item> viewWishlist(int userId) {
		String query = "SELECT i.id, i.name, i.size, i.price, i.category "
					 + "FROM wishlists w JOIN items i ON w.item_id = i.item_id "
					 + "WHERE user_id = ?";
		PreparedStatement ps = db.prepareStatement(query);
		
		List<Item> list = new ArrayList<>();
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int iId = rs.getInt("item_id"),
						iPrice = rs.getInt("item_price");
				String iName = rs.getString("item_name"),
						iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category");
				
				list.add(new Item(iId, iName, iSize, iPrice, iCategory));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int addWishlist(int userId, int itemId) {
		String query = "INSERT INTO wishlists(user_id, item_id) "
					 + "VALUES(?, ?)";
		PreparedStatement ps = db.prepareStatement(query);
		
		int res = 0;
		try {
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static int removeWishlist(int wishlistId) {
		String query = "DELETE FROM wishlists"
					 + "WHERE wishlist_id = ?";
		PreparedStatement ps = db.prepareStatement(query);
		
		int res = 0;
		try {
			ps.setInt(1, wishlistId);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	// ==================================================
	// =                 GETTER-SETTER                  =
	// ==================================================
	
	public int getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
