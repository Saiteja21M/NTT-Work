package com.notifications.handlers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifications.dbfw.DBFWException;
import com.notifications.domain.Notification;
import com.notifications.mvc.HttpRequestHandler;

/**
 * @author SaiTeja Koppala
 */
public class Navigate implements HttpRequestHandler {

	@SuppressWarnings("unchecked")
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, DBFWException {

		Modify modify = new Modify();

		String action = request.getParameter("action");

		HttpSession session = request.getSession(false);
		Connection con = (Connection) session.getAttribute("connection");
		int nv_ws_order_id = Integer.parseInt((String) session.getAttribute("nv_ws_order_id"));
		String user = (String) session.getAttribute("user");

		List<Notification> notif = (List<Notification>) session.getAttribute("nv_ws_order_idResultList");

		switch (action) {
		case "View Duplicates":
			modify.viewDuplicates(user, nv_ws_order_id, notif, request, response, true, session, con);
			break;
		case "Update Notif_Action":
			modify.updateNotifAction(user, nv_ws_order_id, con, notif, request, response, true, session);
			break;
		case "Delete Notification":
			modify.deleteNotifications(user, nv_ws_order_id, con, request, response, session, true, notif, false);
			break;
		case "Clear Duplicates":
			modify.viewDuplicates(user, nv_ws_order_id, notif, request, response, false, session, con);
			break;
		case "Insert Notification":
			modify.InsertOperation(user, nv_ws_order_id, notif, con, request, response, session, false, true, false);
			break;
		case "HOME":
			RequestDispatcher rd = request.getRequestDispatcher("pages/search.jsp");
			rd.forward(request, response);

		}
	}
}
