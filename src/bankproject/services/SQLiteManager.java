package bankproject.services;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {
private static SQLiteManager INSTANCE = new SQLiteManager();
	
	/**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + getSQLiteDBPath();
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static SQLiteManager getINSTANCE() {
		return INSTANCE;
	}


	public static void setINSTANCE(SQLiteManager iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
    
    public static Connection getConnection () throws SQLException {
    	String url = "jdbc:sqlite:" + getSQLiteDBPath();
        // create a connection to the database
    	
        return DriverManager.getConnection(url);
    }
    
    public static String getSQLiteDBPath () {
		String fs = System.getProperty("file.separator");
		String dirPath = System.getProperty("user.dir") + fs + "db"+ fs + "bank";
		File dir = new File(dirPath);
		if (!(dir.exists() && dir.isDirectory())) { // NOT (A && B) = NOT A || NOT B
			dir.mkdirs();
		}
		
    	return dirPath + fs + "data.db";
    }
	
	
	public static void main(String[] args) {
		System.out.println(getSQLiteDBPath());
	}

}
