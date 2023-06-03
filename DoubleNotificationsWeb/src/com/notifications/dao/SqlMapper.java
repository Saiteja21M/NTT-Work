package com.notifications.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.notifications.dbfw.ResultMapper;
import com.notifications.domain.HistoryTable;
import com.notifications.domain.Notification;
import com.notifications.domain.OrderingTable;

/**
 * @author SaiTeja Koppala
 */
public class SqlMapper {

	public static final String FETCH_N01_ORDER = "Select * from toin01 where nv_ws_order_id = ? order by ts_modification, notif_item_number";

	public final static String DELETE_NOTIFICATION_BY_NOTIF = "delete from toin01 where nv_ws_order_id = ? and notif_item_number = ?";

	public final static String UPDATE_NOTIFICAIONS_BY_NOTIF = "update toin01 set notif_item_number = notif_item_number operator ? where "
			+ "nv_ws_order_id = ? and notif_item_number > ?";

	public final static String UPDATE_NOTIF_ACTION_BY_NOTIF = "update toin01 set notif_action = ? where nv_ws_order_id = ? and "
			+ "notif_item_number = ?";

	public final static String GET_ORDERING_TABLE_DATA = "select nv_ws_order_id,mf_order_no, vehicle_id_7, VEHICLE_STATE,CLIENT,PRODUCT_TYPE,BRAND,"
			+ "MF_MODEL_CODE,WS_MODEL_CODE from toio01 where nv_ws_order_id = ?";

	public final static String GET_HISTORY_TABLE_DATA = "select nv_ws_order_id, ts_modification, order_type, ivs_order_status, processing_type, "
			+ "ws_business_type,NV_MF_ORDERER_CA,NV_MF_ORDERER_ID,ORDERER_DOMESTIC,INVOICING_DEALER,MOD_FUNCTION,MOD_SYSTEM,MOD_USER,VERSION,"
			+ "PARTITION_KEY from toih01 where nv_ws_order_id = ? ";

	public final static String GET_HISTOTY_TABLE_DATA_BY_TS = "select nv_ws_order_id, ts_modification, order_type, ivs_order_status, processing_type, "
			+ "ws_business_type,NV_MF_ORDERER_CA,NV_MF_ORDERER_ID,ORDERER_DOMESTIC,INVOICING_DEALER,MOD_FUNCTION,MOD_SYSTEM,MOD_USER,VERSION,"
			+ "PARTITION_KEY from toih01 where nv_ws_order_id = ? and to_char(ts_modification,'DD.MM.YY') = ?";

	public static final String INSERT_INTO_N01 = "insert into toin01 values(?,to_timestamp(?,'YYYY-MM-DD HH:MI:SSXFF'),?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final ResultMapper MAP_N01_ORDER_DATA = new ResultMapper() {

		@Override
		public Object mapRows(ResultSet rs) throws SQLException {

			Notification notif = new Notification();

			notif.setNv_ws_order_id(rs.getInt("nv_ws_order_id"));
			notif.setTs_modification(rs.getString("ts_modification"));
			notif.setNotification_type(rs.getString("notification_type"));
			notif.setNotif_action(rs.getString("notif_action"));
			notif.setNotif_item_number(rs.getInt("notif_item_number"));
			notif.setNotif_docdate(rs.getString("notif_docdate"));
			notif.setVehicle_id_7(rs.getString("vehicle_id_7"));
			notif.setMf_order_no(rs.getString("mf_order_no"));
			notif.setVehicle_state(rs.getString("vehicle_state"));
			notif.setClient(rs.getString("client"));
			notif.setProduct_type(rs.getString("product_type"));
			notif.setBrand(rs.getString("brand"));
			notif.setMf_model_code(rs.getString("mf_model_code"));
			notif.setWs_model_code(rs.getString("ws_model_code"));
			notif.setOrder_type(rs.getString("order_type"));
			notif.setIvs_order_status(rs.getString("ivs_order_status"));
			notif.setProcessing_type(rs.getString("processing_type"));
			notif.setWs_business_type(rs.getString("ws_business_type"));
			notif.setNv_mf_orderer_ca(rs.getString("nv_mf_orderer_ca"));
			notif.setNv_mf_orderer_id(rs.getString("nv_mf_orderer_id"));
			notif.setOrderer_domestic(rs.getString("orderer_domestic"));
			notif.setInvoicing_dealer(rs.getString("invoicing_dealer"));
			notif.setMod_function(rs.getString("mod_function"));
			notif.setMod_system(rs.getString("mod_system"));
			notif.setMod_user(rs.getString("mod_user"));
			notif.setVersion(rs.getInt("version"));
			notif.setPartition_key(rs.getString("partition_key"));
			notif.setDealer_class(rs.getString("dealer_class"));

			return notif;

		}
	};

	public static final ResultMapper MAP_O01_ORDER_DATA = new ResultMapper() {

		@Override
		public Object mapRows(ResultSet rs) throws SQLException {

			OrderingTable o01Data = new OrderingTable();

			o01Data.setNv_ws_order_id(rs.getInt("nv_ws_order_id"));
			o01Data.setVehicle_state(rs.getString("vehicle_state"));
			o01Data.setMf_order_no(rs.getString("mf_order_no"));
			o01Data.setVehicle_id_7(rs.getString("vehicle_id_7"));
			o01Data.setClient(rs.getString("client"));
			o01Data.setProduct_type(rs.getString("product_type"));
			o01Data.setBrand(rs.getString("brand"));
			o01Data.setMf_model_code(rs.getString("mf_model_code"));
			o01Data.setWs_model_code(rs.getString("ws_model_code"));

			return o01Data;

		}
	};

	public static final ResultMapper MAP_H01_ORDER_DATA = new ResultMapper() {

		@Override
		public Object mapRows(ResultSet rs) throws SQLException {

			HistoryTable h01Data = new HistoryTable();

			h01Data.setNv_ws_order_id(rs.getInt("nv_ws_order_id"));
			h01Data.setTs_modification(rs.getString("ts_modification"));
			h01Data.setOrder_type(rs.getString("order_type"));
			h01Data.setIvs_order_status(rs.getString("ivs_order_status"));
			h01Data.setProcessing_type(rs.getString("processing_type"));
			h01Data.setWs_business_type(rs.getString("ws_business_type"));
			h01Data.setNv_mf_orderer_ca(rs.getString("nv_mf_orderer_ca"));
			h01Data.setNv_mf_orderer_id(rs.getString("nv_mf_orderer_id"));
			h01Data.setOrderer_domestic(rs.getString("orderer_domestic"));
			h01Data.setInvoicing_dealer(rs.getString("invoicing_dealer"));
			h01Data.setMod_function(rs.getString("mod_function"));
			h01Data.setMod_system(rs.getString("mod_system"));
			h01Data.setMod_user(rs.getString("mod_user"));
			h01Data.setVersion(rs.getInt("version"));
			h01Data.setPartition_key(rs.getString("partition_key"));

			return h01Data;

		}
	};

}
