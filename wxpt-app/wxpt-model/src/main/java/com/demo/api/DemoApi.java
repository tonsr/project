package com.demo.api;

import java.util.List;
import java.util.Map;

public interface DemoApi {

	public String hello();
	
	public List<Map<String,String>> queryDemo(String id);
}
