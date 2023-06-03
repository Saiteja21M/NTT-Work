package com.notifications.handlers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.notifications.dbcon.ConnectionHolder;
import com.notifications.dbcon.DBConnectionException;

public class MyListenerClass implements ServletContextListener {

	@SuppressWarnings("unused")
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			ConnectionHolder holder = ConnectionHolder.getInstance();

		} catch (DBConnectionException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
