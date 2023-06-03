package com.notifications.domain;

/**
 * 
 * @author SaiTeja Koppala
 *
 */

public class HistoryTable {

	private int nv_ws_order_id;
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

	public HistoryTable(int nv_ws_order_id, String ts_modification, String order_type, String ivs_order_status,
			String processing_type, String ws_business_type, String nv_mf_orderer_ca, String nv_mf_orderer_id,
			String orderer_domestic, String invoicing_dealer, String mod_function, String mod_system, String mod_user,
			int version, String partition_key) {
		super();
		this.nv_ws_order_id = nv_ws_order_id;
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
	}

	@Override
	public String toString() {
		return "HistoryTable [nv_ws_order_id=" + nv_ws_order_id + ", ts_modification=" + ts_modification
				+ ", order_type=" + order_type + ", ivs_order_status=" + ivs_order_status + ", processing_type="
				+ processing_type + ", ws_business_type=" + ws_business_type + ", nv_mf_orderer_ca=" + nv_mf_orderer_ca
				+ ", nv_mf_orderer_id=" + nv_mf_orderer_id + ", orderer_domestic=" + orderer_domestic
				+ ", invoicing_dealer=" + invoicing_dealer + ", mod_function=" + mod_function + ", mod_system="
				+ mod_system + ", mod_user=" + mod_user + ", version=" + version + ", partition_key=" + partition_key
				+ "]";
	}

	public int getNv_ws_order_id() {
		return nv_ws_order_id;
	}

	public void setNv_ws_order_id(int nv_ws_order_id) {
		this.nv_ws_order_id = nv_ws_order_id;
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

	public HistoryTable() {

	}
}
