package my.example.csvreader.writer.sqllite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.JournalMode;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteOpenMode;

public class MyMainClassSelectQuery {

	private static String SQLLITE_DB = "jdbc:sqlite:" + String.join(File.separator, System.getProperty("user.dir"),
																					"src",
																					"main",
																					"resources", 
			   																		"sample.db");
	private static String DB_TABLE_NAME = "records";
	
	public static void main(String[] args) throws IOException {
		new MyMainClassSelectQuery().startProcess();
	}
	
	private void startProcess() throws IOException {
		
		try (Connection connection = getConnection())
		{
			long start = 0L;
			long end = 0L;
			
			// Get Record Count inserted
			start = System.currentTimeMillis();
			ResultSet rs = runQuery(connection, "select count(*) as count from " + DB_TABLE_NAME);
			rs.next();
			end = System.currentTimeMillis();
			System.out.println("* Time taken to run select count query : " + new Float(end - start) / 1000 + " seconds.");
			System.out.println("> Record count in table : " + rs.getInt(1));
		}
		catch(SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		SQLiteConfig config = new SQLiteConfig();
		config.setJournalMode(JournalMode.OFF);
        config.setOpenMode(SQLiteOpenMode.FULLMUTEX);
        config.setBusyTimeout("60000");
        SQLiteDataSource ds = new SQLiteDataSource(config);
        ds.setUrl(SQLLITE_DB);
		return ds.getConnection();
	}
	
	private ResultSet runQuery(Connection connection, String query) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30);  // set timeout to 30 sec.
		ResultSet rs = null;
		
		try {
			rs = statement.executeQuery(query);
		}
		catch (SQLException e) {
			System.out.println("Error :" + e.getMessage());
			System.out.println("SQL :" + query);
			throw e;
		}
		return rs;
	}
}
