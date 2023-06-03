package com.notifications.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpRequestHandler
{
	public void handle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
