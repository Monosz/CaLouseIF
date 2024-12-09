package controller;

import java.util.List;
import model.Item;
import model.Wishlist;

public class WishlistController {
	public static List<Item> viewWishlist(int userId) {
		return Wishlist.viewWishlist(userId);
	}

	public static int addWishlist(int userId, int itemId) {
		return Wishlist.addWishlist(userId, itemId);
	}

	public static int removeWishlist(int wishlistId) {
		return Wishlist.removeWishlist(wishlistId);
	}
}
