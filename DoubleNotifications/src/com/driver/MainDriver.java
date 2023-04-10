package com.driver;

import java.util.Scanner;

import com.dbcon.DBConnectionException;
import com.helpers.HelpingMethods;

public class MainDriver {

	public static void main(String[] args) throws DBConnectionException {

		Scanner sc = new Scanner(System.in);

		System.out.print("Please enter nv_ws_order_id: ");

		int nv_ws_order_id = sc.nextInt();

		HelpingMethods hm = new HelpingMethods();

		hm.showNotificationByOrder(false, nv_ws_order_id);

		sc.close();
	}

}
