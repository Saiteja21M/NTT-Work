package com.querygenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListProvider {

	public List<String> getList(String path) throws IOException {

		String line = "";
		List<String> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				list.add(line);
			}

		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}

		return list;

	}
	
	public List<String> getHeaders(String path) throws IOException
	{
		List<String> headerData = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				headerData.add(br.readLine());

		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}
		
		return headerData;
	}

}
