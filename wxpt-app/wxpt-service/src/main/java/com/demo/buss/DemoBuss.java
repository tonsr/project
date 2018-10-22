package com.demo.buss;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.DemoDao;

@Service
public class DemoBuss {

	@Autowired
	private DemoDao demoDao;
	
	public List<Map<String,String>> queryDemo(Map<String, String> param){
		return demoDao.queryDemo(param);
	}
}
