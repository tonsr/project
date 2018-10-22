package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.mapper.DemoMapper;

@Repository
public class DemoDao {

	@Autowired
	public DemoMapper demoMapper;
	
	public List<Map<String,String>> queryDemo(Map<String,String> demo){
		return demoMapper.queryDemo(demo);
	}
}
