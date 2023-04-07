package com.querygenerator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileGenerator {
	public static void printQueryToAFile(List<String> queries, String queryType, String notifType) throws IOException {

		String filePath = queryType + "_" + notifType + ".txt";

		File file = new File(filePath);

		if (!file.exists()) {
			file.createNewFile();
		}

		PrintWriter out = new PrintWriter(file);

		for (String query : queries) {
			out.println(query);
		}
		out.close();

	}
}
