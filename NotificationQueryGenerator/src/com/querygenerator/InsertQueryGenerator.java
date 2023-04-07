package com.querygenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertQueryGenerator extends QueryReplacer {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		ListProvider lp = new ListProvider();

		AllQueries all = new AllQueries();

		System.out.print("Please paste the file path here: ");

		String filePath = sc.next();

		System.out.print("Please enter the notification type: ");

		String notifType = sc.next();

		List<String> list = lp.getList(filePath);

		List<String> nv_wsList = new ArrayList<>();
		List<String> timeList = new ArrayList<>();
		List<String> countList = new ArrayList<>();

		for (String string : list) {
			String[] lines = string.split(",");
			nv_wsList.add(lines[0]);
			timeList.add(lines[1]);
			countList.add(lines[2]);
			//System.out.println(lines[1]+lines[2]+lines[3]);
		}

		if (tables.containsKey(notifType))
			QueryReplacer.generateChildQueryData(nv_wsList, timeList, notifType, countList);

		QueryReplacer.generateQueryData(all.getParentQuery(), nv_wsList, timeList, notifType, countList, "ParentQuery");

		QueryReplacer.generateQueryData(all.getUpdateQuery(), nv_wsList, timeList, notifType, countList,
				"NotifUpdateQuery");

		QueryReplacer.generateQueryData(all.getDoubleCheckQuery(), nv_wsList, timeList, notifType, countList,
				"DoubleCheckQuery");

		QueryReplacer.generateQueryData(all.getC05UpdateQuery(), nv_wsList, timeList, notifType, countList,
				"c05UpdateQuery");

		System.out.println("Quries Successfully Generated");

		sc.close();
	}
}
