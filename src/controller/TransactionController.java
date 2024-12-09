package controller;

import model.Transaction;

public class TransactionController {
	public static int purchaseItem(int userId, int itemId) {
		return Transaction.purchaseItem(userId, itemId);
	}

//	transaction id + item name, category, size, and price TODO: shi dont make sense cuh
//	public static List<Object> viewHistory(int userId) {
//		List<Transaction> transactions = Transaction.viewHistory(userId);
//		
//		List<Object> history = null; 
//		for(Transaction transaction : transactions) {
//			history.add(transaction.getTransactionId(), Item.getItem(transaction.getItemId()));
//		}
//		
//		return history;
//	}

	public static void createTransaction(int transactionId) {
		Transaction.createTransaction(transactionId);
	}
}
