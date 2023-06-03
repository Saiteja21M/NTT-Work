package com.notifications.domain;

/**
 * POJO class
 * 
 * @author SaiTeja Koppala
 */
public class Notification {

	private int nv_ws_order_id;
	private String notification_type;
	private String notif_action;
	private int notif_item_number;
	private String notif_docdate;
	private String vehicle_id_7;
	private String mf_order_no;
	private String vehicle_state;
	private String client;
	private String product_type;
	private String brand;
	private String mf_model_code;
	private String ws_model_code;
	private String ts_modification;
	private String order_type;
	private String ivs_order_status;
	private String processing_type;
	private String ws_business_type;
	private String nv_mf_orderer_ca;
	private String nv_mf_orderer_id;
	private String orderer_domestic;
	private String invoicing_dealer;
	private String mod_function;
	private String mod_system;
	private String mod_user;
	private int version;
	private String partition_key;
	private String dealer_class;

	public Notification() {

	}

	public int getNv_ws_order_id() {
		return nv_ws_order_id;
	}

	public void setNv_ws_order_id(int nv_ws_order_id) {
		this.nv_ws_order_id = nv_ws_order_id;
	}

	public String getNotification_type() {
		return notification_type;
	}

	public void setNotification_type(String notification_type) {
		this.notification_type = notification_type;
	}

	public String getNotif_action() {
		return notif_action;
	}

	public void setNotif_action(String notif_action) {
		this.notif_action = notif_action;
	}

	public int getNotif_item_number() {
		return notif_item_number;
	}

	public void setNotif_item_number(int notif_item_number) {
		this.notif_item_number = notif_item_number;
	}

	public String getNotif_docdate() {
		return notif_docdate;
	}

	public void setNotif_docdate(String notif_docdate) {
		this.notif_docdate = notif_docdate;
	}

	public String getVehicle_id_7() {
		return vehicle_id_7;
	}

	public void setVehicle_id_7(String vehicle_id_7) {
		this.vehicle_id_7 = vehicle_id_7;
	}

	public String getMf_order_no() {
		return mf_order_no;
	}

	public void setMf_order_no(String mf_order_no) {
		this.mf_order_no = mf_order_no;
	}

	public String getVehicle_state() {
		return vehicle_state;
	}

	public void setVehicle_state(String vehicle_state) {
		this.vehicle_state = vehicle_state;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMf_model_code() {
		return mf_model_code;
	}

	public void setMf_model_code(String mf_model_code) {
		this.mf_model_code = mf_model_code;
	}

	public String getWs_model_code() {
		return ws_model_code;
	}

	public void setWs_model_code(String ws_model_code) {
		this.ws_model_code = ws_model_code;
	}

	public String getTs_modification() {
		return ts_modification;
	}

	public void setTs_modification(String ts_modification) {
		this.ts_modification = ts_modification;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getIvs_order_status() {
		return ivs_order_status;
	}

	public void setIvs_order_status(String ivs_order_status) {
		this.ivs_order_status = ivs_order_status;
	}

	public String getProcessing_type() {
		return processing_type;
	}

	public void setProcessing_type(String processing_type) {
		this.processing_type = processing_type;
	}

	public String getWs_business_type() {
		return ws_business_type;
	}

	public void setWs_business_type(String ws_business_type) {
		this.ws_business_type = ws_business_type;
	}

	public String getNv_mf_orderer_ca() {
		return nv_mf_orderer_ca;
	}

	public void setNv_mf_orderer_ca(String nv_mf_orderer_ca) {
		this.nv_mf_orderer_ca = nv_mf_orderer_ca;
	}

	public String getNv_mf_orderer_id() {
		return nv_mf_orderer_id;
	}

	public void setNv_mf_orderer_id(String nv_mf_orderer_id) {
		this.nv_mf_orderer_id = nv_mf_orderer_id;
	}

	public String getOrderer_domestic() {
		return orderer_domestic;
	}

	public void setOrderer_domestic(String orderer_domestic) {
		this.orderer_domestic = orderer_domestic;
	}

	public String getInvoicing_dealer() {
		return invoicing_dealer;
	}

	public void setInvoicing_dealer(String invoicing_dealer) {
		this.invoicing_dealer = invoicing_dealer;
	}

	public String getMod_function() {
		return mod_function;
	}

	public void setMod_function(String mod_function) {
		this.mod_function = mod_function;
	}

	public String getMod_system() {
		return mod_system;
	}

	public void setMod_system(String mod_system) {
		this.mod_system = mod_system;
	}

	public String getMod_user() {
		return mod_user;
	}

	public void setMod_user(String mod_user) {
		this.mod_user = mod_user;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPartition_key() {
		return partition_key;
	}

	public void setPartition_key(String partition_key) {
		this.partition_key = partition_key;
	}

	public String getDealer_class() {
		return dealer_class;
	}

	public void setDealer_class(String dealer_class) {
		this.dealer_class = dealer_class;
	}

	@Override
	public String toString() {
		return "Notification [nv_ws_order_id=" + nv_ws_order_id + ", notification_type=" + notification_type
				+ ", notif_action=" + notif_action + ", notif_item_number=" + notif_item_number + ", notif_docdate="
				+ notif_docdate + ", vehicle_id_7=" + vehicle_id_7 + ", mf_order_no=" + mf_order_no + ", vehicle_state="
				+ vehicle_state + ", client=" + client + ", product_type=" + product_type + ", brand=" + brand
				+ ", mf_model_code=" + mf_model_code + ", ws_model_code=" + ws_model_code + ", ts_modification="
				+ ts_modification + ", order_type=" + order_type + ", ivs_order_status=" + ivs_order_status
				+ ", processing_type=" + processing_type + ", ws_business_type=" + ws_business_type
				+ ", nv_mf_orderer_ca=" + nv_mf_orderer_ca + ", nv_mf_orderer_id=" + nv_mf_orderer_id
				+ ", orderer_domestic=" + orderer_domestic + ", invoicing_dealer=" + invoicing_dealer
				+ ", mod_function=" + mod_function + ", mod_system=" + mod_system + ", mod_user=" + mod_user
				+ ", version=" + version + ", partition_key=" + partition_key + ", dealer_class=" + dealer_class + "]";
	}

	public Notification(int nv_ws_order_id, String notification_type, String notif_action, int notif_item_number,
			String notif_docdate, String vehicle_id_7, String mf_order_no, String vehicle_state, String client,
			String product_type, String brand, String mf_model_code, String ws_model_code, String ts_modification,
			String order_type, String ivs_order_status, String processing_type, String ws_business_type,
			String nv_mf_orderer_ca, String nv_mf_orderer_id, String orderer_domestic, String invoicing_dealer,
			String mod_function, String mod_system, String mod_user, int version, String partition_key,
			String dealer_class) {
		super();
		this.nv_ws_order_id = nv_ws_order_id;
		this.notification_type = notification_type;
		this.notif_action = notif_action;
		this.notif_item_number = notif_item_number;
		this.notif_docdate = notif_docdate;
		this.vehicle_id_7 = vehicle_id_7;
		this.mf_order_no = mf_order_no;
		this.vehicle_state = vehicle_state;
		this.client = client;
		this.product_type = product_type;
		this.brand = brand;
		this.mf_model_code = mf_model_code;
		this.ws_model_code = ws_model_code;
		this.ts_modification = ts_modification;
		this.order_type = order_type;
		this.ivs_order_status = ivs_order_status;
		this.processing_type = processing_type;
		this.ws_business_type = ws_business_type;
		this.nv_mf_orderer_ca = nv_mf_orderer_ca;
		this.nv_mf_orderer_id = nv_mf_orderer_id;
		this.orderer_domestic = orderer_domestic;
		this.invoicing_dealer = invoicing_dealer;
		this.mod_function = mod_function;
		this.mod_system = mod_system;
		this.mod_user = mod_user;
		this.version = version;
		this.partition_key = partition_key;
		this.dealer_class = dealer_class;
	}

}
