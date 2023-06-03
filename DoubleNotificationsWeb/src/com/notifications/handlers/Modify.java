package com.notifications.handlers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.notifications.dao.DAOAppException;
import com.notifications.dao.HistoryDAO;
import com.notifications.dao.NotificationDAO;
import com.notifications.dao.OrderingDAO;
import com.notifications.dbfw.DBFWException;
import com.notifications.domain.HistoryTable;
import com.notifications.domain.Notification;
import com.notifications.domain.OrderingTable;
import com.notifications.mvc.HttpRequestHandler;

/**
 * @author SaiTeja Koppala
 */
public class Modify implements HttpRequestHandler {

	public static Logger log = Logger.getLogger(Search.class);

	public NotificationDAO notificationDAO = new NotificationDAO();

	public OrderingDAO orderingDAO = new OrderingDAO();

	public HistoryDAO historyDAO = new HistoryDAO();

	@SuppressWarnings({ "unchecked" })
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, DBFWException {

		String action = request.getParameter("action");

		HttpSession session = request.getSession(false);
		Connection con = (Connection) session.getAttribute("connection");
		int nv_ws_order_id = Integer.parseInt((String) session.getAttribute("nv_ws_order_id"));
		String user = (String) session.getAttribute("user");

		List<Notification> notif = (List<Notification>) session.getAttribute("nv_ws_order_idResultList");

		switch (action) {
		case "Update":
			updateNotifAction(user, nv_ws_order_id, con, notif, request, response, false, session);
			break;
		case "Delete":
			deleteNotifications(user, nv_ws_order_id, con, request, response, session, false, notif, false);
			break;
		case "Submit":
			commitChanges(user, nv_ws_order_id, con, request, response, session);
			break;
		case "Cancel":
			rollbackChanges(user, nv_ws_order_id, con, request, response, session);
			break;
		case "findResultsToInsert":
			InsertOperation(user, nv_ws_order_id, notif, con, request, response, session, true, false, false);
			break;
		case "InsertData(Enquiry)":
			InsertOperation(user, nv_ws_order_id, notif, con, request, response, session, false, false, true);
			break;
		}
	}

	@SuppressWarnings("unchecked")
	void updateNotifAction(String user, int nv_ws_order_id, Connection con, List<Notification> notif,
			HttpServletRequest request, HttpServletResponse response, boolean showPage, HttpSession session)
			throws NumberFormatException, DBFWException {

		if (showPage) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/updateNotifAction.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

		else {
			int updated = 0;

			String notif_number = request.getParameter("notif_item_number");
			String newValue = request.getParameter("newValue");
			String column_name = request.getParameter("cloumn-names");

			if (!(notif_number.contains(",") && newValue.contains(","))) {
				updated += notificationDAO.updateNotifActionByNotif(nv_ws_order_id, Integer.parseInt(notif_number),
						newValue, column_name, con);
			} else {
				String[] notif_numbers = notif_number.split(",");
				String[] notif_actions = newValue.split(",");

				for (int i = 0; i < notif_numbers.length; i++) {
					updated += notificationDAO.updateNotifActionByNotif(nv_ws_order_id,
							Integer.parseInt(notif_numbers[i]), notif_actions[i], column_name, con);
				}
			}
			if (updated != 0) {
				RequestDispatcher rd = request.getRequestDispatcher("pages/updated.jsp");
				try {
					List<Notification> orderListAferUpdate = notificationDAO.getNotificationsByNvWsOrderId(con,
							nv_ws_order_id);
					session.setAttribute("listAferUpdate", orderListAferUpdate);
					rd.forward(request, response);
				} catch (DAOAppException | ServletException | IOException e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		}
	}

	void viewDuplicates(String user, int nv_ws_order_id, List<Notification> notificationList,
			HttpServletRequest request, HttpServletResponse response, boolean isShowDuplicateAction,
			HttpSession session, Connection con) throws ServletException, IOException {

		List<Notification> duplicates = new ArrayList<>();

		StringBuilder build = new StringBuilder();

		build.append(user + " started viewDuplicates operation");

		for (Notification notif : notificationList) {
			String temp = notif.getTs_modification().substring(0, 10);
			int count = 0;
			for (Notification notif2 : notificationList) {
				if (temp.equals(notif2.getTs_modification().substring(0, 10))
						&& notif.getNotification_type().equals(notif2.getNotification_type())
						&& notif.getNotif_action().equals(notif2.getNotif_action())) {
					count++;
				}
			}
			if (count > 1) {
				duplicates.add(notif);
			}
		}
		if (duplicates.size() == 2) {
			if (checkIfCancellationIsThereInMiddle(duplicates, notificationList)) {
				duplicates.clear();
			}
		}

		if (!isShowDuplicateAction && duplicates.size() != 0) {
			deleteNotifications(user, nv_ws_order_id, con, request, response, session, false, duplicates, true);
		}
		if (duplicates.size() == 0) {
			build.append("\t no duplicates found in this operation for order: " + nv_ws_order_id);
			log.info(build);
			request.setAttribute("Err", "This order has no duplicates");
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/results.jsp");
			dispatcher.forward(request, response);
		} else if (isShowDuplicateAction) {
			build.append("\t duplicates are found in this operation for order: " + nv_ws_order_id);
			build.append("\t" + duplicates);
			log.info(build);
			session.setAttribute("duplicates", duplicates);
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/duplicates.jsp");
			dispatcher.forward(request, response);

		}
	}

	/**
	 * @return true if 'C' entry is there between two 'E' entries otherwise false
	 */
	private boolean checkIfCancellationIsThereInMiddle(List<Notification> duplicates,
			List<Notification> notificationList) {

		boolean isCancelltionThere = false;

		if (duplicates.size() > 0) {
			for (Notification check : notificationList) {
				if (check.getNotif_item_number() == duplicates.get(0).getNotif_item_number() + 1) {
					if (check.getNotif_action().equals("C")) {
						isCancelltionThere = true;
						break;
					}
				}
			}
		}
		return isCancelltionThere;
	}

	@SuppressWarnings("unchecked")
	void deleteNotifications(String user, int nv_ws_order_id, Connection con, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, boolean isDeleteAction, List<Notification> notif,
			boolean isClearDuplicatesAction) {

		if (isDeleteAction) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/deleteNotification.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

		else {

			int deleted = 0;

			if (!isClearDuplicatesAction) {
				String notif_number = null;
				String[] notif_numbers = null;
				notif_number = request.getParameter("notif_item_number");

				if (!notif_number.contains(",")) {
					deleted += notificationDAO.deleteNotificationByNotif(con, nv_ws_order_id,
							Integer.parseInt(notif_number));
					notificationDAO.correctNotifications(con, nv_ws_order_id, Integer.parseInt(notif_number), deleted,
							"-");
				} else {
					notif_numbers = notif_number.split(",");
					Arrays.sort(notif_numbers);
					for (String number : notif_numbers) {
						deleted += notificationDAO.deleteNotificationByNotif(con, nv_ws_order_id,
								Integer.parseInt(number));
					}
					notif_number = notif_numbers[0];
					notificationDAO.correctNotifications(con, nv_ws_order_id, Integer.parseInt(notif_number), deleted,
							"-");
				}
			} else {
				for (int i = 0; i < notif.size() - 1; i++) {
					deleted += notificationDAO.deleteNotificationByNotif(con, nv_ws_order_id,
							(notif.get(i).getNotif_item_number()));
				}
				int notifNumber = notif.get(0).getNotif_item_number();
				notificationDAO.correctNotifications(con, nv_ws_order_id, notifNumber, deleted, "-");
			}

			if (deleted != 0) {
				RequestDispatcher rd = request.getRequestDispatcher("pages/deleted.jsp");
				try {
					List<Notification> orderListAferDeletion = notificationDAO.getNotificationsByNvWsOrderId(con,
							nv_ws_order_id);
					session.setAttribute("listAferDeletion", orderListAferDeletion);
					rd.forward(request, response);
				} catch (DAOAppException | ServletException | IOException e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void commitChanges(String user, int nv_ws_order_id, Connection con, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		try {
			con.commit();
			RequestDispatcher rd = request.getRequestDispatcher("pages/modificationResult.jsp");
			try {
				List<Notification> orderListAferTransaction = notificationDAO.getNotificationsByNvWsOrderId(con,
						nv_ws_order_id);
				session.setAttribute("listAferTransaction", orderListAferTransaction);
				request.setAttribute("Err", "changes have been commited");
				rd.forward(request, response);

				StringBuilder build = new StringBuilder();

				build.append(user + " has commited the chnages");
				build.append("\n page redirected to modification results page...");
				log.info(build);
			} catch (DAOAppException | ServletException | IOException e) {
				log.error(e);
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void rollbackChanges(String user, int nv_ws_order_id, Connection con, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		try {
			con.rollback();

			RequestDispatcher rd = request.getRequestDispatcher("pages/modificationResult.jsp");
			try {
				List<Notification> orderListAferTransaction = notificationDAO.getNotificationsByNvWsOrderId(con,
						nv_ws_order_id);
				session.setAttribute("listAferTransaction", orderListAferTransaction);
				request.setAttribute("Err", "changes have been rolledback");
				rd.forward(request, response);

				StringBuilder build = new StringBuilder();

				build.append(user + " has rolled back the chnages");
				build.append("\n page redirected to modification results page...");
				log.info(build);
			} catch (DAOAppException | ServletException | IOException e) {
				log.error(e);
				e.printStackTrace();
			}

		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				log.error(e);
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void InsertOperation(String user, int nv_ws_order_id, List<Notification> notif, Connection con,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			boolean isFindResultOperation, boolean isShowInsertPageOperation, boolean isInsertToN01Operation) {

		if (isShowInsertPageOperation) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/insertNotification.jsp");
			try {
				session.setAttribute("notifications", notif);
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (isFindResultOperation || isInsertToN01Operation) {
			String timestamp = request.getParameter("timestamp");

			List<HistoryTable> historyTableData = null;
			List<OrderingTable> orderingTableData = null;
			try {
				historyTableData = historyDAO.getHistoryTableDataByTimestamp(con, nv_ws_order_id, timestamp);
				orderingTableData = orderingDAO.getOrderingTableData(con, nv_ws_order_id);
			} catch (DAOAppException e1) {
				log.error(e1);
				e1.printStackTrace();
			}
			if (!(historyTableData.isEmpty() && orderingTableData.isEmpty()) && isFindResultOperation) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/orderingAndHistoryData.jsp");
				session.setAttribute("notifications", notif);
				session.setAttribute("o01Data", orderingTableData);
				session.setAttribute("h01Data", historyTableData);
				try {
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					log.error(e);
					e.printStackTrace();
				}
			}
			if (isInsertToN01Operation) {

				List<OrderingTable> o01Data = (List<OrderingTable>) session.getAttribute("o01Data");
				List<HistoryTable> h01Data = (List<HistoryTable>) session.getAttribute("h01Data");

				beginInsertToN01Operation(user, nv_ws_order_id, notif, con, request, response, session, o01Data,
						h01Data, timestamp);
			}

		}
	}

	@SuppressWarnings("unchecked")
	private void beginInsertToN01Operation(String user, int nv_ws_order_id, List<Notification> notif, Connection con,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			List<OrderingTable> orderingTableData, List<HistoryTable> historyTableData, String timestamp) {

		int h01RowPosition = Integer.parseInt((String) request.getParameter("h01Row"));
		int notifPosition = Integer.parseInt((String) request.getParameter("notifPosition"));
		String notif_action = request.getParameter("notif_action");
		String notificationType = request.getParameter("notifiaction_type");

		HistoryTable h01Data = historyTableData.get(h01RowPosition - 1);
		OrderingTable o01Data = orderingTableData.get(0);

		Notification n01Data = new Notification();

		n01Data.setNv_ws_order_id(nv_ws_order_id);
		n01Data.setTs_modification(h01Data.getTs_modification());
		n01Data.setNotification_type(notificationType);
		n01Data.setNotif_action(notif_action);
		n01Data.setNotif_item_number(notifPosition);
		n01Data.setNotif_docdate(h01Data.getTs_modification().substring(0, 9));
		n01Data.setVehicle_id_7(o01Data.getVehicle_id_7());
		n01Data.setMf_order_no(o01Data.getMf_order_no());
		n01Data.setVehicle_state(o01Data.getVehicle_state());
		n01Data.setClient(o01Data.getClient());
		n01Data.setProduct_type(o01Data.getProduct_type());
		n01Data.setBrand(o01Data.getBrand());
		n01Data.setMf_model_code(o01Data.getMf_model_code());
		n01Data.setWs_model_code(o01Data.getWs_model_code());
		n01Data.setOrder_type(h01Data.getOrder_type());
		n01Data.setIvs_order_status(h01Data.getIvs_order_status());
		n01Data.setProcessing_type(h01Data.getProcessing_type());
		n01Data.setWs_business_type(h01Data.getWs_business_type());
		n01Data.setNv_mf_orderer_ca(h01Data.getNv_mf_orderer_ca());
		n01Data.setNv_mf_orderer_id(h01Data.getNv_mf_orderer_id());
		n01Data.setOrderer_domestic(h01Data.getOrderer_domestic());
		n01Data.setInvoicing_dealer(h01Data.getInvoicing_dealer());
		n01Data.setMod_function(h01Data.getMod_function());
		n01Data.setMod_system(h01Data.getMod_system());
		n01Data.setMod_user(h01Data.getMod_user());
		n01Data.setVersion(h01Data.getVersion());
		n01Data.setPartition_key(h01Data.getPartition_key());
		n01Data.setDealer_class("EH");

		int updated = notificationDAO.correctNotifications(con, nv_ws_order_id, notifPosition, 1, "+");

		if (updated > 0) {
			int inserted = notificationDAO.insertIntoN01(nv_ws_order_id, notifPosition, n01Data, con);

			if (inserted > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("pages/inserted.jsp");
				try {
					List<Notification> orderListAferInsert = notificationDAO.getNotificationsByNvWsOrderId(con,
							nv_ws_order_id);
					session.setAttribute("listBeforeInsert", notif);
					session.setAttribute("listAferInsert", orderListAferInsert);
					rd.forward(request, response);
				} catch (DAOAppException | ServletException | IOException e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		}

	}

}
