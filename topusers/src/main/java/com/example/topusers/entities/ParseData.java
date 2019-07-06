package com.example.topusers.entities;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
@Component
public interface ParseData {

	public ArrayList<String> parseDatafromURL(String endpoint);
	public void cleanupData();
}
