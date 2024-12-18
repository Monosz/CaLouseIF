package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

/**
 * Represents a transaction where a user purchases an item.
 * <p>
 * Provides methods for creating transactions and retrieving transaction
 * history.
 * <p>
 * Each transaction is linked to a specific user and item.
 * </p>
 * 
 * @see sql/<a href=
 *      "https://github.com/Monosz/CaLouseIF/blob/master/sql/initialize.sql#L39">initialize.sql</a>
 *      for the SQL query used to create the transactions table.
 */
public class Transaction {
	private int transactionId;
	private int userId;
	private int itemId;

	private static Database db = Database.getInstance();

	/**
	 * Constructs a new Transaction object.
	 *
	 * @param transactionId the unique ID of the transaction
	 * @param userId        the ID of the user who performed the transaction
	 * @param itemId        the ID of the item involved in the transaction
	 */
	public Transaction(int transactionId, int userId, int itemId) {
		this.transactionId = transactionId;
		this.userId = userId;
		this.itemId = itemId;
	}

	// ===================================================
	// ====================== LOGIC ======================
	// ===================================================

	/**
	 * Adds a new transaction in the database.
	 * 
	 * @param userId the ID of the user making the purchase
	 * @param itemId the ID of the item being purchased
	 * @return the number of rows affected (1 if successful, 0 otherwise)
	 */
	public static int purchaseItem(int userId, int itemId) {
		String query = "INSERT INTO transactions(user_id, item_id) VALUES(?, ?)";
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
	 * Retrieves the transaction history for a specific user.
	 * 
	 * @param userId the ID of the user whose transaction history is to be retrieved
	 * @return a list of {@code Transaction} objects representing the user's
	 *         transaction history
	 */
	public static List<Transaction> viewHistory(int userId) {
		String query = "SELECT * FROM transactions WHERE user_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Transaction> list = new ArrayList<>();
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int tId = rs.getInt("transaction_id"), uId = rs.getInt("user_id"), iId = rs.getInt("item_id");

				list.add(new Transaction(tId, uId, iId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Adds a new transaction in the database.
	 * 
	 * @param userId the ID of the user making the purchase
	 * @param itemId the ID of the item being purchased
	 * @return the number of rows affected (1 if successful, 0 otherwise)
	 */
	public static int createTransaction(int userId, int itemId) {
		String query = "INSERT INTO transactions(user_id, item_id) VALUES(?, ?)";
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

	// ===================================================
	// ================== GETTER-SETTER ==================
	// ===================================================

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
