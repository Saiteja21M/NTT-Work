package com.dbfw;

@SuppressWarnings("serial")
public class DBFWException extends Exception{
	public DBFWException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DBFWException(String arg0) {
		super(arg0);
	}
}
