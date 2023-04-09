package com.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dbcon.DBConnectionException;
import com.dbfw.DBFWException;
import com.dbfw.DBHelper;
import com.dbfw.ParamMapper;
import com.domain.Notification;

public class NotificationDAO {

	Logger log = null;

	@SuppressWarnings("unchecked")
	public List<Notification> getNotificationsByOrder(Connection con, int nv_ws_order_id) throws DBConnectionException {

		List<Notification> notificationList = new ArrayList<>();

		final ParamMapper FETCHNOTIFICATION = new ParamMapper() {

			public void mapParam(PreparedStatement preStmt) throws SQLException {
				preStmt.setInt(1, nv_ws_order_id);
			}
		};
		notificationList = DBHelper.executeSelect(con, SQLMapper.GETNOTIFICATIONSBYORDER, SQLMapper.COUNTRYMAPPER,
				FETCHNOTIFICATION);

		return notificationList;
	}

	public int deleteNotificationByNotif(Connection con, int nv_ws_order_id, String notif_number)
			throws DBConnectionException {

		int deleted = 0;

		try {
			final ParamMapper DELETENOTIFICATION = new ParamMapper() {

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, nv_ws_order_id);
					preStmt.setString(2, notif_number);
				}
			};
			deleted = DBHelper.executeUpdate(con, SQLMapper.DELETENOTIFICATIONBYNOTIF, DELETENOTIFICATION);

		} catch (DBFWException e) {
			try {
				throw new DBConnectionException("Unable to connect to db " + e);
			} catch (DBConnectionException e1) {
				e1.printStackTrace();
			}

		}

		return deleted;
	}

	public int correctNotifications(Connection con, int nv_ws_order_id, int notif_item_number, int count)
			throws DBConnectionException {

		int corrected = 0;

		try {
			final ParamMapper UPDATENOTIFICATION = new ParamMapper() {

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, count);
					preStmt.setInt(2, nv_ws_order_id);
					preStmt.setInt(3, notif_item_number - 1);
				}
			};
			corrected = DBHelper.executeUpdate(con, SQLMapper.UPDATENOTIFICAIONSBYNOTIF, UPDATENOTIFICATION);

		} catch (DBFWException e) {
			try {
				throw new DBConnectionException("Unable to connect to db" + e);
			} catch (DBConnectionException e1) {
				e1.printStackTrace();
			}

		}
		return corrected;
	}

}
