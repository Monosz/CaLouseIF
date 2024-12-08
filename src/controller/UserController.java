package controller;

import model.User;

public class UserController {
	
	public static String register(String name, String password, String phone, String address, String role) {
		String validation = checkAccountValidation(name, password, phone, address, role);
		
		if(!validation.isEmpty()) {
			return validation;
		}
		
		int res = User.register(name, password, phone, address, role);
		
		if(res<0) {
			return "Username is already taken.";
		} else if(res==0) {
			return "Register failed";
		} else {
			return "Register success";
		}	
	}
	
	public static User login(String name, String password) {	
		return User.login(name, password);
	}
	
	private static String checkAccountValidation(String name, String password, String phone, String address, String role) {
		// check for empty fields
	    if (name.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty() || role.isEmpty()) {
	        return "Please fill in all fields.";
	    }

	    // name validation
	    if (name.length() < 3) {
	        return "Username must be at least 3 characters long.";
	    }

	    // password validation (TODO: include specials)
	    if (password.length() < 8) {
	        return "Password must be at least 8 characters long and include a special character (!, @, #, $, etc.).";
	    }

	    // phone number validation
	    if (!phone.startsWith("+62") || phone.substring(3).length() < 9) {
	        return "Phone number must start with +62 and be at least 10 digits long.";
	    }

	    return ""; 
	}
	
}
