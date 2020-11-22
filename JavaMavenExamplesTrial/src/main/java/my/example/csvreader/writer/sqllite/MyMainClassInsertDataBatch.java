package my.example.csvreader.writer.sqllite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConfig.JournalMode;
import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteOpenMode;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class MyMainClassInsertDataBatch {

	private static String INPUT_FILE = String.join(File.separator, System.getProperty("user.dir"), 
																   "src", 
																   "main", 
																   "resources", 
//																   "sample_10_rec.dat");
																   "sample_1M_rec.dat");
	private static char COL_SEPARATOR = 0x07;
	private static String SQLLITE_DB = "jdbc:sqlite:" + String.join(File.separator, System.getProperty("user.dir"),
																					"src",
																					"main",
																					"resources", 
			   																		"sample.db");
	private static String DB_TABLE_NAME = "records";
	
	// To run batch update queries.
	private PreparedStatement batchStmt = null;
	
	public static void main(String[] args) throws IOException {
		new MyMainClassInsertDataBatch().startProcess();
	}
	
	private void startProcess() throws IOException {
		final CSVParser parser = new CSVParserBuilder()
				.withSeparator(COL_SEPARATOR)
				.withIgnoreQuotations(true)
				.build();
		final CSVReader inputCSVReader = new CSVReaderBuilder(new FileReader(INPUT_FILE))
								.withCSVParser(parser)
								.build();
		
		String[] headerColumns = inputCSVReader.readNext();
		String[] indexColumns = new String[] {"PAYPAL_REFERENCE_ID", "PAYPAL_TRANSACTION_ID"};
		
		try (Connection connection = getConnection())
		{
			long start = 0L;
			long end = 0L;
			
			// Create DB
			start = System.currentTimeMillis();
			createDBTable(connection, DB_TABLE_NAME, headerColumns, indexColumns);
			end = System.currentTimeMillis();
			System.out.println("* Time taken to empty/create table : " + new Float(end - start) / 1000 + " seconds.");
			
			// Insert Data in DB
			start = System.currentTimeMillis();
			String[] currentRecord = inputCSVReader.readNext();
			long cur_rec_idx = 0;
			while (currentRecord != null) {
				cur_rec_idx++;
				insertDBRecord(connection, DB_TABLE_NAME, headerColumns, currentRecord, cur_rec_idx, 10000);
				currentRecord = inputCSVReader.readNext();
				if (cur_rec_idx % 1000 == 1) {
					end = System.currentTimeMillis();
					System.out.println("... At " + new Float(end - start) / 1000 + " seconds processing input record number : " + cur_rec_idx);
				}
			}
			// Run last batch once.
			if (batchStmt != null) batchStmt.executeBatch();
			end = System.currentTimeMillis();
			System.out.println("* Time taken to insert rows in table : " + new Float(end - start) / 1000 + " seconds.");
			
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
	
	private void createDBTable(Connection connection, String tableName, String[] headerColumns, String[] indexColumns) throws SQLException {
		String strSql = "";
		Statement statement = connection.createStatement();
		try {
			strSql = "drop table if exists " + tableName;
			statement.executeUpdate(strSql);
			strSql = "create table " + tableName + " (" + String.join(" string,", headerColumns) + ")";
			statement.executeUpdate(strSql);
			strSql = "create index " + tableName + "_Index1 on " + tableName + "(" + String.join(",", indexColumns) + ")";
			statement.executeUpdate(strSql);
		}
		catch (SQLException e) {
			System.out.println("Error :" + e.getMessage());
			System.out.println("SQL :" + strSql);
			throw e;
		}
		statement.close();
	}

	private void insertDBRecord(Connection connection, String tableName, String[] headerColumns, String[] currentRecord, long cur_rec_idx, long batchSize) throws SQLException {
		String strSql = "";
		try {
			String[] qChars = new String[headerColumns.length];
			Arrays.fill(qChars, "?");
			strSql = "insert into " + tableName + " values(" + String.join(", ", qChars) + ")";
			if (batchStmt == null) {
				batchStmt = connection.prepareStatement(strSql);
			}
			else if ((cur_rec_idx % batchSize) == 1) {
				System.out.println("...... At record # " + cur_rec_idx + " running batch query execute.");
				batchStmt.executeBatch();
				batchStmt = connection.prepareStatement(strSql);
			}
			for (int idx = 0; idx < currentRecord.length; idx++) {
				batchStmt.setString(idx+1, currentRecord[idx]);
			}
			batchStmt.addBatch();
		}
		catch (SQLException e) {
			System.out.println("Error :" + e.getMessage());
			System.out.println("SQL :" + strSql);
			System.out.println("Values : '" + String.join("', '", currentRecord) + "'");
			throw e;
		}
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
