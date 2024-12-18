package controller;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Transaction;

/**
 * Handles operations related to transactions.
 * <p>
 * Provides methods to purchase items, view transaction history, and create new
 * transactions. Interacts with the {@link Transaction} and {@link Item} models.
 * </p>
 * 
 * @see Transaction
 * @see Item
 */
public class TransactionController {

	/**
	 * Creates a new transaction for a user purchasing an item.
	 * 
	 * @param userId the ID of the user making the purchase
	 * @param itemId the ID of the item being purchased
	 * @return a message indicating the result of the transaction
	 */
	public static String purchaseItem(int userId, int itemId) {
		// generate transaction
		int res = Transaction.createTransaction(userId, itemId);

		if (res == 0) {
			// purchase failed
			return "Transaction failed to create";
		} else {
			// purchase succeed -> delete item from every user's wishlist
			Item.updateStatusToPurchased(itemId);
			Item.deleteItemFromWishlists(itemId);
			return "You've successfully purchased item";
		}
	}

	/**
	 * Retrieves the transaction history for a specific user.
	 * 
	 * @param userId the ID of the user whose transaction history is to be retrieved
	 * @return a list of {@link Item} objects representing the user's transaction
	 *         history
	 */
	public static List<Item> viewHistory(int userId) {
		List<Transaction> transactions = Transaction.viewHistory(userId);
		List<Item> history = new ArrayList<>();

		for (Transaction transaction : transactions) {
			Item item = Item.getItem(transaction.getItemId());
			item.setId(transaction.getTransactionId());
			history.add(item);
		}

		return history;
	}

	/**
	 * Creates a new transaction for a user purchasing an item.
	 * 
	 * @param userId the ID of the user making the purchase
	 * @param itemId the ID of the item being purchased
	 * @return an integer indicating the result of the transaction creation (1 if
	 *         successful, 0 otherwise)
	 */
	public static int createTransaction(int userId, int itemId) {
		return Transaction.createTransaction(userId, itemId);
	}
}
