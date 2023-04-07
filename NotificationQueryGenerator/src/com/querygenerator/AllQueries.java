package com.querygenerator;

public class AllQueries {

	private String childQuery = "delete from ivsr_res.toin02 where ts_modification = (select ts_modification from ivsr_res.toin01 where notif_item_number = (select max(notif_item_number) from ivsr_res.toin01 where nv_ws_order_id='43445721' and notification_type='PA' and to_char(ts_modification,'DD.MM.YY') = '19.01.23' and notif_action = 'E') and nv_ws_order_id='43445721' and notification_type='PA' and to_char(ts_modification,'DD.MM.YY') = '19.01.23') and notification_type='PA' and to_char(ts_modification,'DD.MM.YY') = '19.01.23' and nv_ws_order_id='43445721' ;";

	private String parentQuery = "delete from ivsr_res.toin01 where nv_ws_order_id='43445721' and notification_type='PA' and to_char(ts_modification,'DD.MM.YY') = '19.01.23' and notif_item_number = (select max (notif_item_number) from ivsr_res.toin01 where nv_ws_order_id='43445721' and notification_type='PA' and to_char(ts_modification,'DD.MM.YY') = '19.01.23' and notif_action = 'E') and notif_action = 'E';";

	private String updateQuery = "update ivsr_res.toin01 set notif_item_number = notif_item_number-1 where nv_ws_order_id='43445721' and notif_item_number> (select min(notif_item_number) from ivsr_res.toin01 where nv_ws_order_id='43445721' and  notification_type='PA' and to_char(ts_modification,'DD.MM.YY') = '19.01.23' ) ;";

	private String doubleCheckQuery = "select nv_ws_order_id, count(notif_item_number) from ivsr_res.toin01 where nv_ws_order_id = '43445721'  group by notif_item_number, nv_ws_order_id union";

	private String c05UpdateQuery = "update IVSR_RES.toic05 set item_number_high = ( select count(notif_item_number) from ivsr_res.toin01 where nv_ws_order_id = '43445721') where nv_ws_order_id = '43445721';";

	public String getChildQuery() {
		return childQuery;
	}

	public String getParentQuery() {
		return parentQuery;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public String getDoubleCheckQuery() {
		return doubleCheckQuery;
	}

	public String getC05UpdateQuery() {
		return c05UpdateQuery;
	}

}
