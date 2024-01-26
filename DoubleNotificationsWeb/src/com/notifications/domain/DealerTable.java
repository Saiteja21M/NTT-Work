package com.notifications.domain;

/**
 * 
 * @author SaiTeja Koppala
 *
 */

public class DealerTable {

	private String nv_mf_orderer_id;
	private String nv_ws_orderer_id;
	private String orderer_domestic;
	private String dealerClass;

	public String getNv_mf_orderer_id() {
		return nv_mf_orderer_id;
	}

	public void setNv_mf_orderer_id(String nv_mf_orderer_id) {
		this.nv_mf_orderer_id = nv_mf_orderer_id;
	}

	public String getNv_ws_orderer_id() {
		return nv_ws_orderer_id;
	}

	public void setNv_ws_orderer_id(String nv_ws_orderer_id) {
		this.nv_ws_orderer_id = nv_ws_orderer_id;
	}

	public String getOrderer_domestic() {
		return orderer_domestic;
	}

	public void setOrderer_domestic(String orderer_domestic) {
		this.orderer_domestic = orderer_domestic;
	}

	public String getDealerClass() {
		return dealerClass;
	}

	public void setDealerClass(String dealerClass) {
		this.dealerClass = dealerClass;
	}

}
