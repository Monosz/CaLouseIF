package controller;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Transaction;

public class TransactionController {
	public static String purchaseItem(int userId, int itemId) {
		// generate transaction
		int res = Transaction.createTransaction(userId, itemId);
		
		if(res == 0 ) {
			// purchase failed
			return "Transaction failed to create";
		} else {
			// purchase succeed -> delete item from every user's wishlist
			Item.updateStatusToPurchased(itemId);
			Item.deleteItemFromWishlists(itemId);
			return "You've successfully purchased item";
		}
	}

	public static List<Item> viewHistory(int userId) {
		List<Transaction> transactions = Transaction.viewHistory(userId);
		List<Item> history = new ArrayList<>(); 

		for(Transaction transaction : transactions) {
			Item item = Item.getItem(transaction.getItemId());
			item.setId(transaction.getTransactionId());
			history.add(item);
		}

		return history;
	}

	public static int createTransaction(int userId, int itemId) {
		return Transaction.createTransaction(userId, itemId);
	}
}
