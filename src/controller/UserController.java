package controller;

import model.User;

/**
 * Controller for managing user operations such as registration and login.
 * <p>
 * Validates user input and interacts with the {@link User} model for data
 * handling.
 * </p>
 * 
 * @see User
 */
public class UserController {

	/**
	 * Handles user registration by validating the input and passing it to the
	 * {@link User} model layer.
	 *
	 * @param name     the user's name
	 * @param password the user's password
	 * @param phone    the user's phone number
	 * @param address  the user's address
	 * @param role     the user's role
	 * @return 1 if registration is successful, -1 if name already exists, or 0 if
	 *         the database operation fails
	 */
	public static String register(String name, String password, String phone, String address, String role) {
		String validation = checkAccountValidation(name, password, phone, address, role);

		if (!validation.isEmpty()) {
			return validation;
		}

		int res = User.register(name, password, phone, address, role);

		if (res < 0) {
			return "Username is already taken.";
		} else if (res == 0) {
			return "Register failed";
		} else {
			return "Register success";
		}
	}

	/**
	 * Handles user login by passing it to the {@link User} model layer.
	 *
	 * @param name     the user's username
	 * @param password the user's password
	 * @return a {@link User} object if authentication is successful, or
	 *         {@code null} if it fails
	 */
	public static User login(String name, String password) {
		return User.login(name, password);
	}

	/**
	 * Validates the input fields for user registration.
	 *
	 * @param name     the user's name
	 * @param password the user's password
	 * @param phone    the user's phone number
	 * @param address  the user's address
	 * @param role     the user's role
	 * @return an empty string if validation passes, or an error message if
	 *         validation fails
	 */
	private static String checkAccountValidation(String name, String password, String phone, String address,
			String role) {
		// check for empty fields
		if (name.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty() || role.isEmpty()) {
			return "Please fill in all fields.";
		}

		// name validation
		if (name.length() < 3) {
			return "Username must be at least 3 characters long.";
		}

		// password validation
		if (password.length() < 8 || !includeSpecials(password)) {
			return "Password must be at least 8 characters long and include a special character (!, @, #, $, %, ^, &, *).";
		}

		// phone number validation
		phone = phone.replaceAll("\\s+", ""); // Remove spaces
		if (!phone.startsWith("+62") || phone.substring(3).length() < 9) {
			return "Phone number must start with +62 and be at least 10 digits long.";
		}

		return "";
	}

	/**
	 * Checks if a string contains at least one special character. Special
	 * characters are defined as: (!, @, #, $, %, ^, &, *).
	 *
	 * @param string the string to check
	 * @return {@code true} if the string contains at least one special character,
	 *         {@code false} otherwise
	 */
	private static boolean includeSpecials(String string) {
		String[] specials = new String[] { "!", "@", "#", "$", "%", "^", "&", "*" };
		for (String special : specials) {
			if (string.contains(special)) {
				return true;
			}
		}
		return false;
	}
}
