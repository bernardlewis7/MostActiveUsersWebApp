package com.example.topusers.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.topusers.entities.TableDataEntity;
import com.example.topusers.services.GetfrequentUsers;

@RestController
public class UserData {
	@Autowired
	GetfrequentUsers getfrequentusers;
	
	@RequestMapping(value="/getResults")
	public ResponseEntity<?> getTopUsers(){
		String endpoint="http://www.mocky.io/v2/5cdd110c3000007825e23470";
		getfrequentusers.cleanUpData();
		List<TableDataEntity> tabledata = getfrequentusers.getMostFrequentUsers(endpoint);
		return ResponseEntity.ok(tabledata);
	}
}
