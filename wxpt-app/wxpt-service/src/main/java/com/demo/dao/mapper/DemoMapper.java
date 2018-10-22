package com.demo.dao.mapper;

import java.util.List;
import java.util.Map;

import com.demo.dbconnector.anotition.DataSource;

public interface DemoMapper {

	@DataSource(value = "readdb*")
	public List<Map<String,String>> queryDemo(Map<String,String> demo);
}
