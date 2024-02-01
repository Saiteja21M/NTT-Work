package com.notifications.handlers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.notifications.dao.DAOAppException;
import com.notifications.dao.NotificationDAO;
import com.notifications.mvc.HttpRequestHandler;

/**
 * @author SaiTeja Koppala
 */
public class Search implements HttpRequestHandler {

	public static Logger log = Logger.getLogger(Search.class);

	@SuppressWarnings({ "rawtypes", "unused" })
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Login.initConnection(request, response);

		HttpSession session = request.getSession(false);
		String user = (String) session.getAttribute("user");
		Connection con = (Connection) session.getAttribute("connection");

		String nv_ws_order_id = request.getParameter("nv_ws_order_id");

		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("nv_ws_order_id", nv_ws_order_id);
		StringBuilder build = new StringBuilder();

		build.append("Session started by user: " + user + " ");
		build.append("order: " + nv_ws_order_id + " ");

		log.info(build);
		boolean isdebugEnabled = log.isDebugEnabled() ? true : false;

		List nv_ws_order_idResultList = null;
		NotificationDAO dao = new NotificationDAO();
		try {
			if (con == null) {
				throw new NullPointerException("DB Connection is not established");
			}

			nv_ws_order_idResultList = dao.getNotificationsByNvWsOrderId(con, Integer.parseInt(nv_ws_order_id));
			if (!nv_ws_order_idResultList.isEmpty()) {

				if (!dao.checkIfOrderIsRevoked(nv_ws_order_id, con)) {
					sessionObj.setAttribute("nv_ws_order_idResultList", nv_ws_order_idResultList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("pages/results.jsp");
					dispatcher.forward(request, response);

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("pages/search.jsp");
					sessionObj.setAttribute("Err", "Order is not Revoked");
					dispatcher.forward(request, response);

				}

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/search.jsp");
				request.setAttribute("Err", "no orders were found with this nv_ws_order_id");
				dispatcher.forward(request, response);
			}

		} catch (DAOAppException e) {
			log.error(e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			request.setAttribute("Err", e.getMessage());
			dispatcher.forward(request, response);

		} catch (NumberFormatException e1) {
			log.error(e1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/search.jsp");
			request.setAttribute("Err", "Alpha Numeric is not allowed");
			dispatcher.forward(request, response);

		} catch (NullPointerException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/search.jsp");
			request.setAttribute("databaseConnection", "Database Connection can't be established...");
			dispatcher.forward(request, response);
			log.error(e);
			e.printStackTrace();
		}
	}
}
