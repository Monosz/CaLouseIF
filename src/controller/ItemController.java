package controller;

import java.util.List;

import model.Item;

public class ItemController {
	
	public static String uploadItem(String name, String size, String price, String category, int sellerId) {
		String validation = checkItemValidation(name, size, price, category);

		if(!validation.isEmpty()) {
			return validation;
		}
		
		int res = Item.uploadItem(name, size, Integer.parseInt(price), category, sellerId);
		return res == 0 ? "Upload item failed" : "Upload item succeed";
	}

	public static int editItem(int id, String name, String size, int price, String category) {
		return Item.editItem(id, name, size, price, category);
	}

	public static int deleteItem(int id) {
		return Item.declineItem(id);
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

	//	public static void offerPrice(int id, int price) {
	//		TODO
	//	}

	public static void acceptOffer(int id) {
		Item.acceptOffer(id);
	}

	//	public static void declineOffer(int id) {
	// TODO:
	//	}

	public static int approveItem(int id) {
		return Item.approveItem(id);
	}

	//	public static int declineItem(int id) {
	//		TODO
	//	}

	public static List<Item> viewAcceptedItem(int id) {
		return Item.viewAcceptedItem(id);
	}

	//	userId = offererId
	public static List<Item> viewOfferItem(int userId) {
		return Item.viewOfferItem(userId);
	}
}
