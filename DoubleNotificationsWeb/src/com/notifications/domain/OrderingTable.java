package com.notifications.domain;

/**
 * 
 * @author SaiTeja Koppala
 *
 */

public class OrderingTable {

	private int nv_ws_order_id;
	private String mf_order_no;
	private String vehicle_id_7;
	private String vehicle_state;
	private String product_type;
	private String brand;
	private String mf_model_code;
	private String ws_model_code;
	private String client;

	public OrderingTable() {

	}

	public int getNv_ws_order_id() {
		return nv_ws_order_id;
	}

	public void setNv_ws_order_id(int nv_ws_order_id) {
		this.nv_ws_order_id = nv_ws_order_id;
	}

	public String getMf_order_no() {
		return mf_order_no;
	}

	public void setMf_order_no(String mf_order_no) {
		this.mf_order_no = mf_order_no;
	}

	public String getVehicle_id_7() {
		return vehicle_id_7;
	}

	public void setVehicle_id_7(String vehicle_id_7) {
		this.vehicle_id_7 = vehicle_id_7;
	}

	public String getVehicle_state() {
		return vehicle_state;
	}

	public void setVehicle_state(String vehicle_state) {
		this.vehicle_state = vehicle_state;
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public OrderingTable(int nv_ws_order_id, String mf_order_no, String vehicle_id_7, String vehicle_state,
			String product_type, String brand, String mf_model_code, String ws_model_code, String client) {
		super();
		this.nv_ws_order_id = nv_ws_order_id;
		this.mf_order_no = mf_order_no;
		this.vehicle_id_7 = vehicle_id_7;
		this.vehicle_state = vehicle_state;
		this.product_type = product_type;
		this.brand = brand;
		this.mf_model_code = mf_model_code;
		this.ws_model_code = ws_model_code;
		this.client = client;
	}

	@Override
	public String toString() {
		return "OrderingTable [nv_ws_order_id=" + nv_ws_order_id + ", mf_order_no=" + mf_order_no + ", vehicle_id_7="
				+ vehicle_id_7 + ", vehicle_state=" + vehicle_state + ", product_type=" + product_type + ", brand="
				+ brand + ", mf_model_code=" + mf_model_code + ", ws_model_code=" + ws_model_code + ", client=" + client
				+ "]";
	}

}
