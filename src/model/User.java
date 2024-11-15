package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

public class User {
	private static Database db = Database.getInstance();	
	private String name, password, phone, address, role;

	public User(String name, String password, String phone, String address, String role) {
		super();
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	
	public static int register(String name, String password, String phone, String address, String role) {
		String query = "INSERT INTO users(user_name, user_password, user_phone, user_address, user_role)"
					 + "VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = db.prepareStatement(query);
		
		int res = 0;
		try {
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, phone);
			ps.setString(4, address);
			ps.setString(5, role);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static User login(String name, String password) {
		String query = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";
		PreparedStatement ps = db.prepareStatement(query);
		
		User user = null;
		try {
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				int id = rs.getInt("user_id");
				name = rs.getString("user_name");
				password = rs.getString("user_password");
				String phone = rs.getString("user_phone"),
						address = rs.getString("user_address"),
						role = rs.getString("user_role");
				
				user = new User(name, password, phone, address, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static boolean checkAccountValidation(String name, String password, String phone, String address, String role) {
		if (name.isEmpty() || name.length() < 3) { // TODO: Validate unique username
			return false;
		} else if (password.isEmpty() || password.length() < 8) { // TODO; Validate password contain special char
			return false;
		} else if (!phone.startsWith("+62") || phone.substring(3).length() == 9) {
			return false;
		} else if (address.isEmpty()) {
			return false;
		} else if (!role.equals("seller")) {
			return false;
		}
		return true;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
