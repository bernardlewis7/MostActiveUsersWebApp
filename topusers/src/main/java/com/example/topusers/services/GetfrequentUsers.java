package com.example.topusers.services;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.topusers.entities.ParseData;
import com.example.topusers.entities.TableDataEntity;


@Service
public class GetfrequentUsers {
	
	@Autowired
	ParseData parsedata;
	
	Iterator itr;
	Map<String, Integer> usersFrequencyMap;
	Map<String, List<String>> usersMessageMap;
	Map<String, String> frequentmessagesMap;
	List<String> users;
	List<String> listOfMessages ;
	List<TableDataEntity> tabledata; 
	
	public List<TableDataEntity> getMostFrequentUsers(String endpoint){
		String uname,msg;
		
		parsedata.cleanupData();
		itr  = parsedata.parseDatafromURL(endpoint).iterator();
		String string;
		while(itr.hasNext())
		{
			
			string=(String) itr.next();
					uname=string.split(":",2)[0];
					msg=string.split(":",2)[1];
					
					users.add(uname);
					
					listOfMessages=usersMessageMap.get(uname);		
					
					if(listOfMessages==null) 
						listOfMessages =new ArrayList<>();
				
					listOfMessages.add(msg);
					usersMessageMap.put(uname, listOfMessages);
			
		}
		
		usersFrequencyMap=getFrequentStringsCount(users,5);
		
		for(Map.Entry<String, Integer> usersMap : usersFrequencyMap.entrySet()){
			itr=getFrequentStringsCount(usersMessageMap.get(usersMap.getKey()),1).keySet().iterator();
			String tempFrequentString = (String)itr.next();
			String username = usersMap.getKey();
			frequentmessagesMap.put(username, tempFrequentString);
			
			TableDataEntity tempObj = new TableDataEntity();
			tempObj.setUsername(username);
			tempObj.setFrequestStringMsg(tempFrequentString);
			tempObj.setCount(usersFrequencyMap.get(username));
			
			tabledata.add(tempObj);

		}
		
		return tabledata;
		
	}
	
	

public LinkedHashMap<String, Integer> getFrequentStringsCount(List<String> users,Integer numberOfUsers){

	LinkedHashMap<String, Integer> frequentStringsCount = new LinkedHashMap<>();

	HashMap<String, Integer> stringsfrequency = generateMapforStringsfrequency(users,numberOfUsers);
	
	stringsfrequency=sortMapByValue(stringsfrequency);
		
		for(String strings : stringsfrequency.keySet()){
			if(numberOfUsers==0)
				break;
			frequentStringsCount.put(strings,stringsfrequency.get(strings));
			numberOfUsers--;
		}
		return frequentStringsCount;
}


public HashMap<String, Integer> generateMapforStringsfrequency(List<String> users,Integer numberOfUsers){

	HashMap<String, Integer> stringsfrequency = new HashMap<>();
	
	for(String str : users){
		if(stringsfrequency.containsKey(str))
			stringsfrequency.put(str, stringsfrequency.get(str)+1);
		else
			stringsfrequency.put(str, 1);
	}
	return stringsfrequency;
}


public HashMap<String, Integer> sortMapByValue(HashMap<String,Integer> stringsfrequencyMap) {
	HashMap<String, Integer> sortedUsersFrequency = stringsfrequencyMap
	        .entrySet()
	        .stream()
	        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	        .collect(
	            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
	                LinkedHashMap::new));

		return sortedUsersFrequency;
}

public void cleanUpData(){
	
	usersFrequencyMap = new HashMap<>();
	usersMessageMap= new HashMap<>();
	frequentmessagesMap = new LinkedHashMap<>();
	users = new ArrayList<>();
	tabledata = new ArrayList<>();
}
	
}
