package com.notifications.mvc;

@SuppressWarnings("serial")
public class MvcException extends Exception  {

	public MvcException() {
		super();
	}

	public MvcException(String arg0) {
		super(arg0);

	}

	public MvcException(Throwable arg0) {
		super(arg0);

	}

	public MvcException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

}