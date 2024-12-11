package controller;

import java.util.List;

import model.Item;

public class ItemController {
	//	public static int uploadItem(String name, String size, int price, String category) {
	//		TODO
	//	}

	//	public static int editItem(int id, String name, String size, int price, String category) {
	//		TODO
	//	}

	public static int deleteItem(int id) {
		return Item.declineItem(id);
	}

	public static List<Item> browseItem(String name) {
		return Item.browseItem(name);
	}

	public static List<Item> viewItem() {
		return Item.viewItem();
	}

	//	public static boolean checkItemValidation(String name, String size, int price, String category) {
	//		TODO
	//	}

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

	public static List<Item> viewOfferItem(int userId) {
		return Item.viewOfferItem(userId);
	}
}
