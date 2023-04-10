package com.domain;

public class Notification {

	@Override
	public String toString() {
		return "Notification [nv_ws_order_id=" + nv_ws_order_id + ", ts_modification=" + ts_modification
				+ ", notification_type=" + notification_type + ", notif_action=" + notif_action + ", notif_item_number="
				+ notif_item_number + "]";
	}

	private int nv_ws_order_id;
	private String ts_modification;
	private String notification_type;
	private String notif_action;
	private int notif_item_number;

	public Notification(int nv_ws_order_id, String ts_modification, String notification_type, String notif_action,
			int notif_item_number) {
		super();
		this.nv_ws_order_id = nv_ws_order_id;
		this.ts_modification = ts_modification;
		this.notification_type = notification_type;
		this.notif_action = notif_action;
		this.notif_item_number = notif_item_number;
	}

	public int getNv_ws_order_id() {
		return nv_ws_order_id;
	}

	public String getTs_modification() {
		return ts_modification;
	}

	public String getNotification_type() {
		return notification_type;
	}

	public String getNotif_action() {
		return notif_action;
	}

	public int getNotif_item_number() {
		return notif_item_number;
	}

}
