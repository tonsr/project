package com.demo.impl;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.demo.api.DemoApi;
import com.demo.buss.DemoBuss;

@Path("demo")
@Service(timeout=3000,protocol="rest")
public class DemoApiImpl implements DemoApi{

	@Autowired
	private DemoBuss demoBuss;
	
	@GET
	@Path("hello")
	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}
	
	
	@GET
	@Path("{id : \\d+}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public List<Map<String,String>> queryDemo(@PathParam("id") String id){
		Map<String,String> param = new HashMap<>();
		param.put("id", id);
		return demoBuss.queryDemo(param);
	}

}
