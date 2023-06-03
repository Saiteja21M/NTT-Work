package com.notifications.dbcon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

@SuppressWarnings("unused")
public class ConnectionHolder {

	private static ConnectionHolder connectionHolderInstance = null;

	private DataSource ds = null;

	private ConnectionHolder() {
	}

	// Used during web application instead of the above method
	private void initAppserverDataSource() throws DBConnectionException {
		Context initContext;

		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/MyDB");
			//System.out.println(ds.toString());
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DBConnectionException("Unable to get datasource", e);

		}
	}


	public static ConnectionHolder getInstance() throws DBConnectionException {

		//	happens only once
		synchronized (ConnectionHolder.class) {

			// use reflection to synchronize as we have no object
			if (connectionHolderInstance == null) {

				connectionHolderInstance = new ConnectionHolder();

				// Replace this line for Web Application
				connectionHolderInstance.initAppserverDataSource();
			}
		}
		return connectionHolderInstance;
	}

	public Connection getConnection() throws DBConnectionException {

		try {
			
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBConnectionException("Unable to obtain a connection", e);
		}
	}

	public void dispose() throws DBConnectionException {

		BasicDataSource bds = (BasicDataSource) ds;
		try {

			bds.close();
		} catch (SQLException e) {

			throw new DBConnectionException("Unable to close the connection", e);
		}
	}
}
