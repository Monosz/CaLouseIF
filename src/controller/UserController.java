package controller;

import model.User;

public class UserController {
	
	public static String register(String name, String password, String phone, String address, String role) {
		if (User.checkAccountValidation(name, password, phone, address)) {
			return "Invalid register value";
		}
		int res = User.register(name, password, phone, address, role);
		return (res == 0) ? "Register failed" : "Register success";
	}
	
	public static String login(String name, String password) {
		return User.login(name, password) == null ? "User not found" : "User found";
	}
	
}
