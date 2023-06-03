package com.notifications.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.notifications.dbfw.DBFWException;
import com.notifications.dbfw.DBHelper;
import com.notifications.dbfw.ParamMapper;
import com.notifications.domain.Notification;

/**
 * @author SaiTeja Koppala
 */
public class NotificationDAO {
	static Logger log = Logger.getLogger(NotificationDAO.class);

	@SuppressWarnings("rawtypes")
	public List getNotificationsByNvWsOrderId(Connection con, final int nv_ws_order_id) throws DAOAppException {
		List res = null;
		try {
			ParamMapper paramMapper = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement pStmt) throws SQLException {
					pStmt.setInt(1, nv_ws_order_id);

				}
			};
			res = DBHelper.executeSelect(con, SqlMapper.FETCH_N01_ORDER, SqlMapper.MAP_N01_ORDER_DATA, paramMapper);
		} catch (DBFWException e) {
			log.error(e);
			throw new DAOAppException(e);
		}
		return res;

	}

	public int deleteNotificationByNotif(Connection con, int nv_ws_order_id, int notif_number) {

		int deleted = 0;

		try {
			final ParamMapper DELETENOTIFICATION = new ParamMapper() {

				public void mapParams(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, nv_ws_order_id);
					preStmt.setInt(2, notif_number);
				}
			};
			deleted = DBHelper.executeUpdate(con, SqlMapper.DELETE_NOTIFICATION_BY_NOTIF, DELETENOTIFICATION);

		} catch (DBFWException e) {
			log.error(e);
			e.printStackTrace();
		}

		return deleted;
	}

	public int correctNotifications(Connection con, int nv_ws_order_id, int notif_item_number, int count,
			String operator) {

		int corrected = 0;

		try {
			final ParamMapper UPDATENOTIFICATION = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, count);
					preStmt.setInt(2, nv_ws_order_id);
					preStmt.setInt(3, notif_item_number - 1);
				}
			};
			corrected = DBHelper.executeUpdate(con,
					SqlMapper.UPDATE_NOTIFICAIONS_BY_NOTIF.replace("operator", operator), UPDATENOTIFICATION);

		} catch (DBFWException e) {
			log.error(e);
			e.printStackTrace();
		}
		return corrected;
	}

	public int updateNotifActionByNotif(int nv_ws_order_id, int notif_item_number, String newValue, String column_name,
			Connection con) throws DBFWException{

		int corrected = 0;

		try {
			final ParamMapper UPDATENOTIFACTION = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement preStmt) throws SQLException {
					preStmt.setString(1, newValue);
					preStmt.setInt(2, nv_ws_order_id);
					preStmt.setInt(3, notif_item_number);
				}
			};
			if (column_name.contains("ts")) {
				corrected = DBHelper.executeUpdate(con, SqlMapper.UPDATE_NOTIFICATION_BY_NOTIF
						.replace("column", column_name).replace("value", "to_timestamp(?,'YYYY-MM-DD HH:MI:SSXFF')"),
						UPDATENOTIFACTION);
			} else if (column_name.contains("doc")) {
				corrected = DBHelper.executeUpdate(con, SqlMapper.UPDATE_NOTIFICATION_BY_NOTIF
						.replace("column", column_name).replace("value", "to_date(?,'YYYY-MM-DD')"), UPDATENOTIFACTION);
			} else {
				corrected = DBHelper.executeUpdate(con,
						SqlMapper.UPDATE_NOTIFICATION_BY_NOTIF.replace("column", column_name).replace("value", "?"),
						UPDATENOTIFACTION);
			}

		} catch (DBFWException e) {
			log.error(e);
			e.printStackTrace();
		}
		return corrected;

	}

	public int insertIntoN01(int nv_ws_order_id, int notifPosition, Notification n01Data, Connection con) {

		int inserted = 0;

		try {
			final ParamMapper mapper = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement pStmt) throws SQLException {

					pStmt.setInt(1, n01Data.getNv_ws_order_id());
					pStmt.setString(2, n01Data.getTs_modification());
					pStmt.setString(3, n01Data.getNotification_type());
					pStmt.setString(4, n01Data.getNotif_action());
					pStmt.setInt(5, n01Data.getNotif_item_number());
					pStmt.setString(6, n01Data.getNotif_docdate());
					pStmt.setString(7, n01Data.getVehicle_id_7());
					pStmt.setString(8, n01Data.getMf_order_no());
					pStmt.setString(9, n01Data.getOrder_type());
					pStmt.setString(10, n01Data.getIvs_order_status());
					pStmt.setString(11, n01Data.getVehicle_state());
					pStmt.setString(12, n01Data.getClient());
					pStmt.setString(13, n01Data.getProduct_type());
					pStmt.setString(14, n01Data.getBrand());
					pStmt.setString(15, n01Data.getProcessing_type());
					pStmt.setString(16, n01Data.getWs_business_type());
					pStmt.setString(17, n01Data.getMf_model_code());
					pStmt.setString(18, n01Data.getWs_model_code());
					pStmt.setString(19, n01Data.getNv_mf_orderer_ca());
					pStmt.setString(20, n01Data.getNv_mf_orderer_id());
					pStmt.setString(21, n01Data.getOrderer_domestic());
					pStmt.setString(22, n01Data.getInvoicing_dealer());
					pStmt.setString(23, n01Data.getDealer_class());
					pStmt.setString(24, n01Data.getMod_function());
					pStmt.setString(25, n01Data.getMod_system());
					pStmt.setString(26, n01Data.getMod_user());
					pStmt.setInt(27, n01Data.getVersion());
					pStmt.setString(28, n01Data.getPartition_key());
				}
			};
			inserted = DBHelper.executeUpdate(con, SqlMapper.INSERT_INTO_N01, mapper);

		} catch (DBFWException e) {
			log.error(e);
			e.printStackTrace();
		}

		return inserted;

	}

}
