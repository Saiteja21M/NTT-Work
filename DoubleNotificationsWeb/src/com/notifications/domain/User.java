package com.notifications.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author SaiTeja Koppala
 *
 */

public class User {

	public Map<String, String> userMapping = new HashMap<>();

	public String getUserName(String qx_id) {

		userMapping.put("qxz3a7g", "Sai Teja Koppala");

		if (userMapping.containsKey(qx_id)) {
			return userMapping.get(qx_id);
		}

		return null;
	}

	public void addUser(String qx_id, String userName) {

	}

}
