package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

/**
 * Represents a user in the system.
 * <p>
 * Provides functionality for user registration and login.
 * </p>
 * 
 * @see sql/<a href=
 *      "https://github.com/Monosz/CaLouseIF/blob/master/sql/initialize.sql#L4">initialize.sql</a>
 *      for the SQL query used to create the users table
 */
public class User {
	private int id;
	private String name;
	private String password;
	private String phone;
	private String address;
	private String role;

	private static final Database db = Database.getInstance();

	/**
	 * Constructs a new User object.
	 *
	 * @param id       the user's unique identifier
	 * @param name     the user's name
	 * @param password the user's password
	 * @param phone    the user's phone number
	 * @param address  the user's address
	 * @param role     the user's role (e.g., "Buyer", "Seller", "Admin")
	 */
	public User(int id, String name, String password, String phone, String address, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}

	// ===================================================
	// ====================== LOGIC ======================
	// ===================================================

	/**
	 * Registers a new user in the system.
	 *
	 * @param name     the user's name
	 * @param password the user's password
	 * @param phone    the user's phone number
	 * @param address  the user's address
	 * @param role     the user's role
	 * @return 1 if registration is successful, -1 if name already exists, or 0 if
	 *         the database operation fails
	 */
	public static int register(String name, String password, String phone, String address, String role) {
		if (!isUsernameUnique(name)) {
			return -1;
		}

		String query = "INSERT INTO users(user_name, user_password, user_phone, user_address, user_role) "
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

	/**
	 * Checks whether a username is unique in the database.
	 *
	 * @param name the username to check
	 * @return {@code true} if the username is unique, {@code false} otherwise
	 */
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

	/**
	 * Authenticates a user with the provided username and password.
	 *
	 * @param name     the user's username
	 * @param password the user's password
	 * @return a {@link User} object if authentication is successful, or {@code null} if it
	 *         fails
	 */
	public static User login(String name, String password) {
		if (name.equals("admin") && password.equals("admin")) {
			return new User(0, "admin", "admin", null, null, "Admin");
		}

		String query = "SELECT * FROM users " + "WHERE user_name = ? " + "AND user_password = ?";
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
				String phone = rs.getString("user_phone"), address = rs.getString("user_address"),
						role = rs.getString("user_role");

				user = new User(id, name, password, phone, address, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	// ===================================================
	// ================== GETTER-SETTER ==================
	// ===================================================

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
