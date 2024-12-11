package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

/*

CREATE TABLE users (
	user_id 		INT 			PRIMARY KEY AUTO_INCREMENT,
	user_name 		VARCHAR(50) 	UNIQUE 		NOT NULL,
	user_password 	VARCHAR(50) 	NOT NULL,
	user_phone 		VARCHAR(15) 	NOT NULL,
	user_address 	VARCHAR(255)	NOT NULL,
	user_role 		VARCHAR(10) 	NOT NULL
);

INSERT INTO users(user_name, user_password, user_phone, user_address, user_role)
VALUES
	("Dummy Buyer", "@buyer123", "+62987654321", "124 Conch Street", "Buyer"),
	("Dummy Seller", "@seller123", "+62123456789", "123 Sesame Street", "Seller");

DROP TABLE users;

 */

public class User {
	private static Database db = Database.getInstance();

	private int id;
	private String name, password, phone, address, role;

	public User(int id, String name, String password, String phone, String address, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}

	// ==================================================
	// =                     LOGIC                      =
	// ==================================================

	public static int register(String name, String password, String phone, String address, String role) {
		int validation = checkAccountValidation(name, password, phone, address);

		if(validation<0) {
			return validation;
		}

		String query = "INSERT INTO users(user_name, user_password, user_phone, user_address, user_role) "
				+ "VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = db.prepareStatement(query);

		// Remove spaces
		phone = phone.replaceAll("\\s+", "");

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

	private static int checkAccountValidation(String name, String password, String phone, String address) {
		//		name validation (must be unique)
		if (!isUsernameUnique(name)) {
			return -1;
		}

		return 1;
	}

	private static boolean isUsernameUnique(String name) {
		String query = "SELECT * FROM users WHERE user_name = ?";
		PreparedStatement ps = db.prepareStatement(query);
		try {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true; 
	}

	public static User login(String name, String password) {
		if (name.equals("admin") && password.equals("admin")) {
			return new User(0, "admin", "admin", null, null, "Admin");
		}

		String query = "SELECT * FROM users "
				+ "WHERE user_name = ? "
				+ "AND user_password = ?";
		PreparedStatement ps = db.prepareStatement(query);

		User user = null;
		try {
			ps.setString(1, name);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				name = rs.getString("user_name");
				password = rs.getString("user_password");
				String phone = rs.getString("user_phone"),
						address = rs.getString("user_address"),
						role = rs.getString("user_role");

				user = new User(id, name, password, phone, address, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}


	// ==================================================
	// =                 GETTER-SETTER                  =
	// ==================================================

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
