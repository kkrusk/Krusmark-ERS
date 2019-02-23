package com.krusmark.jackson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krusmark.services.ReimbursementService;
import com.krusmark.services.ReportService;

public class JsonJacksonObjectMapper {
	public static String getReimb(int id, int role) {

		File file = null;
		try {
			file = File.createTempFile("prefix-", "-suffix");
		} catch (IOException e1) {
			System.err.println("ERR: Unable to create temp file.");
			e1.printStackTrace();
		};

		ObjectMapper mapper = new ObjectMapper();

		/**
		 * Write object to file
		 */
		try {
			//mapper.writeValue(file, ReimbursementService.pull());//Plain JSON
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, ReimbursementService.pull(id, role));// Prettified JSON
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = "";
		if (file != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line = null;
				while ((line = br.readLine()) != null) {
					//System.out.println(line);
					json += line + "\n";
				}
			} catch (FileNotFoundException e) {
				System.out.println("Can't find temp file");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}
	
	public static String getReport() {
		File file = null;
		try {
			file = File.createTempFile("prefix-", "-suffix");
		} catch (IOException e1) {
			System.err.println("ERR: Unable to create temp file.");
			e1.printStackTrace();
		};

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, ReportService.pull());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = "";
		if (file != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line = null;
				while ((line = br.readLine()) != null) {
					json += line + "\n";
				}
			} catch (FileNotFoundException e) {
				System.out.println("Can't find temp file");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(json);
		
		return json;
	}
}
