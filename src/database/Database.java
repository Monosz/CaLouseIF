package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Singleton class for managing database connections and preparing SQL
 * statements.
 * <p>
 * Provides a method to get a single instance of the Database connection and
 * prepare SQL queries using the connection.
 * </p>
 */
public class Database {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String HOST = "localhost:3306";
    private static final String DATABASE = "calouseif";
    private static final String URL = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    private static Database instance = null;
    private Connection con = null;

    /**
     * Private constructor for initializing the database connection. Establishes a
     * connection to the database using JDBC.
     */
    private Database() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the single instance of the Database class.
     * 
     * @return the singleton instance of the Database class
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Prepares a SQL statement using the current database connection.
     *
     * @param query the SQL query to be prepared
     * @return the prepared statement for the query
     */
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
