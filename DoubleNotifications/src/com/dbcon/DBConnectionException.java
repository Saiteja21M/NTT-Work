package com.dbcon;

@SuppressWarnings("serial")
public class DBConnectionException extends Exception{
	public DBConnectionException(String mesg, Throwable cause) {

		super(mesg, cause);
	}

	public DBConnectionException(String mesg) {
		super(mesg);
	}
}