package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

/**
 * Represents a user's wishlist in the application.
 * <p>
 * Provides methods to view, add, and remove items from a wishlist stored in the
 * database.
 * </p>
 * 
 * @see sql/<a href=
 *      "https://github.com/Monosz/CaLouseIF/blob/master/sql/initialize.sql#L30">initialize.sql</a>
 *      for the SQL query used to create the wishlists table
 */
public class Wishlist {
	private int wishlistId;
	private int userId;
	private int itemId;

	private static final Database db = Database.getInstance();

	/**
	 * Constructs a new Wishlist object.
	 *
	 * @param wishlistId the unique identifier for the wishlist
	 * @param userId     the ID of the user who owns the wishlist
	 * @param itemId     the ID of the item in the wishlist
	 */
	public Wishlist(int wishlistId, int userId, int itemId) {
		this.wishlistId = wishlistId;
		this.userId = userId;
		this.itemId = itemId;
	}

	// ===================================================
	// ====================== LOGIC ======================
	// ===================================================

	/**
	 * Retrieves the list of items in a user's wishlist from the database.
	 *
	 * @param userId the ID of the user whose wishlist is being retrieved
	 * @return a list of {@code Item} objects representing the items in the wishlist
	 */
	public static List<Item> viewWishlist(int userId) {
		String query = "SELECT i.item_id, i.item_name, i.item_size, i.item_price, i.item_category "
				+ "FROM wishlists w JOIN items i ON w.item_id = i.item_id " + "WHERE user_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Item> list = new ArrayList<>();
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int iId = rs.getInt("item_id"), iPrice = rs.getInt("item_price");
				String iName = rs.getString("item_name"), iSize = rs.getString("item_size"),
						iCategory = rs.getString("item_category");

				list.add(new Item(iId, iName, iSize, iPrice, iCategory));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Adds an item to a user's wishlist in the database.
	 *
	 * @param userId the ID of the user
	 * @param itemId the ID of the item to be added
	 * @return the number of rows affected (1 if successful, 0 otherwise)
	 */
	public static int addWishlist(int userId, int itemId) {
		String query = "INSERT INTO wishlists(user_id, item_id) " + "VALUES(?, ?)";
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

	/**
	 * Removes an item from a user's wishlist in the database.
	 *
	 * @param wishlistId the ID of the wishlist entry to be removed
	 * @return the number of rows affected (1 if successful, 0 otherwise)
	 */
	public static int removeWishlist(int wishlistId) {
		String query = "DELETE FROM wishlists" + "WHERE wishlist_id = ?";
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

	// ===================================================
	// ================== GETTER-SETTER ==================
	// ===================================================

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
