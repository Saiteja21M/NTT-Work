package com.doa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbfw.ResultMapper;
import com.domain.Notification;

public class SQLMapper {

	public final static String GETNOTIFICATIONSBYORDER = "Select nv_ws_order_id, to_char(ts_modification,'DD.MM.YY'),notification_type,"
			+ "notif_action,notif_item_number from toin01 where nv_ws_order_id = ? order by ts_modification, notif_item_number";
	
	public final static String DELETENOTIFICATIONBYNOTIF = "delete from toin01 where nv_ws_order_id = ? and notif_item_number = ?";
	
	public final static String UPDATENOTIFICAIONSBYNOTIF = "update toin01 set notif_item_number = notif_item_number - ? where "
			+ "nv_ws_order_id = ? and notif_item_number > ?";

	public static final ResultMapper COUNTRYMAPPER = new ResultMapper() {

		public Object mapRow(ResultSet rs) throws SQLException {

			int nv_ws_order_id = rs.getInt(1);
			String ts_modification = rs.getString(2);
			String notification_type = rs.getString(3);
			String notif_action = rs.getString(4);
			int notif_item_number = rs.getInt(5);

			Notification notif = new Notification(nv_ws_order_id,ts_modification,notification_type,notif_action,notif_item_number);

			return notif;
		}
		// mapRow

	};

}
