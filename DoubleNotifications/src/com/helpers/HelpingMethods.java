package com.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.dbcon.ConnectionHolder;
import com.dbcon.DBConnectionException;
import com.doa.NotificationDAO;
import com.domain.Notification;
import com.driver.MainDriver;

public class HelpingMethods {

	public Scanner sc = new Scanner(System.in);

	ConnectionHolder ch = null;
	Connection con = null;

	NotificationDAO nd = new NotificationDAO();

	public HelpingMethods() {
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			con.setAutoCommit(false);
		} catch (DBConnectionException | SQLException s) {
			s.printStackTrace();
		}
	}

	public void showNotificationByOrder(boolean isAfterTransaction, int nv_ws_order_id) throws DBConnectionException {

		List<Notification> notificationList = nd.getNotificationsByOrder(con, nv_ws_order_id);
		
		if(notificationList.isEmpty()) {
			System.out.println("no orders were found with given nv_ws_order_id");
			MainDriver.main(new String[] {""});
		}

		for (Notification notifs : notificationList) {
			System.out.println(notifs.getNv_ws_order_id() + "\t" + notifs.getTs_modification() + "\t"
					+ notifs.getNotification_type() + "\t" + notifs.getNotif_action() + "\t"
					+ notifs.getNotif_item_number());
		}
		if (!isAfterTransaction) {
			System.out.print("Enter 1 to view duplicates or 2 to delete directly from here: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				viewDuplicates(nv_ws_order_id, notificationList);
				break;
			case 2:
				deletenotifications(nv_ws_order_id);
				break;
			}

		}
	}

	private void deletenotifications(int nv_ws_order_id) throws DBConnectionException {
		System.out.print(
				"Please enter the notif item number of the notification which you want delete seperated by a comma: ");

		String notif_number = sc.next();

		int deleted = 0;

		if (!notif_number.contains(",")) {
			deleted += nd.deleteNotificationByNotif(con, nv_ws_order_id, Integer.parseInt(notif_number));
			nd.correctNotifications(con, nv_ws_order_id, Integer.parseInt(notif_number), deleted);
		} else {
			String[] notif_numbers = notif_number.split(",");
			Arrays.sort(notif_numbers);
			for (String number : notif_numbers) {
				deleted += nd.deleteNotificationByNotif(con, nv_ws_order_id, Integer.parseInt(number));
			}
			notif_number = notif_numbers[0];
			nd.correctNotifications(con, nv_ws_order_id, Integer.parseInt(notif_number), deleted);
		}

		System.out.println(deleted + " notification(s) deleted");
		showNotificationByOrder(true, nv_ws_order_id);
		System.out.print("Enter 1 to commit changes or 2 to rollback changes: ");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			commitChanges(con);
			break;
		case 2:
			rollbackChanges(con);
			break;
		}

	}

	private void viewDuplicates(int nv_ws_order_id, List<Notification> notificationList) throws DBConnectionException {

		// Map<Integer,String> duplicates= new HashMap<>();

		List<Notification> duplicates = new ArrayList<>();

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
		if (duplicates.size() > 0) {
			for (Notification check : notificationList) {
				if (check.getNotif_item_number() == duplicates.get(0).getNotif_item_number() + 1) {
					if (check.getNotif_action().equals("C")) {
						duplicates.clear();
						break;
					}
				}
			}
		}
		if (duplicates.size() == 0) {
			System.out.println("No Duplicates Found");
		} else {
			for (Notification notifs : duplicates) {
				System.out.println(notifs.getNv_ws_order_id() + "\t" + notifs.getTs_modification() + "\t"
						+ notifs.getNotification_type() + "\t" + notifs.getNotif_action() + "\t"
						+ notifs.getNotif_item_number());
			}
			deletenotifications(nv_ws_order_id);
		}
	}

	private void commitChanges(Connection con) {
		try {
			con.commit();
			System.out.println("Changes have been committed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void rollbackChanges(Connection con) {
		try {
			con.rollback();
			System.out.println("Changes have been rolledback");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
