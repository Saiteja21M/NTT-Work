package com.querygenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryReplacer {

	public static Map<String, String> tables = new HashMap<>();

	public static AllQueries all = new AllQueries();

	static {

		tables.put("PA", "toin02");
		tables.put("TB", "toin03");
		tables.put("OT", "toin03");
		tables.put("TL", "toin03");
		tables.put("RL", "toin03");
		tables.put("TP", "toin03");
		tables.put("TO", "toin03");
		tables.put("TF", "toin03");
		tables.put("OR", "toin04");
		tables.put("PD", "toin05");
		tables.put("IM", "toin06");
		tables.put("IW", "toin07");
		tables.put("IR", "toin08");

	}

	public static void generateChildQueryData(List<String> nv_wsList, List<String> timeList, String notifType,
			List<String> countList) throws IOException {

		if (tables.containsKey(notifType)) {
			String originalQuery = all.getChildQuery();

			if (tables.containsKey(notifType)) {
				originalQuery.replaceAll("toin02", tables.get(notifType));
			}

			List<String> insertQuery = new ArrayList<>();

			for (int i = 0; i < nv_wsList.size(); i++) {
				if (countList.get(i).equals("2")) {
					insertQuery.add(originalQuery.replaceAll("43445721", nv_wsList.get(i)).replaceAll("PA", notifType)
							.replaceAll("19.01.23", timeList.get(i)));
				}
			}
			FileGenerator.printQueryToAFile(insertQuery, "childQuery", notifType);
		}
	}

	public static void generateQueryData(String originalQuery, List<String> nv_wsList, List<String> timeList,
			String notifType, List<String> countList, String queryType) throws IOException {

		List<String> insertQuery = new ArrayList<>();
		for (int i = 0; i < nv_wsList.size(); i++) {
			if (countList.get(i).equals("2")) {
				insertQuery.add(originalQuery.replaceAll("43445721", nv_wsList.get(i)).replaceAll("PA", notifType)
						.replaceAll("19.01.23", timeList.get(i)));
			}
		}
		FileGenerator.printQueryToAFile(insertQuery, queryType, notifType);
	}

}
