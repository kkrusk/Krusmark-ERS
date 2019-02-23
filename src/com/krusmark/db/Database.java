package com.krusmark.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Database {

	private static final String driverName = "com.mysql.jdbc.Driver";

	private static final String strSshUser = "pi"; // SSH loging username
	private static final String strSshPassword = "raspberry"; // SSH login password
	private static final String strSshHost = "68.100.137.70"; // hostname or ip or SSH server
	private static final int nSshPort = 113; // remote SSH host port number
	private static final String strRemoteHost = "127.0.0.26"; // hostname or ip of your database server
	private static final int nLocalPort = 22; // local port number use to bind SSH tunnel
	private static final int nRemotePort = 3306; // remote port number of your database
	private static final String strDbUser = "root"; // database loging username
	private static final String strDbPassword = "root"; // database login password

	private static final String db = "ers_db";

	// LOCAL HOST TESTING
	private static final String lh_url = "jdbc:mysql://localhost:3306";
	private static final String lh_db = "regform";
	private static final String lh_un = "root";
	private static final String lh_pw = "root";

	// Private constructor to restrict exterior instantiation
	private Database() {
	}

	// getInstance() method to modularize establish DB connection
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			try {
				doSshTunnel();
			} catch (Exception e) {
				System.out.println("error tunnelling");
			}

			Class.forName(driverName);
			System.out.println("jdbc good");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:" + nLocalPort + "/" + db, strDbUser,
					strDbPassword);
			System.out.println("connection good");
		} catch (SQLException sqle) {
			// Human Readable code as to what went wrong
			System.out.println(sqle.getMessage());

			// Googleable, vendor specific error code
			System.err.println("SQL State: " + sqle.getSQLState());

		} catch (ClassNotFoundException cfne) {

			System.err.println("Could not find " + driverName);
			cfne.printStackTrace();
		}
		return conn;

	}

	// DEBUGGING WITH LOCALHOST
	public static Connection getLocalConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(lh_url + "/" + lh_db, lh_un, lh_pw);
		} catch (SQLException sqle) {
			// Human Readable code as to what went wrong
			sqle.getMessage();

			// Googleable, vendor specific error code
			System.err.println("SQL State: " + sqle.getSQLState());
		} catch (ClassNotFoundException cfne) {
		
			System.err.println("Could not find " + driverName);
			cfne.printStackTrace();
		}
		return conn;
	}

	private static void doSshTunnel() throws JSchException {
		final JSch jsch = new JSch();
		Session session = jsch.getSession(strSshUser, strSshHost, nSshPort);
		session.setPassword(strSshPassword);

		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();

		session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	}
}
