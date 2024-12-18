package controller;

import java.util.List;

import model.Item;
import model.Transaction;

public class ItemController {

	public static String uploadItem(String name, String size, String price, String category, int sellerId) {
		String validation = checkItemValidation(name, size, price, category);

		if(!validation.isEmpty()) {
			return validation;
		}

		int res = Item.uploadItem(name, size, Integer.parseInt(price), category, sellerId);
		return res == 0 ? "Upload item failed" : "Upload item succeed";
	}

	public static String editItem(int id, String name, String size, String price, String category) {
		String validation = checkItemValidation(name, size, price, category);

		if(!validation.isEmpty()) {
			return validation;
		}

		int res = Item.editItem(id, name, size, Integer.parseInt(price), category);
		return res == 0 ? "Failed to save changes" : "Changes saved";
	}

	public static int deleteItem(int id) {
		return Item.deleteItem(id);
	}

	public static List<Item> browseItem(String name) {
		return Item.browseItem(name);
	}

	public static List<Item> viewItem() {
		return Item.viewItem();
	}

	public static boolean isNumeric(String str) { 
		try {  
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}

	public static String checkItemValidation(String name, String size, String price, String category) {
		// check for empty fields
		if (name.isEmpty() || size.isEmpty() || price.isEmpty() || category.isEmpty()) {
			return "Please fill in all fields.";
		}

		// name & category validation 
		if (name.length() < 3) {
			return "Item name must be at least 3 characters long.";
		}
		if (category.length() < 3) {
			return "Category name must be at least 3 characters long.";
		}

		// price validation: cannot be 0, must be number
		if(!isNumeric(price)) {
			return "Price must be in number.";
		}
		if(Integer.parseInt(price)<=0) {
			return "Price can not be 0 or lower.";
		}

		return ""; 
	}

	public static List<Item> viewRequestedItem(int id, String status) {
		return Item.viewRequestedItem(id, status);
	}

	public static String offerPrice(int id, String price, int uid) {
		if(price.isEmpty()) {
			return "Please fill in the field";
		}
		if(!isNumeric(price)) {
			return "Price must be in number.";
		}
		if(Integer.parseInt(price)<=0) {
			return "Price can not be 0 or lower.";
		}

		Integer offerStatus = Item.getItem(uid).getOfferStatus();
		System.out.println("offer status" + offerStatus);
		System.out.println("offer price" + price);
		if (offerStatus != null && Integer.parseInt(price) <= offerStatus) {
			return "Price cannot be lower than the current offer price.";
		}

		int res = Item.offerPrice(id, Integer.parseInt(price), uid);
		return res == 0 ? "Failed to offer price" : "Offer placed successfully";
	}

	public static int acceptOffer(int id) {
		Item item = Item.getItem(id);
		int uid = item.getOffererId();
		return Transaction.createTransaction(uid, id);
	}

	public static int declineOffer(int id) {
		return Item.declineOffer(id);
	}

	public static int approveItem(int id) {
		return Item.approveItem(id);
	}

	public static int declineItem(int id) {
		return Item.declineItem(id);
	}

	public static List<Item> viewAcceptedItem(int id) {
		return Item.viewAcceptedItem(id);
	}

	//	userId = offererId
	public static List<Item> viewOfferItem(int userId) {
		return Item.viewOfferItem(userId);
	}

	public static List<Item> viewSellerItem(int userId) {
		return Item.viewSellerItem(userId);
	}
}
