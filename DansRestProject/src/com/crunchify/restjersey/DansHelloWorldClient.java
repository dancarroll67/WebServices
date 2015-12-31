package com.crunchify.restjersey;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class DansHelloWorldClient {

	public static void main(String[] args) {
		
		try {
 
			System.out.println("hello world client start");
		
			// Step2: Now pass JSON File Data to REST Service
			try {
				URL url = new URL("http://localhost:8080/CrunchifyRESTJerseyExample/crunchify/helloworldpost/");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(getJsonObject().toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				String line="";
				while ((line =in.readLine()) != null) {
					System.out.println("lient  in.readline not null: "+ line);
				}
				System.out.println("\n hello world REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\n Error while calling hello world REST Service");
				System.out.println(e);
			}
 
			//br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static JSONObject getJsonObject() {
		// Step1: Let's 1st read file from fileSystem
		// Change CrunchifyJSON.txt path here
		System.out.println("hello world client getJson");
		String string = "";
		try {
			InputStream crunchifyInputStream = new FileInputStream("C:\\Users\\dcarroll\\Documents\\CrunchifyJSON.txt");
			InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
			BufferedReader br = new BufferedReader(crunchifyReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}

		JSONObject jsonObject = new JSONObject(string);
		System.out.println("client json object: " + jsonObject.toString());
		return jsonObject;
	}

}
