package com.notifications.dbcon;

public class DBConnectionException extends Exception {


	private static final long serialVersionUID = 1L;

	public DBConnectionException(String arg0) {
		super(arg0);

	}

	public DBConnectionException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public DBConnectionException(Exception e) {
		super(e);
	}

}
