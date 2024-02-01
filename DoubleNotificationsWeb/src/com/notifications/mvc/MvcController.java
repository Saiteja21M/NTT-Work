package com.notifications.mvc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notifications.dao.DAOAppException;
import com.notifications.dbfw.DBFWException;

@SuppressWarnings("serial")
public class MvcController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	@SuppressWarnings("rawtypes")
	private Map handlers = new HashMap<>();

	private Map<String, String> mappers = new HashMap<>();

	public void initHandlers() {
		mappers.put("search", "com.notifications.handlers.Search");
		mappers.put("modify", "com.notifications.handlers.Modify");
		mappers.put("navigate", "com.notifications.handlers.Navigate");
		mappers.put("login", "com.notifications.handlers.Login");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		initHandlers();
		String url = request.getServletPath();
		File f = new File(url);
		String file = f.getName();
		String key = file.substring(0, file.lastIndexOf('.'));
		try {
			Class handClazz = Class.forName((String) mappers.get(key));// Loading the Class

			// Checking whether the class implemented the HttpRequestHandler Interface
			if (checkInterface(handClazz, "com.notifications.mvc.HttpRequestHandler")) {
				// Then create the object of that class & add that to the map
				Object handler = handClazz.newInstance();
				handlers.put(key, handler);
			} else {
				try {
					throw new MvcException(
							"Class does not implement com.notifications.mvc.HttpRequestHandler interface");
				} catch (MvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpRequestHandler handler = (HttpRequestHandler) handlers.get(key);
		if (handler != null) {
			try {
				handler.handle(request, response);
			} catch (NumberFormatException | DBFWException e) {
				e.printStackTrace();
			} catch (DAOAppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new ServletException("No matching handler");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

	@SuppressWarnings("rawtypes")
	public boolean checkInterface(final Class clazz, final String interfaceName) {
		boolean found = false;
		// getting the interface implemented by the
		Class[] interfaces = clazz.getInterfaces();

		for (int i = 0; i < interfaces.length; i++) {
			if (interfaces[i].getName().equals(interfaceName)) {
				found = true;
				break;
			}
		}
		return found;
	}
}
