package controller;

import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.Transaction;

public class TransactionController {
	public static int purchaseItem(int userId, int itemId) {
		return Transaction.purchaseItem(userId, itemId);
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

	public static void createTransaction(int transactionId) {
		Transaction.createTransaction(transactionId);
	}
}
