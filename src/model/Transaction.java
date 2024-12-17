package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

/*

CREATE TABLE transactions (
    transaction_id 	INT	PRIMARY KEY	AUTO_INCREMENT,
    user_id 		INT NOT NULL,
    item_id 		INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);

DROP TABLE transactions;

 */

public class Transaction {
	private static Database db = Database.getInstance();

	private int transactionId, userId, itemId;

	public Transaction(int transactionId, int userId, int itemId) {
		this.transactionId = transactionId;
		this.userId = userId;
		this.itemId = itemId;
	}

	// ==================================================
	// =                     LOGIC                      =
	// ==================================================

	public static int purchaseItem(int userId, int itemId) {
		String query = "INSERT INTO transactions(user_id, item_id) "
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

	public static List<Transaction> viewHistory(int userId) {
		String query = "SELECT * FROM transactions "
				+ "WHERE user_id = ?";
		PreparedStatement ps = db.prepareStatement(query);

		List<Transaction> list = new ArrayList<>();
		try {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int tId = rs.getInt("transaction_id"),
						uId = rs.getInt("user_id"),
						iId = rs.getInt("item_id");

				list.add(new Transaction(tId, uId, iId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static int createTransaction(int userId, int itemId) {
		String query = "INSERT INTO transactions(user_id, item_id) "
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

	// ==================================================
	// =                 GETTER-SETTER                  =
	// ==================================================

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
