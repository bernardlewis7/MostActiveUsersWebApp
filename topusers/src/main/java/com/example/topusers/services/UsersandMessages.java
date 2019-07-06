package com.example.topusers.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.ws.Response;

import org.apache.tomcat.util.buf.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.topusers.entities.ParseData;

@Component
public class UsersandMessages implements ParseData  {
	
	
	ArrayList<String> userandmessages;
	
	public ArrayList<String> parseDatafromURL(String endpoint) {
		
		try{
		URL url = new URL(endpoint);
		HttpURLConnection httpconnection = (HttpURLConnection) url.openConnection();
		httpconnection.setRequestMethod("GET");
		httpconnection.setRequestProperty("ACCEPT", "application/json");
		if(httpconnection.getResponseCode()==200){
			BufferedReader br = new BufferedReader(new InputStreamReader(httpconnection.getInputStream()));
			String output;
			while((output= br.readLine())!=null)
				{
				if(!(output.contentEquals("[") || output.contentEquals("]")))
				{
					output=output.trim();
					if(output.endsWith(","))
						userandmessages.add(output.substring(1,output.length()-2));
					else
						userandmessages.add(output.substring(1,output.length()-1));
				}
				
				}
		
			}
		}
	
		catch(IOException e){
			System.out.println(e);
		}
			
	return userandmessages;
	}

	public void cleanupData(){
		userandmessages=new ArrayList<>();
	}

}
