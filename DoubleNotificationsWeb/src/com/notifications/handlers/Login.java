package com.notifications.handlers;

import java.io.IOException;
import java.net.Inet6Address;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.notifications.dbcon.ConnectionHolder;
import com.notifications.dbcon.DBConnectionException;
import com.notifications.domain.User;
import com.notifications.mvc.HttpRequestHandler;

public class Login implements HttpRequestHandler {

	public static Logger log = Logger.getLogger(Search.class);

	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User userObj = new User();

		String user = request.getParameter("qx_id") + ": " + userObj.getUserName(request.getParameter("qx_id"));
		String password = request.getParameter("password");

		if (!userObj.validateUser(user, password)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");
			request.setAttribute("invalidDetails", "Password is wrong...");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/search.jsp");
			dispatcher.forward(request, response);
		}

		String systemName = Inet6Address.getLocalHost().toString();

		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("user", user);
		StringBuilder build = new StringBuilder();

		build.append("Session started by user: " + user + " ");
		build.append("from system: " + systemName + " ");

		log.info(build);
	}

	public static void initConnection(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectionHolder ch = null;
		Connection con = null;
		HttpSession sessionObj = request.getSession();

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			if (con != null) {
				con.setAutoCommit(false);
			} else {
				throw new NullPointerException();
			}
			sessionObj.setAttribute("connection", con);
		} catch (DBConnectionException | SQLException | NullPointerException e1) {
			try {
				con.close();
			} catch (SQLException | NullPointerException e) {
				log.error(e);
				e.printStackTrace();
			}
			log.error(e1);
			e1.printStackTrace();
		}

	}

}
