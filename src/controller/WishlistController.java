package controller;

import java.util.List;
import model.Item;
import model.Wishlist;

/**
 * Handles operations related to the user's wishlist.
 * <p>
 * Provides methods to view the wishlist, add items to it, and remove items from
 * it. Interacts with the {@link Wishlist} model to perform said operations.
 * </p>
 * 
 * @see Wishlist
 * @see Item
 */
public class WishlistController {

	/**
	 * Retrieves the wishlist items for a specific user.
	 * 
	 * @param userId the ID of the user whose wishlist is to be retrieved
	 * @return a list of {@link Item} objects representing the user's wishlist
	 */
	public static List<Item> viewWishlist(int userId) {
		return Wishlist.viewWishlist(userId);
	}

	/**
	 * Adds an item to the user's wishlist.
	 * 
	 * @param userId the ID of the user adding the item
	 * @param itemId the ID of the item to be added
	 * @return an integer indicating the result of the operation (1 if successful, 0
	 *         otherwise)
	 */
	public static int addWishlist(int userId, int itemId) {
		return Wishlist.addWishlist(userId, itemId);
	}

	/**
	 * Removes an item from the wishlist.
	 * 
	 * @param wishlistId the ID of the wishlist entry to be removed
	 * @return an integer indicating the result of the operation (1 if successful, 0
	 *         otherwise)
	 */
	public static int removeWishlist(int wishlistId) {
		return Wishlist.removeWishlist(wishlistId);
	}
}
