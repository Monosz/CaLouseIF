package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 
CREATE DATABASE calouseif;
USE calouseif;

DROP DATABASE calouseif

*/

public class Database {
	private final String USERNAME = "root",
			PASSWORD = "",
			HOST = "localhost:3306",
			DATABASE = "calouseif",
			URL = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	private static Database instance = null;
	private Connection con = null;
	
	private Database() {
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance() { 
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	} 

}
